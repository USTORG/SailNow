package com.sailnow.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sailnow.core.ManagerFactory;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;

/**
 * Servlet implementation class UserService
 */
public class UserServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServiceServlet() {
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
			JsonObject jsonResponse = new JsonObject();
			jsonResponse.addProperty("Error", "No Valid Session");
			
			response.getWriter().println(jsonResponse.toString());
			return;
		}
		
		process(request,response);
		
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String action = request.getParameter("action");

		if (action.equals("findUser")) {
			String ouput = proccessFindUser(request, response);

			response.getWriter().print(ouput);
			return;
		} else if (action.equals("activeUser")) {
			
			String output = processActiveUser(request,response);
			response.getWriter().println(output);
		}
	}
	private String processActiveUser(HttpServletRequest request, HttpServletResponse response) {
		
		UserModel user = (UserModel) request.getSession(false).getAttribute("User");

		if (user == null) {
			JsonObject json = new JsonObject();
			json.addProperty("Error", "No Active User");
			return json.toString();
		}

		String jsonResponse = new Gson().toJson(user);
		return jsonResponse;
	}

	private String proccessFindUser(HttpServletRequest request, HttpServletResponse response) {
		
		String email = request.getParameter("email");
		
		UserService service = ManagerFactory.getUserService();
		
		UserModel user = service.findUser(email);
		
		if(user == null)
		{
			return "User "+email+" Not Found";
		}

		JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("email", user.getEmail());
		jsonResponse.addProperty("given_name", user.getGiven_name());
		jsonResponse.addProperty("famil_name", user.getFamily_name());
		JsonArray list = getUserPackages(user);
		jsonResponse.addProperty("packages", new Gson().toJson(list));

		
		return jsonResponse.toString();
	}

	private JsonArray getUserPackages(UserModel user) {
		
		JsonObject json = null;
		JsonArray array = new JsonArray();
		for(PackageModel pack: user.getSellpackages())
		{
			json = new JsonObject();
			json.addProperty("name", pack.getName());
			json.addProperty("description", pack.getDescription());
			json.addProperty("duration", pack.getDuration());
			json.addProperty("price", pack.getPrice());
			array.add(json);
		}
		return array;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
