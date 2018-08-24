package org.project.zuulserver.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CorrelationIdFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(CorrelationIdFilter.class);
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		
		CorrelationIdContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(CorrelationIdContext.CORRELATION_ID));
		
		logger.debug("UserContextFilter" + CorrelationIdContext.CORRELATION_ID + " set to: {}", CorrelationIdContextHolder.getContext().getCorrelationId());
		
		filterChain.doFilter(httpServletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
