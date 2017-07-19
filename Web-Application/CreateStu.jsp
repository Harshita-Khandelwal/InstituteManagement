<%@ page language="java" import="Database.Db,java.sql.*" %>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<jsp:useBean id="stu" class="Database.Student" scope="request" />
<jsp:setProperty name="stu" property="*" />
<%! int bi;
%>
<%Database.Db.rs=Database.Db.stmt.executeQuery("select batch_id from batch where batch_name=\""+stu.getStbatch()+"\"");
		Database.Db.rs.next();
		bi=Database.Db.rs.getInt("batch_id");
%>
<% stu.setBid(bi);%>

<%! String name,add_by;
	Date dt;
	int i;
%>
<%
	name=(String)application.getAttribute("name");
	Database.Db.rs=Database.Db.stmt.executeQuery("select username from login where name=\""+name+"\"");
	Database.Db.rs.next();
	add_by=Database.Db.rs.getString("username");
	stu.setAdd_by(add_by);
	dt=new Date(System.currentTimeMillis());
	stu.setAdd_date(dt);
%>

<br/><br/><center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<br/><h2>
<%!String u;%>
<%
u=request.getParameter("updateP");
if(u!=null)
{
%>
<% i=stu.updateS(); %>
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
	<% i=stu.addNew(); %>
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
<a href="StNewC.jsp">Add New Student profile</a><br>
<a href="StSearchOpt.jsp">View Profile</a>
</h2>
</div></center>
<%@ include file="footer.html" %>