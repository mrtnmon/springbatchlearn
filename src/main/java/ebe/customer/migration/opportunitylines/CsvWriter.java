package ebe.customer.migration.opportunitylines;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CsvWriter {

	@Autowired
	private Environment env;

	public void WriteDataToCsv(String id, String name) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(env.getProperty("persistence.file")), true));
		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(',');
		sb.append(name);
		sb.append('\n');

		printWriter.append(sb.toString());
		printWriter.close();

	}
}
