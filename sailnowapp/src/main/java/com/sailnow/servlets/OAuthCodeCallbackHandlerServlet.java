package com.sailnow.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.OAuthService;
import com.sailnow.interfaces.OAuthTokenDao;
import com.sailnow.models.UserModel;
import com.sailnow.oauth.AccessTokenResponse;
import com.sailnow.oauth.OAuthProperties;
import com.sailnow.oauth.OAuthServiceBuilder;
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
