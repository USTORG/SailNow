package com.sailnow.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.PackageService;
import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;

/**
 * Servlet implementation class SampleServlet
 */
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		ManagerFactory mgr = new ManagerFactory();
		PrintWriter writer = response.getWriter();

		if(action.equals("createPackage"))
		{

			String name = request.getParameter("name");
			PackageService pkservice = mgr.getPackageService();
			PackageModel pkmodel = pkservice.findPackage(name);
			if(pkmodel!=null)
			{
				writer.println("Package "+name+" already exist");
				return;
			}			String description = request.getParameter("description");
			String duration = request.getParameter("duration");
			double price = Double.parseDouble(request.getParameter("price"));
			
			String userid = request.getParameter("userid");

			UserModel user = mgr.getUserService().findUser(userid);
			if(user == null)
			{
				writer.println("User "+userid+" not found");
				return;
			}

			pkservice.createPackage(new PackageModel(name,description,duration,price,user));
			writer.print("Package "+name+" successfully created");
		}else if(action.equals("findPackage"))
		{
			String name = request.getParameter("name");
			PackageModel pkmodel = mgr.getPackageService().findPackage(name);
			if(pkmodel == null)
			{
				writer.print("Unable to find Package "+name);
				return;
			}
			String output = outPutJson(pkmodel);
			
			response.setContentType("application/json");
			writer.print(output);
		}else if(action.equals("createUser"))
		{
			String email = request.getParameter("userid");
			UserModel user = mgr.getUserService().findUser(email);
			if(user != null)
			{
				writer.println("User "+email+" already exist");
				return;
			}
			mgr.getUserService().createUser(email);
			writer.write("Successfully created "+mgr.getUserService().findUser(email).getEmail());
//			writer.println("Successfully created "+mgr.getUserService().findUser(email).getEmail());
		}else if(action.equals("listAllPackages"))
		{
			JSONObject json = new JSONObject();
			List<PackageModel> list = mgr.getPackageService().listAllPackages();
			json.put("Packages", list);
			writer.println(json.toString());
		}
			
		
	}

	private String outPutJson(PackageModel pkmodel) {
		JSONObject json = new JSONObject();
		json.put("name", pkmodel.getName());
		json.put("description", pkmodel.getDescription());
		json.put("duration", pkmodel.getDuration());
		json.put("price", pkmodel.getPrice());
		json.put("userid", pkmodel.getSeller().getEmail());
		return json.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
