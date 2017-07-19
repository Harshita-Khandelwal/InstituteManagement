<%@ page language="java" import="java.util.*,Database.Db"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<%!String sid,c1,cname;
int cid;%>
<%sid=(String)request.getParameter("hidden");%>
<%cid=Integer.parseInt(request.getParameter("hiddenC"));%>
<%
 Database.Db.rs=Database.Db.stmt.executeQuery("select course_name from course where course_id="+cid);
	Database.Db.rs.next();
	cname=Db.rs.getString("course_name");%>
<h1>Update Profile</h1>
</tr><br/>
<h1><form method=get action="StEdit.jsp">
<tr><td>
Student with id S<%=sid%> is pursuing course <%=cname%> </td></tr>
<tr><td>Update Course:</td>
<td>
 <select name="stcourse">
  <%! Vector c; 
	Iterator itr;
%>

 <%	
 c=new Vector();
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
  </option>
<%}%>
</select></td></tr>
<tr>
<td><input type=submit name=Submit value=Update></td>
<input type=hidden name=hidden value=<%=sid%>>
</tr></form></h1>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
