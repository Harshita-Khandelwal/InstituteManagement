<%@ page language="java" import="Database.Db,java.sql.*" %>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<jsp:useBean id="query" class="Database.Query" scope="request" />
<jsp:setProperty name="query" property="*" />

<%! String name,add_by,course;
	Date dt;
	int i,cid;
%>
<%
	name=(String)application.getAttribute("name");
	Database.Db.rs=Database.Db.stmt.executeQuery("select username from login where name=\""+name+"\"");
	Database.Db.rs.next();
	add_by=Database.Db.rs.getString("username");
	query.setAdd_by(add_by);
	dt=new Date(System.currentTimeMillis());
	query.setAdd_date(dt);
%>
<%
	course=request.getParameter("qcourse");
	Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+course+"\"");
	Db.rs.next();
	cid=Db.rs.getInt("course_id");
	query.setCourse_id(cid);
%>
<br/><br/><center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<br/><h2>
<%!String u;%>
<%
u=request.getParameter("updateQ");
if(u!=null)
{
%>
<% i=query.updateQ(); %>
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
	<% i=query.addNew(); %>
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
<a href="QueryNew.jsp">Add New Query</a>
<a href="QuSearchOpt.jsp">View Queries</a></h2>
</div></center>
<%@ include file="footer.html" %>