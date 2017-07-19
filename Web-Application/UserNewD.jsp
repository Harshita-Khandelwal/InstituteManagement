<%@ page language="java" import="java.util.*"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Add New User</h1>
</tr>
<tr>Select Designation:</tr>
<form method=get action="UserNew.jsp">
<tr><td><input type="radio" name="user1" value="admin" checked> Admin<br/></td></tr>
<tr><td><input type="radio" name="user1" value="manager" > Manager<br/></td></tr>
<tr><td><input type="radio" name="user1" value="accounts_manager">  Accounts Manager<br/></td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
</tr></form>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
