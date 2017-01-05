package com.memetube.client;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.memetube.generated.GetStudentRequest;
import com.memetube.generated.GetStudentResponse;
import com.memetube.generated.ObjectFactory;

public class MemetubeClient extends WebServiceGatewaySupport {

	ObjectFactory oFactory = new ObjectFactory();

	public GetStudentResponse getMemeCategoryOfTheDay() {

		@SuppressWarnings("unchecked")
		JAXBElement<GetStudentResponse> response = (JAXBElement<GetStudentResponse>) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:7070/spring4soap-1/soapws", oFactory.createGetStudentRequest());

		if (response != null) {
			return response.getValue();
		} else {
			return null;
		}
	}

}
