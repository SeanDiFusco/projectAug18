package org.project.zuulserver.util;

import org.springframework.stereotype.Component;

@Component
public class CorrelationIdContext {
	public static final String CORRELATION_ID = "project-correlation-id";
	
	private String correlationId = new String();
	
	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
}
