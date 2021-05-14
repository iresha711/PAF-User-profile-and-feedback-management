<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-8">
		<h1 class="m-3">Feedback</h1>
	<form id="formFeedBack" name="formFeedBack" method="post" action="feedback.jsp">
	Email:
	<input id="Email" name="Email" type="text" class="form-control form-control-sm">
	<br> Message:
	<input id="Message" name="Message" type="text" class="form-control form-control-sm">
	
	<br>
	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
				
		
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">
	<%
		FeedBack feedbackobj = new FeedBack();
		out.print(feedbackobj.readItems());
	%>
</div>
</div></div></div>

</body>
</html>