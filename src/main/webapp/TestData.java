package main.webapp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.reportz.json.JSONObject;

@Path("/db")
public class TestData {


	@POST
	@Path("/testData")
	@Produces("text/json")
	public Response process(@FormParam("json") String pjson) {

		System.out.println("JSON Received is : "+pjson);
		
		ResponseBuilder response = null;
		if( testConnection(pjson))
		{
			response = Response.ok();
		}else {
			response = Response.serverError();
		}
		return response.build();

	}
	public boolean testConnection(String pjson){
		JSONObject jobj = new JSONObject(pjson);
		String hostName = jobj.getString("hostName");
		String userName = jobj.getString("userName");
		String password = jobj.getString("password");
		String defaultSchema = jobj.getString("defaultSchema");
		String url = "jdbc:mysql://"+hostName+":3306/"+defaultSchema;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return false;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager
					.getConnection(url,userName, password);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return false;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			return true;
		} else {
			System.out.println("Failed to make connection!");
			return false;
		}

	}

}