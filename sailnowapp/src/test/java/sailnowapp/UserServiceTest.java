package sailnowapp;

import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.PackageService;
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
		
		suite.addTest(new UserServiceTest("addUser"));
		suite.addTest(new UserServiceTest("deleteUser"));
//		suite.addTest(new UserServiceTest("addPackage"));
//		suite.addTest(new UserServiceTest("removePackage"));
		
		return suite;
	}
	
	public void addUser()
	{
		String email = "Yasinj6@gmail.com";
		UserService user = mgrFactory.getUserService();
		UserModel newUser = null;
		if(user.getUser(email) == null)
		{
			newUser = user.addUser(email);
			assertEquals(newUser.getEmail(),email);
		}
		assertTrue(true);
	}
	
	public void deleteUser()
	{
		String email = "Yasinj6@gmail.com";
		
		UserService user = mgrFactory.getUserService();
		user.deleteUser(email);
		assertNull(user.getUser(email));
	}
	
	public void addPackage()
	{
		String email = "Yasinj6@gmail.com";
		UserService user = mgrFactory.getUserService();
		user.addUser(email);
		
		PackageService packages = mgrFactory.getPackage();
		PackageModel pack = new PackageModel();
		pack.setName("Hawai Package");
		pack.setDescription("This is amazing Package For People who never went to Hawai");
		pack.setDuration("3 weeks");
		pack.setPrice(850);
		PackageService userpack = packages.createPackage(pack);
		
		user.addPackage(userpack.getPackage("Hawai Package"));
		
		assertNotNull(user.getPackages().getPackage("Hawai Package"));
	}
	
	public void removePackage()
	{
		UserService user = mgrFactory.getUserService();
		
		user.removePackage("Hawai Package");
		
		assertNull(user.getPackages().getPackage("Hawai Package"));
	}
}
