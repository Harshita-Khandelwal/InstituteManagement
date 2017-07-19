<%@ page language="java" import="Database.Db,java.sql.*" %>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<jsp:useBean id="course" class="Database.Course" scope="request" />
<jsp:setProperty name="course" property="*" />

<%! String name,add_by,approx_duration,approx,duration;
int i;
%>
<%
	name=(String)application.getAttribute("name");
	Database.Db.rs=Database.Db.stmt.executeQuery("select username from login where name=\""+name+"\"");
	Database.Db.rs.next();
	add_by=Database.Db.rs.getString("username");
	course.setAdd_by(add_by);
%>
<%
	approx=request.getParameter("approx");
	duration=request.getParameter("duration");
	approx_duration=approx+duration;
	course.setApprox_duration(approx_duration);
%>
<br/><br/><center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<br/><h2>
<%!String u;%>
<%
u=request.getParameter("updateC");
if(u!=null)
{
%>
<% i=course.updateC(); %>
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
<% i=course.addNew(); %>
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
%>
<br/><br/>
<a href="CourseNew.jsp">Add New Course</a><br>
<a href="CourseSearchOpt.jsp">View Course</a></h2>
</div></center>
<%@ include file="footer.html" %>