<%@ page language="java" import="java.util.*,Database.Db"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/>
<center>
<%!int slabel;
String des;%>
<% slabel=Integer.parseInt(request.getParameter("hidden"));%>	

<%Db.rs=Db.stmt.executeQuery("select designation from login where login_id="+slabel);
Db.rs.next();
des=Db.rs.getString("designation");%>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Update User</h1>
</tr>
<tr>Select Designation:</tr>
<form method=get action="UserEdit.jsp">
<%if(des.equals("admin"))
{
%>
<tr><td><input type="radio" name="user1" value="admin" checked> Admin<br/></td></tr>
<tr><td><input type="radio" name="user1" value="manager" > Manager<br/></td></tr>
<tr><td><input type="radio" name="user1" value="accounts_manager">  Accounts Manager<br/></td></tr>
<%
}
else if(des.equals("manager"))
{
%>
<tr><td><input type="radio" name="user1" value="admin" > Admin<br/></td></tr>
<tr><td><input type="radio" name="user1" value="manager" checked> Manager<br/></td></tr>
<tr><td><input type="radio" name="user1" value="accounts_manager">  Accounts Manager<br/></td></tr>
<%
}
else
{
%>
<tr><td><input type="radio" name="user1" value="admin" > Admin<br/></td></tr>
<tr><td><input type="radio" name="user1" value="manager" > Manager<br/></td></tr>
<tr><td><input type="radio" name="user1" value="accounts_manager" checked>  Accounts Manager<br/></td></tr>
<%}%>
<tr><td><input type=submit name=Submit value=Update>
<input type=hidden name=hidden value=<%=slabel%>></td>
</tr></form>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
