package com;
import java.sql.*;
public class User {
	
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
	public String insertUser(String nic, String name, String type, String phone, String email, String address, String history)
	{
	String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
	{return "Error while connecting to the database for inserting."; }
	
	// create a prepared statement
	String query = " insert into usertb(`NIC`,`Name`,`Type`,`Phone`,`Email`,`Address`,`History`)" + " values (?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	// binding values
	preparedStmt.setString(1, nic);
	preparedStmt.setString(2, name);
	preparedStmt.setString(3, type);
	preparedStmt.setInt(4, Integer.parseInt(phone));
	preparedStmt.setString(5, email);
	preparedStmt.setString(6, address);
	preparedStmt.setString(7, history);
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	
	String newUser = readItems();
	output = "{\"status\":\"success\", \"data\": \"" +newUser + "\"}";
	//output = "Inserted successfully";
	}
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while creating user profile.\"}";
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
	output = "<table border='1'><tr><th>NIC</th><th>Name</th>" +
	"<th>Type</th>" +
	"<th>Phone</th>" +"<th>Email</th>" +"<th>Address</th>" +"<th>History</th></tr>";
	String query = "select * from usertb";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	// iterate through the rows in the result set
	while (rs.next())
	{
	String NIC = rs.getString("NIC");
	String Name = rs.getString("Name");
	String Type = rs.getString("Type");
	String Phone = Integer.toString(rs.getInt("Phone"));
	String Email = rs.getString("Email");
	String Address = rs.getString("Address");
	String History = rs.getString("History");
	
	// Add into the html table
	output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + NIC + "' </td>";
	//output += "<tr><td>" + NIC + "</td>";
	output += "<td>" + Name + "</td>";
	output += "<td>" + Type + "</td>";
	output += "<td>" +  Phone + "</td>";
	output += "<td>" +  Email + "</td>";
	output += "<td>" +  Address + "</td>";
	output += "<td>" +  History + "</td>";
	
	// buttons
	//output += "<td><input name='btnUpdate' type='button' value='Update'+ class='btnUpdate btn btn-secondary'></td><td><form method='post' action='user.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='hidItemDelete' type='hidden' value='" + NIC + "'>" + "</form></td></tr>";
	}
	con.close();
	
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
		output = "Error while reading the profiles.";
		System.err.println(e.getMessage());
	}
	return output;
	}
	public String updateUser(String nic, String type, String phone, String email, String address)
	{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		
		String query = "UPDATE usertb SET Type=?,Phone=?,Email=?,Address=? WHERE NIC=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, type);
				preparedStmt.setInt(2, Integer.parseInt(phone));
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, nic);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newUser = readItems();
				output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
				//output = "Updated successfully";
				}
				catch (Exception e)
				{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the profile.\"}";
				System.err.println(e.getMessage());
				}
				return output;
				}
	public String deleteUser(String nic)
	{
		String output = "";
		try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	
	// create a prepared statement
	String query = "delete from usertb where NIC=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	
	String NIC = null;
	// binding values
	preparedStmt.setString(1, nic);
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	
		String newUser = readItems();
		output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
	
	//output = "Deleted successfully";
	}
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\":\"Error while deleting the profile.\"}";
		System.err.println(e.getMessage());
	}
		
	return output;
	}
	
	public String insertUser1(String nIC, String name, String type, String phone, String email, String address, String history) {
		// TODO Auto-generated method stub
		return null;
	}
	/*public String deleteUser(String nIC) {
		// TODO Auto-generated method stub
		return null;
	}*/
	public String updateUser(String nIC) {
		// TODO Auto-generated method stub
		return null;
	}
	public String updateUser(String nIC, String name, String type, String phone, String email, String address,
			String history) {
		// TODO Auto-generated method stub
		return null;
	}
}
