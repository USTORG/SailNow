package com.sailnow.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
			String description = request.getParameter("description");
			String duration = request.getParameter("duration");
			double price = Double.parseDouble(request.getParameter("price"));
			
			String userid = request.getParameter("userid");
			PackageService pkservice = mgr.getPackageService();
			UserModel user = mgr.getUserService().findUser(userid);
			pkservice.createPackage(new PackageModel(name,description,duration,price,user));
			writer.print("Package "+name+" successfully created");
		}else if(action.equals("findPackage"))
		{
			String name = request.getParameter("name");
			PackageModel pkmodel = mgr.getPackageService().findPackage(name);
			
			String output = outPutJson(pkmodel);
			
			response.setContentType("application/json");
			writer.print(output);
		}else if(action.equals("createUser"))
		{
			String email = request.getParameter("userid");
			
			mgr.getUserService().createUser(email);
			
			writer.println("Successfully created "+mgr.getUserService().findUser(email).getEmail());
		}
			
		
	}

	private String outPutJson(PackageModel pkmodel) {
		JSONObject json = new JSONObject();
		json.put("Package", pkmodel);
		
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
