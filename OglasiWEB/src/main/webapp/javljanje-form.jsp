<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Javi se </title>
</head>
<body>


	<c:if test="${not empty oglasi }">
	<form action="/OglasiWEB/PrijavaServlet" method="post" >
		NAS'O ${oglasi.size() } OGLASA
		<table border=5px>
		<tr>
			<th> Korisnik </th>
			<th> Broj Pregleda</th>
			<th> Tekst </th>
			<th> Odaberi </th>
		</tr>
		
		
				
			<c:forEach var="oglas" items="${oglasi}">
				<tr>					
					<td> ${oglas.oglasKorisnik.nickname } </td>
					<td> ${oglas.brojPregleda} </td>
					<td> ${oglas.text} </td>
					<td><input type="radio" name="idOglas" value="${oglas.idOglas}">
					</td>
				</tr>
			</c:forEach>
			</table>
	
		
		<input type="text" name="text">
		<input type="Submit" value="Javi se." >
		</form>	
	</c:if>
	
		
		
		<a href="/OglasiWEB/">
<button> Home</button>
</a>
		
</body>
</html>