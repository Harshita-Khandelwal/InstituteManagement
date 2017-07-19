<%@ page language="java" import="java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>View/Edit User Account</h1>
</tr>
<tr>Search Options:</tr>
<form method=get action="UserView.jsp">
<tr><td><input type="radio" name="group1" value="admin">  All Admin</td></tr>
<tr><td><input type="radio" name="group1" value="manager">  All Manager</td></tr>
<tr><td><input type="radio" name="group1" value="account_manager">  All Accounts Manager</td></tr>
<tr><td><input type="radio" name="group1" value="all" checked>  All Entries</td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
</tr></form>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
