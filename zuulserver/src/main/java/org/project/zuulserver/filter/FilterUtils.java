package org.project.zuulserver.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;


@Component
public class FilterUtils {

	public static final String CORRELATION_ID = "project-correlation-id";
	
	// String constants for the 3 available types of filters
	public static final String PRE_FILTER_TYPE = "pre";
	public static final String POST_FILTER_TYPE = "post";
	public static final String ROUTE_FILTER_TYPE = "route";
	
	// Get project-correlation-id from original request header
	// If not present in original request header it tries to get project-correlation-id that was added in the TrackingFilter (PreFilter type) stage
	// May return null if problem occurs in TrackingFilter
	public String getCorrelationId() {
		RequestContext ctx = RequestContext.getCurrentContext();
		
		if(ctx.getRequest().getHeader(CORRELATION_ID) != null) {
			return ctx.getRequest().getHeader(CORRELATION_ID) ;
		} else {
			return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
		}
	}
	
	public void setCorrelationId(String correlationId) {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
	}
	
}
