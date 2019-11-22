package com.example.demo.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class IDValidationInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	private final String TARGET_PATH_POST = "/storage/documents";
	private final String TARGET_TO_REPLACE = "/storage/documents/";
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("Before Handler Execution");
        
        //Fetch the URI from the request like /storage/documents or /storage/documents/<doc_id>
        String url_id = request.getRequestURI().toString();
        
        
        return true;
    }
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		log.info("Request is complete");
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		log.info("Handler execution is complete");
	}
	
}
