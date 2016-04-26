package sailnowapp;

import java.util.List;

import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.PackageService;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PackageServiceTest extends TestCase {

	private ManagerFactory mgr;
	
	public PackageServiceTest(String name) {
		super(name);
		mgr = new ManagerFactory();
	}

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new PackageServiceTest("createPackage"));
		suite.addTest(new PackageServiceTest("findPackage"));
		suite.addTest(new PackageServiceTest("listAllPackages"));
		suite.addTest(new PackageServiceTest("removePackage"));

		return suite;
	}
	
	public void createPackage()
	{
		String userid = "user1@gmail.com";
		
		UserService userservice = mgr.getUserService();
		UserModel user = userservice.createUser(userid);
		
		PackageService pkservice = mgr.getPackageService();
		PackageModel pkg = new PackageModel("Hawai Package",
					"Hawai Package Description",
					"3 weeks",
					700,user);
		PackageModel pkg2 = new PackageModel("Hawai Package2",
				"Hawai Package Description",
				"3 weeks",
				700,user);
		pkservice.createPackage(pkg);
		pkservice.createPackage(pkg2);
		assertEquals(userservice.findUser(userid).getSellpackages().size(), 2);
	}
	
	public void findPackage()
	{
		String pkName = "Hawai Package";
		
		PackageService pkservice = mgr.getPackageService();
		PackageModel pk = pkservice.findPackage(pkName);
		
		assertEquals(pk.getName(), pkName);
	}
	
	public void listAllPackages()
	{
		PackageService pkservice = mgr.getPackageService();
		List<PackageModel> list = pkservice.listAllPackages();
		
		assertFalse("List should have something", list.size() < 0);
	}
	
	public void removePackage()
	{
		String pkName = "Hawai Package2";
		
		PackageService pkservice = mgr.getPackageService();
		pkservice.removePackage(pkName);
		
		assertEquals(pkservice.listAllPackages().size(), 1);
	}
}
