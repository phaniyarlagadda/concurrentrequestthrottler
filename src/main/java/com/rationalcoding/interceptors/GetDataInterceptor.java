package com.rationalcoding.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rationalcoding.controllers.GetMovieRatings;
import com.rationalcoding.customdatastractures.ConcurrentRequestLimiter;

@Component
public class GetDataInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(GetMovieRatings.class);
	private ConcurrentRequestLimiter concurrentRequestLimiter = new ConcurrentRequestLimiter(5);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String userName = request.getParameter("userName");
		concurrentRequestLimiter.acquire(userName);
		logger.info("Lock acquired successfully for "+userName);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String userName = request.getParameter("userName");
		concurrentRequestLimiter.release(userName);
		logger.info("Lock released successfully for "+userName);
	}

}
