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
<center><h1>View/Edit Faculty Profile</h1></center>

<form method="get" action="FacView.jsp">
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
	<jsp:forward page="FacView.jsp">
				<jsp:param name="hidden" value="<%= s %>" /> 
	</jsp:forward>
<%	}
	%>
</td>
</tr>
<%
if(s.equals("batch"))
{
%>
<table border=1 cellpadding="10%">
<tr>
	<th>Course Name</th>
	<th>Batch Name</th>
	<th>Start Time</th>
	<th>Duration</th>
	<th></th>
</tr>
<%! Vector v;
	int i,cid;
	String cname;
%>
<%	
	Db.rs=Db.stmt.executeQuery("select course_id from course");
	v=new Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getInt("course_id"));
			}
%>	
<%
	i=0;
		while(i<v.size())
			{
				cid=(Integer)v.get(i);
				Db.rs=Db.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
				Db.rs.next();
				cname=Db.rs.getString("course_name");
 %>
<%
				Db.rs=Db.stmt.executeQuery("select * from batch where course_id=\""+cid+"\"");
				while(Db.rs.next()==true)
				{
%>
<tr>
<td><%=cname%>
</td>
<td><%=Db.rs.getString("batch_name")%></td>
<td><%=Db.rs.getString("batch_start_time")%><%=Db.rs.getString("start_am_pm")%></td>
<td><%=Db.rs.getString("batch_duration")%></td>
<td><a href="FacView.jsp?hidden=<%=s%>&cid=<%=cid%>&bid=<%=Db.rs.getInt("batch_id")%>">Show Faculty Details</a></td>
</tr>
<%				
				}
				i++;
			}
}
else
{
%>
<tr><td><input type="submit" name="submit" value="Submit"></td></tr>
<%
}
%>
</table>
<tr><td><input type="hidden" name="hidden" value=<%= s %>></td></tr>
</table>
</form>
</div>
</center><br/>
<%@ include file="footer.html" %>
