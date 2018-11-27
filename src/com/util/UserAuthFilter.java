package com.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//过滤器
public class UserAuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest reque, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)reque;
		//获取请求路径
		String path = request.getContextPath();
		String  url= request.getServletPath();
		if(url.endsWith(".action")){
			Object obj=request.getSession().getAttribute("UserInfo");
			if((obj==null||obj=="")&&!url.endsWith("login.action")&&!url.endsWith("validCode.action")&&!url.endsWith("logout.action")){
				//未登录或登录超时、返回登录页面
				//没有登录
		    	response.setContentType("text/html; charset=UTF-8");
				response.getWriter().println("<script>alert('请登录！');location.href='"+request.getContextPath()+"/pages/login.jsp';</script>");
				return; 
			}else{
				//已登录
			}
		}
		//正常逻辑、过滤器向下走
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
