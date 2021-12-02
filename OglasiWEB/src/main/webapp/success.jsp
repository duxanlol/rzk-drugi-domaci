<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
<h3>Uspesno!</h3>
<c:if test="${not empty message }">
	<h4>${message }</h4>
</c:if>
<a href="/OglasiWEB/">
<button> Home</button>
</a>
</body>
</html>