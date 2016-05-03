package com.sailnow.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		}
		else
		{
			output = "Unrecognized action";
		}
		
		
		
		response.getWriter().println(output);
		
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
		String userid = request.getParameter("userid");
		String itemid = request.getParameter("itemid");
		
		User user = ManagerFactory.getUserService().findUser(userid);
		item.purchaseSaleItem(user, itemid);
		String output = "Success";
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
			array.add(json);
		}

		return new Gson().toJson(array);
	}

    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }
	
	private String converListToJson(List<SaleItem> list) {
		
		JsonObject json = null;
		JsonArray array = new JsonArray();
		int num = 0;
		for(SaleItem item : list)
		{
			json = new JsonObject();
			json.addProperty("itemid", item.getItemid());
			json.addProperty("description", item.getItem_details().getDescription());
			json.addProperty("duration", item.getItem_details().getDuraion());
			json.addProperty("price", item.getItem_details().getPrice());
			array.add(json);
			
			try {
				saveBytesToFile("/Users/yasinjama/temp/imageoutput/image"+num+".jpg",item.getItem_details().getImage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;

		}
		return new Gson().toJson(array);
	}

	private String processCreatePackage(HttpServletRequest request, HttpServletResponse response) {
		
		
		String itemid = request.getParameter("itemid");
		String description = request.getParameter("description");
		String duration = request.getParameter("duration");
		double price = Double.parseDouble(request.getParameter("price"));
		byte [] image = getImage(request);
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
		
		item.createSaleItem(user, itemid, new ItemDetails(description,duration,price,image));
		
		String output = "Success";
		return output;
	}

	private byte [] getImage(HttpServletRequest request) {
		
		byte [] bytes = new byte[1024];
		InputStream filecontent = null;
		OutputStream out = null;
	     try {
			Part filePart = request.getPart("file");
			 filecontent = filePart.getInputStream();
			 final String fileName = getFileName(filePart);
			 
			 filecontent.read(bytes);
//			 File fil =  new File("/Users/yasinjama/temp/"+fileName);
//			out = new FileOutputStream(fil);
//			
//			int read = 0;
//			
//			while ((read = filecontent.read(bytes)) != -1) {
//	            out.write(bytes, 0, read);
//	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     finally {
	         if (out != null) {
	             try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	         if (filecontent != null) {
	             try {
					filecontent.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	     }
	    return bytes;
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
