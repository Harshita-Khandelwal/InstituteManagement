<%@ page language="java" import="java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Create New Profile</h1>
</tr><br/>
<h1><form method=get action="StNew.jsp">
<tr><td>Select Course:</td>
<td>
 <select name="stcourse">
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

</select> </td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
</tr></form></h1>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
