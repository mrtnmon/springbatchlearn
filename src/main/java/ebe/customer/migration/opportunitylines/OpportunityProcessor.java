package ebe.customer.migration.opportunitylines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import be.ebts.schemas._2014._05.qe.CreateOpportunityRequest;

@Component
public class OpportunityProcessor implements ItemProcessor<OpportunityRequestDTO, CreateOpportunityRequest> {

	private static final Logger log = LoggerFactory.getLogger(OpportunityProcessor.class);

	@Autowired
	OpportunityRequestConverter opportunityRequestConverter;
	@Autowired
	Environment env;
	
	@Override
	public CreateOpportunityRequest process(OpportunityRequestDTO item) throws Exception {

		if(!checkCSVForEntry(item.getPrimaryAccountId(), item.getName()))	{
			return opportunityRequestConverter.convertOpportunityRequestDTO(item);
		} else {
			return null;
		}
	}

	private boolean checkCSVForEntry(String id, String name) {
		String csvFile = env.getProperty("persistence.file");
		String lineToCheckInCSV = "";
		String inputString = id + "," + name;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((lineToCheckInCSV = br.readLine()) != null) {
				if (lineToCheckInCSV.equals(inputString)) {
					System.out.println("entry with name " + name + " and id " +  id + " already exists");
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("entry with name " + name + " and id " +  id + " does not exist yet");
		return false;
	}
}
