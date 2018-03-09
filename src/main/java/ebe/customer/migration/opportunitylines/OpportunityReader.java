package ebe.customer.migration.opportunitylines;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class OpportunityReader extends FlatFileItemReader<OpportunityRequestDTO> {

	public OpportunityReader() {
		setResource(new ClassPathResource("test-data.csv"));
		setLineMapper(new DefaultLineMapper<OpportunityRequestDTO>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { 
								"primaryAccountId", 
								"salesStage", 
								"name", 
								"consortiumFlag", 
								"elecContractStartDate", 
								"currentElecContractEndDate",
								"elecYearlyVolume", 
								"elecEanCount", 
								"gasContractStartDate", 
								"currentGasContractEndDate", 
								"gasYearlyVolume", 
								"gasEanCount",
								"injectionContractStartDate", 
								"injectionYearlyVolume", 
								"injectionEanCount", 
								"assignedResponsible", 
								"elecDuration", 
								"gasDuration",
								"injectionDuration", 
								"injectDuration"
								// "creditChecks"
						});
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<OpportunityRequestDTO>() {
					{
						setTargetType(OpportunityRequestDTO.class);
					}
				});
			}
		});
	}
}
