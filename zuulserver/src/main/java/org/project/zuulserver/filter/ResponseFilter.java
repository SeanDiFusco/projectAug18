package org.project.zuulserver.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ResponseFilter extends ZuulFilter {

	@Autowired
	private FilterUtils filterUtils;
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);
	
	@Override
	public String filterType() {
		return FilterUtils.POST_FILTER_TYPE;
	}
	
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}
	
	@Override
	public Object run() {
			//get current request context
			RequestContext ctx = RequestContext.getCurrentContext();
			
			logger.debug("Correlation id being added to the outbound headers : {}",filterUtils.getCorrelationId());
			
			// Get response
			HttpServletResponse response = ctx.getResponse();
			
			// Add header for project-correlation-id to response headers
			response.addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
			
			logger.debug("Outgoing request being completed for : {}", ctx.getRequest().getRequestURI() );
	
			return null;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

}
