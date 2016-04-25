package com.sailnow.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sailnow.utils.CachUtil;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        this.context.log("Requested Resource::"+uri);
        
        HttpSession session = req.getSession(false);
       
//        if(session == null && !uri.endsWith("/sailnowapp/index.html") && !uri.endsWith("/sailnowapp/SignIn") && !uri.endsWith("/sailnowapp/oauth2callback"))
//        {
         if(session == null && uri.endsWith("html") && !uri.endsWith("index.html")){
            this.context.log("Unauthorized access request");
            CachUtil.putInCache("requestUrl",uri);
            res.sendRedirect("/sailnowapp/index.html");
        }else{
        	 System.out.println("Found session id= "+session.getId());
            chain.doFilter(req, res);
        }

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
	}

}
