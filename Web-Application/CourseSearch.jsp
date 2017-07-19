<%@ page language="java" import="Database.Db,java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:300px;background:white ">
<%! String s;%>
<% 
s=request.getParameter("group1");
application.setAttribute("group1",s);
%>
<center><h1>View/Edit Courses</h1></center>

<form method="get" action="CourseView.jsp">
<h2>Search By <%= s %></h2>
<table>

<tr>
<td>
<%! String g;%>
<%
	g=request.getParameter("IdProb");
	if(g!=null)
	{
%>
<font color=red><%= g %></font>
<%
	}
%>
<%if(s.equals("id"))
	{%>
	Enter id:
	<input type="text" name="id">
	<%
	}
	else if(s.equals("name"))
	{%>
	Enter Name:
	<input type="text" name="name">
	<%
	}
	else if(s.equals("all"))
	{
%>
	<jsp:forward page="CourseView.jsp">
				<jsp:param name="hidden" value="<%= s %>" /> 
	</jsp:forward>
<%	}
	%>
</td>
</tr>
<tr><td><input type="submit" name="submit" value="Submit"></td></tr>
</table>
<tr><td><input type="hidden" name="hidden" value=<%= s %>></td></tr>
</table>
</form>
</div>
</center><br/>
<%@ include file="footer.html" %>
