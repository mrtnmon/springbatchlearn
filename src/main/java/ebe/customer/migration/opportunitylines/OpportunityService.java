package ebe.customer.migration.opportunitylines;

import java.io.IOException;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import be.ebts.schemas._2014._05.qe.CreateOpportunityRequest;
import be.ebts.schemas._2014._05.qe.CreateOpportunityResponse;
import be.ebts.schemas._2014._05.qe.header.Header;

@Component
public class OpportunityService {

	private static final String BATCH_LOGIN_NAME = "local:BATCH";
	@Resource(name = "opportunityInterfaceWebServiceTemplate")
	private WebServiceTemplate template;
	@Autowired
	private OpportunityRequestConverter converter;

	// @Autowired
	// private SecurityService securityService;
	@SuppressWarnings("unchecked")
	private <S, T> T send(S request, String action) {
		WebServiceMessageCallback callback = new SoapActionCallback(action) {
			@Override
			public void doWithMessage(WebServiceMessage message) throws IOException {
				super.doWithMessage(message);
				SoapMessage sm = (SoapMessage) message;
				sm.getSoapHeader().addHeaderElement(new QName("http://schemas.ebts.be/2014/05/QE/Header", "BtsAuthHeader")).setText("C549B25B-2E4E-4302-BF43-1AA527B3F261");
			}
		};
		final Object response = template.marshalSendAndReceive(request, callback);
		try {
			parseResponseForError(response);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		return (T) response;
	}

	public String createOpportunity(CreateOpportunityRequest opportunity) {
		CreateOpportunityResponse response = send(opportunity.withHeader(createUserHeader(true)), "Create");
		return response.getOptyId();
	};

	private Header createUserHeader(boolean realTime) {
		String username = "anonymousUser";
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			username = BATCH_LOGIN_NAME;
		}
		return new Header().withRequestedBy(username).withRealTime(realTime);
	}

	private static <T> void parseResponseForError(T response) {
		JAXBElement<String> errorDescriptionElement = null;
		if (response instanceof CreateOpportunityResponse) {
			final be.ebts.schemas._2014._05.qe.CreateOpportunityResponse.Result result = ((CreateOpportunityResponse) response).getResult();
			errorDescriptionElement = result == null ? null : result.getErrorDescription();
		}
		if (errorDescriptionElement != null && errorDescriptionElement.getValue() != null && !errorDescriptionElement.getValue().isEmpty()) {
			throw new RuntimeException(errorDescriptionElement.getValue());
			// throw new TechnicalException(TechnicalErrorCode.CODE_SERVICE_ERROR, errorDescriptionElement.getValue());
		}
	}
}
