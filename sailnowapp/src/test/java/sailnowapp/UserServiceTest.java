package sailnowapp;

import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserServiceTest extends TestCase {

	private ManagerFactory mgrFactory = null;

	
	public UserServiceTest(String testName)
	{
		super(testName);
		mgrFactory = new ManagerFactory();
	}

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		
		suite.addTest(new UserServiceTest("createUser"));
		suite.addTest(new UserServiceTest("findUser"));
//		suite.addTest(new UserServiceTest("updateUser"));
		suite.addTest(new UserServiceTest("removePackage"));

		return suite;
	}
	
	public void createUser()
	{
		UserService userservice = mgrFactory.getUserService();
		UserModel user = userservice.createUser("user1@email.com");
		
		assertNotNull("createUser() user : user1@email.com is created successfully", user);
		
	}

	public void findUser()
	{
		String userid = "user1@email.com";
		UserModel user = mgrFactory.getUserService().findUser(userid);
		
		assertNotNull("findUser() found user object ",user);
		assertEquals("User : "+user.getEmail(), user.getEmail(), userid);
	}
	
//	public void updateUser()
//	{
//		String userid = "user2@email.com";
//		UserService userservice = mgrFactory.getUserService();
//
//		UserModel user = userservice.findUser("user1@email.com");
//		
//
//	}
	
	public void removePackage()
	{
		String userid = "user1@email.com";
		
		mgrFactory.getUserService().removeUser(userid);
		
		assertNull(mgrFactory.getUserService().findUser(userid));
	}
}
