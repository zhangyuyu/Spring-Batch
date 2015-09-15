package config;

import model.Report;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import processor.CustomItemProcessor;

@Configuration
@ComponentScan
@Import({DatabaseConfig.class , AppConfig.class})
public class HelloWorldJobConfig{

    @Autowired
    JobBuilderFactory jobs;

    @Autowired
    StepBuilderFactory steps;

    @Autowired
    DriverManagerDataSource dataSource;

    @Bean(name = "helloWorldJob")
    public Job helloWorldJob(){
        return jobs.get("helloWorldJob")
                    .start(step())
                    .build();
    }

    @Bean
    public Step step(){
        return steps.get("step")
                    .< Report,Report>chunk(10)
                    .reader(cvsFileReader())
                    .processor(customerItemProcessor())
                    .writer(mysqlItemWriter())
                    .build();
    }

    @Bean
    public FlatFileItemReader<Report> cvsFileReader() {
        FlatFileItemReader<Report> reader = new FlatFileItemReader<Report>();
        reader.setResource(new ClassPathResource("cvs/input/report.csv"));
        reader.setLineMapper(new DefaultLineMapper<Report>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{
                        "name", "sex", "age", "birthday"
                });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Report>() {{
                setTargetType(Report.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<Report , Report> customerItemProcessor(){
        return new CustomItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Report> mysqlItemWriter() {
        JdbcBatchItemWriter<Report> writer = new JdbcBatchItemWriter<Report>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into raw_report(name,sex,age,birthday) values (:name, :sex, :age, :birthday)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Report>());
        return writer;
    }
}
