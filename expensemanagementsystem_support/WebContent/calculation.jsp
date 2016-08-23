<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/calculation.js"></script>
<title>Calculations Page</title>
</head>
<body bgcolor="#d2ff52" background="img/bg.jpg">
	<%
		java.text.DateFormat df = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
	%>
	<div align="center">
		<h1>
			<font color="red">${username}'s Calculations till <%=df.format(new java.util.Date())%></font>
		</h1>
		<hr>
		<br> <br>	
		<table border="0" align="center">
			<tr>
				<c:if test="${username != 'vamsi'}">
					<td>
						
								<c:if test="${sessionScope.FinalResult.vamsi < 0}">
										<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GIVE</font>
										<font color="red" size="5">${sessionScope.FinalResult.vamsi}</font>						
										<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-TO VAMSI</font>
								</c:if> 
								<c:if test="${sessionScope.FinalResult.vamsi > 0}">
										<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GET</font>
										<font color="Green" style="font-size: medium; font-size: 25px; font-style: normal;">${sessionScope.FinalResult.vamsi}</font>
										<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-FROM VAMSI</font>
								</c:if> 
					</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${username != 'uday'}">
					<td>
						<c:if test="${sessionScope.FinalResult.uday < 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GIVE </font>
								<font color="red" size="5">${sessionScope.FinalResult.uday}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-TO UDAY</font>
						</c:if>
						<c:if test="${sessionScope.FinalResult.uday > 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GET </font>
								<font color="Green" size="5">${sessionScope.FinalResult.uday}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-FROM UDAY</font>
						</c:if>
					</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${username != 'gunda'}">
					<td>
						<c:if test="${sessionScope.FinalResult.gunda < 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GIVE </font>
								<font color="red" size="5">${sessionScope.FinalResult.gunda}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-TO GUNDA</font>
						</c:if>
						<c:if test="${sessionScope.FinalResult.gunda > 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GET </font>
								<font color="Green" size="5">${sessionScope.FinalResult.gunda}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-FROM GUNDA</font>
						</c:if>
					</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${username != 'zubair'}">
					<td>
						<c:if test="${sessionScope.FinalResult.zubair < 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GIVE </font>
								<font color="red" size="5">${sessionScope.FinalResult.zubair}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-TO ZUBAIR</font>
						</c:if>
						<c:if test="${sessionScope.FinalResult.zubair > 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GET </font>
								<font color="Green" size="5">${sessionScope.FinalResult.zubair}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-FROM ZUBAIR</font>
						</c:if>
					</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${username != 'murali'}">
					<td>
						<c:if test="${sessionScope.FinalResult.murali < 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GIVE </font>
								<font color="red" size="5">${sessionScope.FinalResult.murali}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-TO MURALI</font>
						</c:if>
						<c:if test="${sessionScope.FinalResult.murali > 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GET </font>
								<font color="Green" size="5">${sessionScope.FinalResult.murali}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-FROM MURALI</font>
						</c:if>
					</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${username != 'sudheer'}">
					<td>
						<c:if test="${sessionScope.FinalResult.sudheer < 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GIVE </font>
								<font color="red" size="5">${sessionScope.FinalResult.sudheer}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-TO SUDHEER</font>
						</c:if>
						<c:if test="${sessionScope.FinalResult.sudheer > 0}">
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">YOU HAVE TO GET </font>
								<font color="Green" size="5">${sessionScope.FinalResult.sudheer}</font>
								<font color="black" style="font-size: medium; font-size: 25px; font-style: normal;">RS/-FROM SUDHEER</font>
						</c:if>
					</td>
				</c:if>
			</tr>
		</table>
	</div>
	<div align="center">
		<table>
			<th><a href="back.obj"><img alt="" src="img/Back.png"
					title="Go Back"></a></th>
			<th><a href="#" onclick="Print()"><img alt=""
					src="img/Print.png" title="Print This Page"> </a></th>
		</table>
	</div>

</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>