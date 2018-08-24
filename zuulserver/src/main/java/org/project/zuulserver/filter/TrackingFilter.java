package org.project.zuulserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TrackingFilter extends ZuulFilter{
	
	@Autowired
	private FilterUtils filterUtils;
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}
	@Override
	public Object run() {
		if(isCorrelationIdPresent()) {
			logger.debug(filterUtils.CORRELATION_ID + " found in tracking filter is : {}", filterUtils.getCorrelationId());
		} else {
			filterUtils.setCorrelationId(generateCorrelationId());
			logger.debug(filterUtils.CORRELATION_ID + " generated in tracking filter is : {}", filterUtils.getCorrelationId());
		}
		
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.debug("Processing incoming request originating from : {}", ctx.getRequest().getRequestURI());
	
		return null;
	}
	@Override
	public String filterType() {
		return FilterUtils.PRE_FILTER_TYPE;
	}
	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}
	
	//Uses FilterUtils instance to check if project-correlation-id present
	private boolean isCorrelationIdPresent() {
		if(filterUtils.getCorrelationId() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public String generateCorrelationId() {
		return java.util.UUID.randomUUID().toString();
	}

}
