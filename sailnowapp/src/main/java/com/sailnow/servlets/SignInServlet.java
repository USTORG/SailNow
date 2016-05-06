package com.sailnow.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.OAuthService;
import com.sailnow.oauth.GoogleProvider;
import com.sailnow.oauth.OAuthProperties;
import com.sailnow.oauth.OAuthServiceBuilder;
import com.sailnow.utils.CachUtil;
import com.sailnow.oauth.OAuthRequest;


/**
 * Servlet implementation class SignInServlet
 */
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CLIENT_ID = "927328338419-nocjn3oj36ji13lh653ca42899p0ghh4.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "ltapa_-eL5sXFt0fbHNirLSk";
    

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
//	      OauthSe service = 
//	      service.apiKey(CLIENT_ID);
//	      service.apiSecret(CLIENT_SECRET);
//	      service.callback("http://localhost:8080/oauth2callback");
//	      service.scope("email%20profile") ;
//	      service = service.build();
//		OAuthProperties prop = new OAuthProperties();
//		OauthRequest req = new OauthRequest(prop.getClientId(),
//				OAuthCodeCallbackHandlerServlet.getOAuthCodeCallbackHandlerUrl(request),GoogleApi.oauthEndpoint(),
//				prop.getScopesAsString());
		
		if(CachUtil.size() == 0)
		{
			CachUtil.putInCache("requestUrl", "/sailnowapp/protected/user.html");
		}
		
		OAuthService service = new OAuthServiceBuilder().provider(GoogleProvider.class).build(new OAuthProperties());
		String redirectUri = service.getAuthorizationRequestUrl(OAuthCodeCallbackHandlerServlet.getOAuthCodeCallbackHandlerUrl(request));

	      response.sendRedirect(redirectUri);
		
	}

}
