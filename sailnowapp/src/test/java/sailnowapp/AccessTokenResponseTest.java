package sailnowapp;

import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.OAuthTokenDao;
import com.sailnow.models.UserModel;
import com.sailnow.oauth.AccessTokenResponse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AccessTokenResponseTest extends TestCase {

	public AccessTokenResponseTest(String name) {
		super(name);
	}

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new AccessTokenResponseTest("saveKey"));
		
		return suite;
	}
	
	public void saveKey()
	{
	    OAuthTokenDao oauthTokenDao = ManagerFactory.getOAuthTokenDao();
	    AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
	    
	    UserModel user = new UserModel();
	    user.setEmail("email.com");
	    accessTokenResponse.setAccessToken("accessToken123");
	    accessTokenResponse.setExpiresIn(123L);
	    accessTokenResponse.setRefreshToken("refresh123");
	    accessTokenResponse.setUsers(user);
	    user.setAccesstoken(accessTokenResponse);
	    ManagerFactory.getUserService().createUser(user);
//	    oauthTokenDao.saveKeys(accessTokenResponse, user.getEmail());
	    
//	    oauthTokenDao.getKey("email")
	}
}
