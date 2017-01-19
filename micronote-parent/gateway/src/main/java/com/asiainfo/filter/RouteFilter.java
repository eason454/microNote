package com.asiainfo.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class RouteFilter extends ZuulFilter {
	private static final Logger logger=LoggerFactory.getLogger(RouteFilter.class);
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		 RequestContext ctx = RequestContext.getCurrentContext();
		    HttpServletRequest request = ctx.getRequest();
		    logger.error(String.format("%s original request to %s", request.getMethod(), request.getRequestURL().toString()));
		    
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "route";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
