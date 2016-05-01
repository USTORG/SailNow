package com.sailnow.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.OAuthService;
import com.sailnow.interfaces.OAuthTokenDao;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.User;
import com.sailnow.oauth.AccessTokenResponse;
import com.sailnow.oauth.OAuthProperties;
import com.sailnow.oauth.OAuthServiceBuilder;
import com.sailnow.utils.CachUtil;
import com.sailnow.oauth.OAuthRequest;

/**
 * Servlet implementation class OAuthCodeCallbackHandlerServlet
 */
public class OAuthCodeCallbackHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  /** The name of the Oauth code URL parameter */
	  public static final String CODE_URL_PARAM_NAME = "code";

	  /** The name of the OAuth error URL parameter */
	  public static final String ERROR_URL_PARAM_NAME = "error";

	  /** The URL suffix of the servlet */
	  public static final String URL_MAPPING = "/oauth2callback";
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuthCodeCallbackHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // Getting the "error" URL parameter
	    String[] error = request.getParameterValues(ERROR_URL_PARAM_NAME);

	    // Checking if there was an error such as the user denied access
	    if (error != null && error.length > 0) {
	    	response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "There was an error: \""+error[0]+"\".");
	      return;
	    }
	    // Getting the "code" URL parameter
	    String[] code = request.getParameterValues(CODE_URL_PARAM_NAME);

	    // Checking conditions on the "code" URL parameter
	    if (code == null || code.length == 0) {
	    	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The \"code\" URL parameter is missing");
	      return;
	    }
	    
	    // Construct incoming request URL
	    String requestUrl = getOAuthCodeCallbackHandlerUrl(request);
	    
	    OAuthService service = new OAuthServiceBuilder().provider(GoogleProvider.class).build(new OAuthProperties());
	    // Exchange the code for OAuth tokens
//	    AccessTokenResponse accessTokenResponse = exchangeCodeForAccessAndRefreshTokens(code[0],
//	        requestUrl);
	    AccessTokenResponse accessToken = service.getAccessToken(code[0],requestUrl);
	    
	    //Maybe pressist oauth token
	    
	    //Get user information using access token
	    User user = service.getUser(accessToken);
	    
	    UserService userservice = ManagerFactory.getUserService();
	    
	    if(user == null)
	    {
	    	//handle this case
	    }
	    
	    
	    
	    if(userservice.findUser(user.getEmail()) == null)
	    {
	    	 ManagerFactory.getUserService().createUser(user);
	    }
	    
	   
	    //Create Session 
	    
	    HttpSession session = request.getSession();
	    session.setAttribute("accessToken", accessToken);
	    session.setAttribute("User", user);
	    System.out.println("Create session id= "+session.getId());
	    String url = (String) CachUtil.getFromCache("requestUrl");
	    System.out.println("Redirecting url "+url);
	    response.setHeader("Cache-Control", "no-cache");

	    //Forces caches to obtain a new copy of the page from the origin server
	    response.setHeader("Cache-Control", "no-store");

	    //Directs caches not to store the page under any circumstance
	    response.setDateHeader("Expires", 0);

	    //Causes the proxy cache to see the page as "stale"
	    response.setHeader("Pragma", "no-cache");
	  
	    response.sendRedirect(url);
	    
//	    UserModel user = service.getUser
//	    UserModel user = getCurrentUser(accessTokenResponse);
//	    String email = user.getEmail();
//	    
//	    OAuthTokenDao oauthTokenDao = ManagerFactory.getOAuthTokenDao();
//	    oauthTokenDao.saveKeys(accessTokenResponse, email);
	    
	    //redirect back to request page
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 public static String getOAuthCodeCallbackHandlerUrl(HttpServletRequest req) {
		    String scheme = req.getScheme() + "://";
		    String serverName = req.getServerName();
		    String serverPort = (req.getServerPort() == 80) ? "" : ":" + req.getServerPort();
		    String contextPath = req.getContextPath();
		    String servletPath = URL_MAPPING;
		    String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
		    return scheme + serverName + serverPort + contextPath + servletPath + pathInfo;
		  }
	 
	 
}
