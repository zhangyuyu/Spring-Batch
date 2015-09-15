import model.Report;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import processor.CustomItemProcessor;
import reader.ReportFieldSetMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan
@Import(AppConfig.class)
public class HelloWorldJobConfig {

        @Autowired
        private JobBuilderFactory jobs;

        @Autowired
        private StepBuilderFactory steps;

        @Bean(name="helloWorldJob")
        public Job helloWorldJob() {
                return jobs.get("helloWorldJob")
                    .start(step())
                    .build();
        }

        @Bean
        public Step step() {
                return steps.get("step")
                    .<Report, Report> chunk(10)
                    .reader(cvsFileItemReader())
                    .processor(itemProcessor())
                    .writer(xmlItemWriter())
                    .build();
        }

        @Bean
        public FlatFileItemReader<Report> cvsFileItemReader() {
                FlatFileItemReader<Report> reader = new FlatFileItemReader<Report>();
                reader.setResource(new ClassPathResource("cvs/input/report.csv"));
                reader.setLineMapper(new DefaultLineMapper<Report>() {{
                        setLineTokenizer(new DelimitedLineTokenizer() {{
                                setNames(new String[] { "name", "sex" , "age" ,"birthday" });
                        }});
                        setFieldSetMapper(new ReportFieldSetMapper());
                        /*setFieldSetMapper(new BeanWrapperFieldSetMapper<Report>() {{
                                setTargetType(Report.class);
                        }});*/
                }});
                return reader;
        }

        @Bean
        public ItemProcessor<Report, Report> itemProcessor() {
                return new CustomItemProcessor();
        }

        @Bean
        public ItemWriter<Report>xmlItemWriter() {
                StaxEventItemWriter writer = new StaxEventItemWriter<>();

                FileSystemResource resource = new FileSystemResource("xml/outputs/report.xml");
                writer.setResource(resource);

                Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
                Map<String,Object> properties = new HashMap<String, Object>();
                marshaller.setClassesToBeBound(Report.class);
                marshaller.setMarshallerProperties(properties);
                writer.setMarshaller(marshaller);

                writer.setRootTagName("report");
                return writer;
        }


}
