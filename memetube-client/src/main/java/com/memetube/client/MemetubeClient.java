package com.memetube.client;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.memetube.generated.GetMemeCategoryOfTheDayRequest;
import com.memetube.generated.GetMemeCategoryOfTheDayResponse;
import com.memetube.generated.ObjectFactory;

public class MemetubeClient extends WebServiceGatewaySupport {

	ObjectFactory oFactory = new ObjectFactory();

	WebServiceTemplate ws = getWebServiceTemplate();

	public GetMemeCategoryOfTheDayResponse getMemeCategoryOfTheDay() throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.memetube.generated");
		marshaller.afterPropertiesSet();

		ws.setMarshaller(marshaller);
		ws.setUnmarshaller(marshaller);

		GetMemeCategoryOfTheDayRequest request = oFactory.createGetMemeCategoryOfTheDayRequest();
		request.setDayOfWeek(1);
		@SuppressWarnings("unchecked")
		GetMemeCategoryOfTheDayResponse response = (GetMemeCategoryOfTheDayResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:7070/spring4soap-1/soapws", request);

		if (response != null) {
			return response;
		} else {
			return null;
		}
	}

}
