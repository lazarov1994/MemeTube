package com.concretepage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.concretepage.soap.GetMemeCategoryOfTheDayRequest;
import com.concretepage.soap.GetMemeCategoryOfTheDayResponse;

@Endpoint
public class MemeEndpoint {
	private static final String NAMESPACE_URI = "http://concretepage.com/soap";
	@Autowired
	private MemeCategoryUtility memeUtility;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMemeCategoryOfTheDayRequest")
	@ResponsePayload
	public GetMemeCategoryOfTheDayResponse getCountry(@RequestPayload GetMemeCategoryOfTheDayRequest request) {
		GetMemeCategoryOfTheDayResponse response = new GetMemeCategoryOfTheDayResponse();
		response.setCategory(memeUtility.getCategoryByDay(request.getDayOfWeek()));
		return response;
	}
}
