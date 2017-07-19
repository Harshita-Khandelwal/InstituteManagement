<%@ page language="java" import="Database.Db,java.util.*"%>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<br/><br/>
<center>
<div class="view" align="center" style="color:black;width:800px;height:auto;background:white ">
<center>
<h1>View/Edit Batches</h1></center>
<table border=1 cellpadding="10%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Course Name</th>
	<th>Start Time</th>
	<th>End Time</th>
	<th>Duration</th>
	<th>Start Date</th>
	<th>Faculty ID</th>
</tr>
<%! String s,h,y;
	int cid,sid,i;
	Vector v;
	String cname;
%>

<% s=request.getParameter("hidden");
if(s.equals("course"))
			{
				y=request.getParameter("course");
				Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+y+"\"");
			}
	else if(s.equals("all"))
			{
				Db.rs=Db.stmt.executeQuery("select course_id from course");
			}
%>
<%	
	v=new Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getInt("course_id"));
			}%>
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
<td>C<%=Db.rs.getInt("course_id")%>B<%=Db.rs.getInt("batch_id")%>
</td>
<td><%=Db.rs.getString("batch_name")%></td>
<td><%=cname%></td>
<td><%=Db.rs.getString("batch_start_time")%><%=Db.rs.getString("start_am_pm")%></td>
<td><%=Db.rs.getString("batch_end_time")%><%=Db.rs.getString("end_am_pm")%></td>
<td><%=Db.rs.getString("batch_duration")%></td>
<td><%=Db.rs.getString("batch_start_date")%></td>
<td>F<%=Db.rs.getString("faculty_id")%></td>
<td>
<form method=get action="BatchEdit.jsp">
	<input type=hidden name=course_name value=<%=cname%>>
	<input type=hidden name=course_id value=<%=Db.rs.getInt("course_id")%>>
	<input type=hidden name=batch_id value=<%=Db.rs.getInt("batch_id")%>>
	<input type=submit name=submit value=Update>
</form></td>
</tr>
<%
				}
				i++;
			}
%>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>