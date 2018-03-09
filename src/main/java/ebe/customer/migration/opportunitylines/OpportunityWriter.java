package ebe.customer.migration.opportunitylines;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import be.ebts.schemas._2014._05.qe.CreateOpportunityRequest;
import org.springframework.stereotype.Component;

@Component
public class OpportunityWriter implements ItemWriter<CreateOpportunityRequest> {

	@Autowired
	OpportunityService opportunityService;
	
	@Autowired
	CsvWriter csvWriter;
	
	@Override
	public void write(List<? extends CreateOpportunityRequest> items) throws Exception {

		for (CreateOpportunityRequest request : items) {
			String result = opportunityService.createOpportunity(request);
			csvWriter.WriteDataToCsv(request.getPrimaryAccountId(), request.getName());
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
