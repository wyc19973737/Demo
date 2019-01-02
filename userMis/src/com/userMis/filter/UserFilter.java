package com.userMis.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest hRequest = (HttpServletRequest) request;
	    Object user = hRequest.getSession().getAttribute("user");
	    if (user == null) {
	        request.getRequestDispatcher("/web/login.jsp").forward(request, response);
	    } else {
	        chain.doFilter(request, response);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
