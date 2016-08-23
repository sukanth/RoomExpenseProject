<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/common.css">
  <script src="js/jquery-1.9.1.js"></script>
  <script src="js/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
	  $("#datepicker").datepicker({ 
		  dateFormat: "yy-mm-dd",
		  maxDate : '+d'
		  }).val()
  });
  </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add expense</title>
</head>
<body bgcolor="#d2ff52" background="img/bg.jpg">
<div align="center">
<font color="red">
			<h1>
				<center>Adding expenses</center>
			</h1>
		</font> 
		<sf:form method="post" modelAttribute="User_bean"
			action="expenseaddition.obj">

			<fieldset>
				<table>
				<tr>
						<td><sf:label path="username">USERNAME :</sf:label></td>
						<td><sf:input path="username" size="20" maxlength="50" readonly="true"/> 
							<sf:errors path="username" cssStyle="color:red"></sf:errors></td>
					</tr>
					<tr>
						<td><sf:label path="name">EXPENSENAME :</sf:label></td>
						<td><sf:input path="name" size="20" maxlength="50" /> <font
							color="red"><small>expensename cannot be blank</small></font> <br>
							<sf:errors path="name" cssStyle="color:red"></sf:errors></td>
					</tr>
					<tr>
						<td><sf:label path="date">EXPENSE DATE :</sf:label></td>
						<td><sf:input path="date" size="20" maxlength="15" id="datepicker" placeholder="MM/DD/YYYY" onkeydown="return false" />
							<font color="red"><small>date cannot be blank</small></font>
							<br> <sf:errors path="date" cssStyle="color:red"></sf:errors></td>
					</tr>
					<tr>
						<td><sf:label path="amount">AMOUNT :</sf:label></td>
						<td><sf:input path="amount" size="20" maxlength="15"/>
							<font color="red"><small>amount cannot be blank</small></font>
							<br> <sf:errors path="date" cssStyle="color:red"></sf:errors></td>
					</tr>
					<tr>
						<td><sf:label path="applicableto">APPLICABLE TO :</sf:label></td>
						<td><sf:checkboxes items="${sessionScope.applicableto}" path="applicableto" id="sessionScope.applicableto"/>
							<font color="red"><small>kindly select at least one</small></font></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							 value="ADD EXPENSE"></td>
					</tr>
				</table>
			</fieldset>

		</sf:form>
		<a href="back.obj"><img alt="" src="img/Back.png" title="Go Back"></a>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>