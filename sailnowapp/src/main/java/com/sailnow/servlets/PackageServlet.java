package com.sailnow.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.sailnow.models.UserModel;

/**
 * Servlet implementation class PackageServlet
 */
public class PackageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackageServlet() {
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
			
		}else if(action.equals("findPackage"))
		{
			output = processFindPackage(request,response);
		}else if(action.equals("getAllPackages"))
		{
			output = processGetAllPackages(request,response);
		}
		else{
			output = proccessNoAction(request,response);
		}
		
		response.getWriter().println(output);
		
		return;
	}

	private String processCreatePackage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String processFindPackage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String processGetAllPackages(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String proccessNoAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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
