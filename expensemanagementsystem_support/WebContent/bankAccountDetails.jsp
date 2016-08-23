<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/bankAccountDetails.js"></script>
<title>Bank Account Details</title>
<style type="text/css">
.ACTIVE {
	color: green;
	font-weight: bold;
}

.INACTIVE {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body bgcolor="#d2ff52" background="img/bg.jpg">
	<div align="center">
		<h1>
			<font color="red">Bank Account Details</font>
		</h1>
		<hr>
		<display:table name="sessionScope.accountDetails" pagesize="6" sort="list"
			export="false" id="display">
			<display:column property="username" title="User Name"
				headerClass="sortable" sortable="true"></display:column>
			<display:column  title="Account Details"   headerClass="sortable" >
    			<textarea  name="accDetails" rows="5" disabled="disabled" cols=80 >${display.BankAccount}</textarea>
			</display:column>
			<display:column title="Edit/Update">
			</display:column>
		</display:table>
		<a href="back.obj"><img alt="" src="img/Back.png"
			title="Go Back"></a>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>