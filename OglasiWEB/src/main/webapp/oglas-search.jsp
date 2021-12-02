<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Search</title>
</head>
<body>

<form action="/OglasiWEB/OglasServlet" method="get" id="form1"> 
			<label for="text" >Tekst: </label>
			<input type="text"  id="text" name="text" required>
			<input name="search" type="Submit" value="Search" id="form1">
		</form>
		<br>

		
	


</body>
</html>