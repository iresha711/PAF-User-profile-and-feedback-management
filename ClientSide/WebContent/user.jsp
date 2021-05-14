<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-8">
		<h1 class="m-3">User details</h1>
	<form id="formUser" name="formUser" method="post" action="user.jsp">
	NIC:
	<input id="NIC" name="NIC" type="text" class="form-control form-control-sm">
	<br> Name:
	<input id="Name" name="Name" type="text" class="form-control form-control-sm">
	<br> Type:
	<input id="Type" name="Type" type="text" class="form-control form-control-sm">
	<br> Phone:
	<input id="Phone" name="Phone" type="text" class="form-control form-control-sm">
	<br> Email:
	<input id="Email" name="Email" type="text" class="form-control form-control-sm">
	<br> Address:
	<input id="Address" name="Address" type="text" class="form-control form-control-sm">
	<br> History :
	<input id="History" name="History" type="text" class="form-control form-control-sm">
	<br>
	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
				
		
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<!--  //Save---------------------------------
if (request.getParameter("NIC") != null)
{
	User userobj = new User();
	String stsMsg = "";
	
	//Insert--------------------------
	if (request.getParameter("hidItemIDSave") == "")
	{
		stsMsg = userobj.insertUser(request.getParameter("NIC"),
		request.getParameter("Name"),
		request.getParameter("Type"),
		request.getParameter("Phone"),
		request.getParameter("Email"),
		request.getParameter("Address"),
		request.getParameter("History"));
	}
	else//Update----------------------
	{
		stsMsg = userobj.updateUser(request.getParameter("hidItemIDSave"),
		request.getParameter("NIC"),
		request.getParameter("Name"),
		request.getParameter("Type"),
		request.getParameter("Phone"),
		request.getParameter("Email"),
		request.getParameter("Address"),
		request.getParameter("History"));
	}
	session.setAttribute("statusMsg", stsMsg);
	}
	//Delete-----------------------------
	if (request.getParameter("hidItemIDDelete") != null)
	{
		User userobj = new User();
		String stsMsg = userobj.deleteUser(request.getParameter("hidItemIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
} -->

<br>
<div id="divItemsGrid">
	<%
		User userobj = new User();
		out.print(userobj.readItems());
	%>
</div>
</div></div></div>
</body>
</html>