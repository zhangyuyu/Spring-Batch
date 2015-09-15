package itemreader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import model.Report;

public class ReportFieldSetMapper implements FieldSetMapper<Report> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {

		Report report = new Report();
		report.setName(fieldSet.readString(0));
		report.setSex(fieldSet.readString(1));
		report.setAge(fieldSet.readInt(2));

		String date = fieldSet.readString(3);
		try {
			report.setBirthday(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return report;
	}

}
