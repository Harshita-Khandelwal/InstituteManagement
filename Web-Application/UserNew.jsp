<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="user" class="Database.User" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<%!int slabel; 
	String designation,passprob;
%>
<%Db.rs=Db.stmt.executeQuery("select max(login_id) as highest from login");
			Db.rs.next();
			slabel=(Db.rs.getInt("highest"));
			slabel++;
	%>
<%
	designation=request.getParameter("user1");
application.setAttribute("user1",designation);
%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Add New User</h1>
</tr>


<h2><form method=post action="CreateUser.jsp">
<tr><td>Login ID:<%= slabel %></td></tr>
<tr><td>User Name:</td><td><input type=text name=username required maxlength="10"></td></tr>
<tr><td>Password:</td><td><input type=password name=password required maxlength="10"></td></tr>
<tr><td>Confirm Password:</td><td><input type=password name=confirm_password required maxlength="10"></td>
<%passprob=request.getParameter("PassProb");
	if(passprob!=null)
	{
%>
<td><font color=red><%= passprob%></font></td>
<%
	}
%>
</tr>
<tr><td>Name:</td><td><input type=text name=name required maxlength="20"></td></tr>
<tr><td>Mobile No:</td><td><input type=number name=mobile_no required min="999999999" max="9999999999"></td></tr>
<tr><td>Designation:</td><td><%= designation %></td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>

		<input type="hidden" name="login_id" value= <%=slabel %> />
		<input type="hidden" name="designation" value= <%=designation %> />
	</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>