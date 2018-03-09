package ebe.customer.migration.opportunitylines;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

import be.ebts.schemas._2014._05.qe.CreateOpportunityRequest;
import be.ebts.schemas._2014._05.qe.ObjectFactory;

@Component
public class OpportunityRequestConverter {
	// @Autowired
	ObjectFactory factory = new ObjectFactory();


	public CreateOpportunityRequest convertOpportunityRequestDTO(OpportunityRequestDTO dto) {
		XMLGregorianCalendar date;
		CreateOpportunityRequest request = null;
		try {
			request = new CreateOpportunityRequest()
					.withPrimaryAccountId(dto.getPrimaryAccountId())
					.withName(dto.getName())
					.withSalesStage(dto.getSalesStage())
					.withElecContractStartDate(factory.createCreateOpportunityRequestElecContractStartDate(stringToXMLGregorianCalendar( dto.getElecContractStartDate())))
					.withCurrentElecContractEndDate(factory.createCreateOpportunityRequestCurrentElecContractEndDate(stringToXMLGregorianCalendar( dto.getCurrentElecContractEndDate())))
					.withElecYearlyVolume(factory.createCreateOpportunityRequestElecYearlyVolume(dto.getElecYearlyVolume()))
					.withElecEanCount(factory.createCreateOpportunityRequestElecEanCount(dto.getElecEanCount()))
					.withGasContractStartDate(factory.createCreateOpportunityRequestGasContractStartDate(stringToXMLGregorianCalendar(dto.getGasContractStartDate())))
					.withCurrentGasContractEndDate(factory.createCreateOpportunityRequestGasContractStartDate(stringToXMLGregorianCalendar(dto.getCurrentElecContractEndDate())))
					.withGasYearlyVolume(factory.createCreateOpportunityRequestElecYearlyVolume(dto.getElecYearlyVolume()))
					.withGasEanCount(factory.createCreateOpportunityRequestGasEanCount(dto.getGasEanCount()))
					.withInjectionContractStartDate(factory.createCreateOpportunityRequestInjectionContractStartDate(stringToXMLGregorianCalendar(dto.getInjectionContractStartDate())))
					.withInjectionYearlyVolume(factory.createCreateOpportunityRequestInjectionYearlyVolume(dto.getInjectionYearlyVolume()))
					.withInjectionEanCount(factory.createCreateOpportunityRequestInjectionEanCount(dto.getInjectionEanCount()))
					.withAssignedResponsible(factory.createCreateOpportunityRequestAssignedResponsible(dto.getAssignedResponsible()))
					.withElecDuration(factory.createCreateOpportunityRequestElecDuration(dto.getElecDuration()))
					.withGasDuration(factory.createCreateOpportunityRequestGasDuration(dto.getGasDuration()))
					.withInjectionDuration(factory.createCreateOpportunityRequestInjectionDuration(dto.getInjectionDuration()))
					.withInjectDuration(dto.getInjectDuration())
					.withCreditChecks(factory.createCreditCheckCollectionType()) 
					;
		
		} catch (DatatypeConfigurationException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}

	private XMLGregorianCalendar stringToXMLGregorianCalendar(String stringDate) throws ParseException, DatatypeConfigurationException {
		DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date dateformat = df.parse(stringDate);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(dateformat);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	}
}
