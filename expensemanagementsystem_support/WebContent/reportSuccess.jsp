<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DeletePage</title>
</head>
<body bgcolor="#d2ff52" background="img/bg.jpg">
	<div align="center">
		<h1><font color="red">hi admin reports generated  successfully </font></h1>
	Reports Created under path	<a title="${filePath}" href="${filePath}">${filePath}</a><br><br>
		<a href="goback.obj">Back to Admin Home Page</a>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>

