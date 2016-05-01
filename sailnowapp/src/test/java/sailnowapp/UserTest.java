package sailnowapp;

import com.sailnow.core.UserServiceImpl;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.User;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserTest extends TestCase {
	
	UserService usservice = null;
	
	public UserTest(String name) {
		super(name);
		 usservice = new UserServiceImpl();
	}

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new UserTest("createUser"));
		suite.addTest(new UserTest("updateUser"));
		suite.addTest(new UserTest("removeUser"));
		return suite;
	}
	
	public void createUser()
	{
		String userid = "Yasinj6@gmail.com";
		usservice.createUser(new User(userid,"Yasin","Jama"));
		
		assertNotNull(usservice.findUser(userid));
	}
	
	public void updateUser()
	{
		String userid = "Yasinj6@gmail.com";
		String lastname = "Mohamed";
		
//		User user = usservice.findUser(userid);
		User user = new User(userid,"Yasin",lastname);
		usservice.updateUser(user);
		
		
		assertEquals(usservice.findUser(userid).getFamily_name(),lastname);
	}
	
	public void removeUser()
	{
		String userid = "Yasinj6@gmail.com";
		
		usservice.removeUser(userid);
		
		assertNull(usservice.findUser(userid));

	}
}
