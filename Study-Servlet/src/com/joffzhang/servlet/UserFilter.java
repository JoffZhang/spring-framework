package com.joffzhang.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zy
 * @date 2020/12/9 15:07
 */
public class UserFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("UserFilter...init...");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		//过滤请求
		System.out.println("UserFilter...doFilter...");
		//放行
		filterChain.doFilter(servletRequest,servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println("UserFilter...destroy...");
	}
}
