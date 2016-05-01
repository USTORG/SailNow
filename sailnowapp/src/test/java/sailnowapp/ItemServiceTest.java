package sailnowapp;

import java.util.List;

import com.sailnow.core.ItemServiceImpl;
import com.sailnow.core.UserServiceImpl;
import com.sailnow.interfaces.ItemService;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.ItemDetails;
import com.sailnow.models.SaleHistory;
import com.sailnow.models.SaleItem;
import com.sailnow.models.User;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ItemServiceTest extends TestCase {

	ItemService service;
	
	public ItemServiceTest(String name) {
		super(name);
		service = new ItemServiceImpl();
	}


	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTest(new ItemServiceTest("createSaleItem"));
		suite.addTest(new ItemServiceTest("getUserSaleItemList"));
		suite.addTest(new ItemServiceTest("purchaseSaleItem"));
		suite.addTest(new ItemServiceTest("getUserSaleHistory"));

		return suite;
	}
	
	public void createSaleItem()
	{
		String userid = "Yasinj6@gmail.com";
		
		UserService us = new UserServiceImpl();
		
		us.createUser(new User(userid,"Yasin","Jama"));
		
		User user = us.findUser(userid);
		
		ItemService sale = new ItemServiceImpl();
		
		sale.createSaleItem(user, "Package1", new ItemDetails("description1"
				,"2weeks",700));
		
				
		assertNotNull(sale.findSaleItem("Package1"));
		
	}
	
	public void getUserSaleItemList()
	{
		String userid = "Yasinj6@gmail.com";

		
		ItemService ser = new ItemServiceImpl();
		UserService us = new UserServiceImpl();
		List<SaleItem> items = ser.getUserSaleItemList(userid);
		
		assertEquals(items.size(),1);
	}
	
	public void purchaseSaleItem()
	{
		String userid = "Yasinj7@gmail.com";

		
		ItemService ser = new ItemServiceImpl();
		UserService us = new UserServiceImpl();
		
		us.createUser(new User(userid,"Yasin","Mohamed"));
		
		User user = us.findUser(userid);
		
		ser.purchaseSaleItem(user, "Package1");
		
		List<SaleHistory> history = ser.getUserSaleHistory(userid);
		
		assertEquals(history.size(),1);
	}
	
	public void getUserSaleHistory()
	{
		ItemService item = new ItemServiceImpl();
		UserService us = new UserServiceImpl();
		String userid = "Yasinj7@gmail.com";
		User user = us.findUser(userid);

		
		List<SaleHistory> userHistory = item.getUserSaleHistory(userid);
		
		assertEquals(userHistory.size(),1);
	}
}
