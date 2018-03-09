package ebe.customer.migration.opportunitylines;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class FileExistsListener {
	
	@Autowired
	private Environment env;
	
	@BeforeStep
	public void checkIfFileExists() throws IOException {
		String fileName = env.getProperty("persistence.file");
		if (Files.notExists(Paths.get(fileName))) {
			Files.createFile(Paths.get(fileName));
			System.out.println("did create file");
		}
		
		System.out.println("did not create file");
	}
}
