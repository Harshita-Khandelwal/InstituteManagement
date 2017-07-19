<%@ page language="java" import="Database.Db,java.sql.*" %>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<jsp:useBean id="user" class="Database.User" scope="request" />
<jsp:setProperty name="user" property="*" />

<%! String name,add_by,pass,confpass;
	int i;
%>
<%
	name=(String)application.getAttribute("name");
	Database.Db.rs=Database.Db.stmt.executeQuery("select username from login where name=\""+name+"\"");
	Database.Db.rs.next();
	add_by=Database.Db.rs.getString("username");
	user.setAdd_by(add_by);
%>
<% pass=user.getPassword();
	confpass=user.getConfirm_password();
	if(!(pass.equals(confpass)))
	{
%>
<jsp:forward page="UserNew.jsp">
<jsp:param name="PassProb" value="Confirm password doesnt matches password field !!" />
<jsp:param name="user1" value="<%= (String)(application.getAttribute(\"user1\")) %>" />  
</jsp:forward>
<%
	}
%>
<br/><br/><center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<br/><h2>
<%!String u;%>
<%
u=request.getParameter("updateU");
if(u!=null)
{
%>
<% i=user.updateU(); %>
<% 
		if(i==1)
		{
%>Database Successfully Updated!!
<%
		}
		else if(i==0)
		{
%>
<br/>Error Occured....profile Was Not Created!!
<%
		}
}
else
{
%>
	<% i=user.addNew(); %>
<% 
		if(i==1)
		{
%>Database Successfully Updated!!
<% application.removeAttribute("user1");%>
<%
		}
		else if(i==0)
		{
%>
<br/>Error Occured....profile Was Not Created!!
<%
		}
}
%>
<br/><br/>
<a href="UserNewD.jsp">Add New User</a>
<a href="UserSearchOpt.jsp">View User</a></h2>
</div></center>
<%@ include file="footer.html" %>