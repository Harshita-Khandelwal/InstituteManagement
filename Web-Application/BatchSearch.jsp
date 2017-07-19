<%@ page language="java" import="Database.Db,java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:300px;background:white ">
<%! String s;%>
<% 
s=request.getParameter("group1");
%>
<center><h1>View/Edit Batches</h1></center>

<form method="get" action="BatchView.jsp">
<h2>Search By <%= s %></h2>
<table>

<tr>
<td>
<% if(s.equals("course"))
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
	<jsp:forward page="BatchView.jsp">
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
