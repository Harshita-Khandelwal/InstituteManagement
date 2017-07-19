<%@ page language="java" import="Database.Db,java.sql.*" %>
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" />
<jsp:useBean id="fee" class="Database.Fee" scope="request" />
<jsp:setProperty name="fee" property="*" />
<%! String name,receipt_generated_by;
	java.sql.Date rec_generation_date;
	java.sql.Time rec_generation_time;
	int idep,due,i;
%>
<%
	name=(String)application.getAttribute("name");
	Database.Db.rs=Database.Db.stmt.executeQuery("select username from login where name=\""+name+"\"");
	Database.Db.rs.next();
	receipt_generated_by=Database.Db.rs.getString("username");
	fee.setReceipt_generated_by(receipt_generated_by);
	
	rec_generation_date=new java.sql.Date(System.currentTimeMillis());
	rec_generation_time=new java.sql.Time(System.currentTimeMillis());
	fee.setRec_generation_date(rec_generation_date);
	fee.setRec_generation_time(rec_generation_time);
%>
<%
	idep=Integer.parseInt(request.getParameter("fee_deposit"));
	 due=Integer.parseInt(request.getParameter("due"));
	if(idep>due)
	{
	%>	
<jsp:forward page="FeeNew.jsp">
				<jsp:param name="prob" value="Amount more than due can't be deposited" />
				<jsp:param name="id" value="<%= (String)(application.getAttribute(\"id\")) %>" /> 
</jsp:forward>
<%
	}
%>
<br/><br/><center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<br/><h2>
<% i=fee.addNew(); %>
<% 
		if(i==1)
		{		
		application.removeAttribute("id");
%>
Database Successfully Updated!!
<br/><br/>
<%
		}
		else if(i==0)
		{
%>
<br/>Error Occured....Batch Was Not Created!!
<br/><br/>
<%
		}
%>		
<a href="FeeNewS.jsp">Deposit Fee</a></h2>
</div></center>
<%@ include file="footer.html" %>