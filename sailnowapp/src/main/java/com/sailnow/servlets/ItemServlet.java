package com.sailnow.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.ItemService;
import com.sailnow.models.ItemDetails;
import com.sailnow.models.SaleHistory;
import com.sailnow.models.SaleItem;
import com.sailnow.models.User;

/**
 * Servlet implementation class ItemServlet
 */
@MultipartConfig
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String image;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session == null)
		{
			JsonObject json = new JsonObject();
			json.addProperty("Error", "No Valid Session");
		}
		
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String action = request.getParameter("action");
		
		String output = null;
		
		if(action.equals("createPackage"))
		{
			output = processCreatePackage(request,response);
		}else if(action.equals("listUserPackages"))
		{
			output = processListUserPackages(request,response);
		}else if(action.equals("listUserPackageHistory"))
		{
			output = processlistUserPackageHistory(request,response);
		}else if(action.equals("purchaseItem"))
		{
			output = processpurchaseItem(request,response);
		}else if (action.equals("createUser"))
		{
			output = processCreateUser(request.getParameter("userid"));
		}else if(action.equals("activeUser"))
		{
			output = proccessActiveUser(request);
		}else if(action.equals("getAllItems"))
		{
			output = getAllItems(request);
		}else if(action.equals("getItemById"))
		{
			output = getItemById(request);
		}else if (action.equals("deleteItem"))
		{
			output=deleteItem(request);
		}
		else
		{
			output = "Unrecognized action";
		}
				
		response.getWriter().println(output);
		
	}

	private String deleteItem(HttpServletRequest request) {
		
		String itemid = request.getParameter("itemid");
		
		ItemService item = ManagerFactory.getItemService();
		
		if(item.findSaleItem(itemid)==null)
		{
			return "Item Doesn't exist";
		}
		
		item.removeSaleItem(itemid);
		return "Item deleted";
	}

	private String getItemById(HttpServletRequest request) {
		
		String itemId = request.getParameter("itemid");
		
		if(itemId ==null || itemId.equals(""))
		{
			return "Missing ItemId Parameter";
		}
		
		ItemService item = ManagerFactory.getItemService();
		
		SaleItem saleItem = item.findSaleItem(itemId);
		
		if(saleItem== null)
		{
			return "Item Doesn't Exist";
		}
		
		List<SaleItem> list = new ArrayList<SaleItem>();
		list.add(saleItem);
		
		return converListToJson(list);
	}

	private String getAllItems(HttpServletRequest request) {
		
		ItemService service = ManagerFactory.getItemService();
		
		String output = converListToJson(service.getAllItems());
		
		return output;
	}

	private String proccessActiveUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("User");
		
		if(user == null)
		{
			return"Error: There is not active User";
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("givenName", user.getGiven_name());
		json.addProperty("family_name", user.getFamily_name());

		return json.toString();
	}

	private String processCreateUser(String parameter) {
		ManagerFactory.getUserService().createUser(new User(parameter,parameter,parameter));
		String output = "Success";
		return output;
	}

	private String processpurchaseItem(HttpServletRequest request, HttpServletResponse response) {
		
		ItemService item = ManagerFactory.getItemService();
		String itemid = request.getParameter("itemid");
		User user = (User) request.getSession().getAttribute("User");
		user = ManagerFactory.getUserService().findUser(user.getEmail());
		
		if(item.findSaleItem(itemid).getUser().getEmail().equals(user.getEmail()))
		{
//			Anup - Added item id and changed text			
			return "You can't purchase own Item";
		}
		item.purchaseSaleItem(user, itemid);
//		Anup - Added item id and changed text
		String output = "Thank you for purchasing: " + itemid;
		return output;
		
	}

	private String processlistUserPackageHistory(HttpServletRequest request, HttpServletResponse response) {
		
		//remove this if 
		String userid = request.getParameter("userid");
		User user = null;
		if(userid == null || userid.equals(""))
			user = (User) request.getSession(false).getAttribute("User");
		else
			user = ManagerFactory.getUserService().findUser(userid);
		
		ItemService items = ManagerFactory.getItemService();
		
		List<SaleHistory> list =  items.getUserSaleHistory(user.getEmail());
		
		String output = converHistoryToJson(list);
		
		return output;
	}

	private String processListUserPackages(HttpServletRequest request, HttpServletResponse response) {
		
		//remove this if 
		String userid = request.getParameter("userid");
		User user = null;
		if(userid == null || userid.equals(""))
			user = (User) request.getSession(false).getAttribute("User");
		else
			user = ManagerFactory.getUserService().findUser(userid);
		
		ItemService items = ManagerFactory.getItemService();
		
		List<SaleItem> list =  items.getUserSaleItemList(user.getEmail());
		
		String output = converListToJson(list);
		
		
		
		return output;
	}

	private String converHistoryToJson(List<SaleHistory> list) {
		
		JsonObject json = null;
		JsonArray array = new JsonArray();
		int num = 0;
		for(SaleHistory item : list)
		{
			json = new JsonObject();
			json.addProperty("itemid", item.getItemid());
			json.addProperty("description", item.getItem_details().getDescription());
			json.addProperty("duration", item.getItem_details().getDuraion());
			json.addProperty("price", item.getItem_details().getPrice());
			json.addProperty("image", new String(item.getItem_details().getImage()));
			json.addProperty("fname", item.getSeller_fname());
			json.addProperty("lname", item.getSeller_lname());
			array.add(json);
		}

		return new Gson().toJson(array);
	}

	
	private String converListToJson(List<SaleItem> list) {
		
		JsonObject json = null;
		JsonArray array = new JsonArray();
		for(SaleItem item : list)
		{
			json = new JsonObject();
			json.addProperty("itemid", item.getItemid());
			json.addProperty("description", item.getItem_details().getDescription());
			json.addProperty("duration", item.getItem_details().getDuraion());
			json.addProperty("price", item.getItem_details().getPrice());
			json.addProperty("image", new String(item.getItem_details().getImage()));
			json.addProperty("fname", item.getUser().getGiven_name());
			json.addProperty("lname", item.getUser().getFamily_name());
			array.add(json);

		}
		return new Gson().toJson(array);
	}

	private String processCreatePackage(HttpServletRequest request, HttpServletResponse response) {
		
		
		String itemid = request.getParameter("itemid");
		String description = request.getParameter("description");
		String duration = request.getParameter("duration");
		double price = Double.parseDouble(request.getParameter("price"));
		String image  = request.getParameter("file");
		ItemService item = ManagerFactory.getItemService();
		
		//For Testing purpose pass userid by parameter
		User user =  null;
		String userid = request.getParameter("userid");
		if( userid== null || userid.equals(""))
		{
			user = (User) request.getSession().getAttribute("User");
		}else{
			user = ManagerFactory.getUserService().findUser(request.getParameter("userid"));

		}

		
		if(user == null)
		{
			
		}
		
		item.createSaleItem(user, itemid, new ItemDetails(description,duration,price,image.getBytes()));
		
		String output = "Success";
		return output;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
