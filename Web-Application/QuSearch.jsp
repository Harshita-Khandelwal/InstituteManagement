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
<center><h1>View/Edit Queries</h1></center>

<form method="get" action="QuView.jsp">
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
	else if(s.equals("course"))
	{%>
	Select Course:
	<select name="course">
 <%! Vector c; 
	Iterator itr;
%>

 <%	c=new Vector();
	Database.Db.rs=Database.Db.stmt.executeQuery("select course_name from course");
	while(Database.Db.rs.next()==true)
	{
		c.add(Database.Db.rs.getString("course_name"));
	}
	itr=c.iterator();
	while(itr.hasNext())
	{
%>
<option>
<%= itr.next() %>
<%
	}
%>
  </option>

</select>
	<%
	}
	else if(s.equals("all"))
	{
%>
	<jsp:forward page="QuView.jsp">
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
