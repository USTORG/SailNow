package sailnowapp;

import java.util.HashSet;
import java.util.Set;

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
		suite.addTest(new UserServiceTest("buyPackage"));
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
	
	public void buyPackage()
	{
		String seller = "user1@email.com";
		String buyer = "user2@email.com";
		
		UserModel user1 = ManagerFactory.getUserService().findUser(seller);
		PackageModel pkg = new PackageModel("Hawai Package",
				"Hawai Package Description",
				"3 weeks",
				700,user1);
		
		ManagerFactory.getPackageService().createPackage(pkg);
		
		ManagerFactory.getUserService().createUser(buyer);
		
		UserModel user2 = ManagerFactory.getUserService().findUser(buyer);
		Set<PackageModel> buypkg = new HashSet<PackageModel>();
		PackageModel buy = ManagerFactory.getPackageService().findPackage(pkg.getName());
		buypkg.add(buy);
		user2.setBuypackages(buypkg);
		
		ManagerFactory.getUserService().updateUser(user2);
		
		int size = ManagerFactory.getUserService().findUser(buyer).getBuypackages().size();
		
		assertEquals(size, 1);
		
	}
	public void removePackage()
	{
		String user1 = "user1@email.com";
		String user2 = "user2@email.com";
//		mgrFactory.getPackageService().removePackage("Hawai Package");
		mgrFactory.getUserService().removeUser(user1);
		mgrFactory.getUserService().removeUser(user2);

		
		assertNull(mgrFactory.getUserService().findUser(user1));
	}
}
