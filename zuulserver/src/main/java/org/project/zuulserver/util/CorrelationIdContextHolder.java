package org.project.zuulserver.util;

import org.springframework.util.Assert;

public class CorrelationIdContextHolder {

	private static final ThreadLocal<CorrelationIdContext> userContext = new ThreadLocal<CorrelationIdContext>();
	
	public static final CorrelationIdContext getContext() {
		CorrelationIdContext context = userContext.get();
		
		if(context==null) {
			context = new CorrelationIdContext();
			userContext.set(context);
		} return userContext.get();
	}
	
	public static final void setContext(CorrelationIdContext context) {
		Assert.notNull(context, "Only non-null UserContext should be passed into setContext");
		userContext.set(context);
	}

}
