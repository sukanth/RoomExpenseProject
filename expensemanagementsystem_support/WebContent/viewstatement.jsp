<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/displaytag.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view Statement</title>
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
<body  background="img/bg.jpg">
	<div align="center">
		<h1>
			<font color="red">Statement from 
				'${sessionScope.from}' to '${sessionScope.to}'</font>
		</h1>
		<display:table pagesize="15" name="sessionScope.statementList"
			sort="list" export="true" id="display" defaultsort="1">
			<display:caption> Statement</display:caption>
			<display:setProperty name="export.pdf.filename"
				value="EMS_Statement.pdf"></display:setProperty>
			<display:setProperty name="export.excel.filename"
				value="EMS_Statement.xls"></display:setProperty>
			<display:setProperty name="export.csv.filename"
				value="EMS_Statement.csv"></display:setProperty>
				
			<display:column property="username" title="username"></display:column>
			<display:column property="name" title="Expense name"></display:column>
			<display:column property="expenseDate" title="Expense date"
				headerClass="sortable" sortable="true"></display:column>
			<display:column property="amount" title="amount"
				headerClass="sortable" sortable="true"></display:column>
			<display:column property="applicableto" title="Applicable To"
				headerClass="sortable"></display:column>
			<c:if test="${display.statusCd == 'ACTIVE' }">
				<display:column property="statusCd" title="status"
					headerClass="sortable" sortable="true" class="ACTIVE"></display:column>
			</c:if>
			<c:if test="${display.statusCd == 'INACTIVE' }">
				<display:column property="statusCd" title="status"
					headerClass="sortable" sortable="true" class="INACTIVE"></display:column>
			</c:if>
		</display:table>
		<a href="backtoStatement.obj"><img alt="" src="img/Back.png"
			title="Go Back"></a>
	</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>