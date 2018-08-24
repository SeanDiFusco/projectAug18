package org.project.zuulserver.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


public class CorrelationIdContextInterceptor implements ClientHttpRequestInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(CorrelationIdContextInterceptor.class);
	
	@Override
	public ClientHttpResponse intercept(
			HttpRequest request, byte[] body, ClientHttpRequestExecution execution
			) throws IOException{
		HttpHeaders headers = request.getHeaders();
		
		//Uses the ThreadLocal UserContextHolder to access the correlationId and add it to the current header response
		headers.add(CorrelationIdContext.CORRELATION_ID, CorrelationIdContextHolder.getContext().getCorrelationId());
	
		logger.debug("CorrelationIdContextInterceptor has added correlationId to headers");
		return execution.execute(request, body);
	}
	
}
