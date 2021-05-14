package com;
import java.sql.*;
public class FeedBack {
	
	//A common method to connect to the DB
	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	//Provide the correct details: DBServer/DBName, username, password
	
	con = DriverManager.getConnection("jdbc:mysql://localhost/usermanagement?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}
	//insert
	public String insertFeedBack(String email, String msg)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// create a prepared statement
			String query = " insert into fbt (`Email`,`Message`)"+ "values (?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, email);
			preparedStmt.setString(2, msg);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newFeeDBack = readItems();
			output = "{\"status\":\"success\", \"data\": \"" +newFeeDBack + "\"}";
			//output = "Sent successfully";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while creating feedback.\"}";
				System.err.println(e.getMessage());
			}
				return output;
			}
			//read
			public String readItems()
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
					output = "<table border='1'><tr> <th>Email</th><th>Message</th></tr>";
					String query = "select * from fbt";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
					while (rs.next())
					{
						String Email = rs.getString("Email");
						String Message = rs.getString("Message");

						String msg = null;
				// Add into the html table
						output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + Email + "' </td>";
						output += "<tr><td>" + Email + "</td>";
						output += "<td>" + Message + "</td>";

// buttons
//output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>" +"<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='Email' type='hidden' value='" + Email + "'>" + "</form></td></tr>";
					}
					con.close();
					// Complete the html table
					output += "</table>";
					}
				catch (Exception e)
				{
					output = "Error while reading the feedback.";
					System.err.println(e.getMessage());
				}
				return output;
			}


			public String deleteFeedBack(String email2)
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
					String query = "delete from fbt where Email=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					String Email = null;
			// binding values
					preparedStmt.setString(1,email2);
			// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newFeedback = readItems();
					output = "{\"status\":\"success\", \"data\": \"" + newFeedback  + "\"}";
					//output = "Deleted successfully";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\":\"Error while deleting the feedback.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}
}

