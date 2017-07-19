<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="query" class="Database.Query" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<%! int slabel; 
	%>
		<%	Db.rs=Db.stmt.executeQuery("select max(query_id) as highest from query");
			Db.rs.next();
			slabel=(Db.rs.getInt("highest"));
			slabel++;
	%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:auto;background:white ">
<table>
<tr>
<h1>Create New Query</h1>
</tr>
<h2><form method=post action="CreateQuery.jsp">
<tr><td>Query ID:Q<%= slabel %></td></tr>
<tr><td>Name:</td><td><input type=text name=query_name required maxlength="30"></td></tr>
<tr><td>Mobile No:</td><td><input type=number name=query_mobile_no required max="9999999999"></td></tr>
<tr><td>Father's Name:</td><td><input type=text name=query_father_name required maxlength="30"></td></tr>
<tr><td>Father's Mobile No:</td><td><input type=number name=query_father_mobile_no required max="9999999999"></td></tr>
<tr><td>Address:</td><td><textarea rows="4" cols="30" name="query_address" required maxlength="50"></textarea></td></tr>
<tr><td>College:</td><td><input type=text name=query_clg required maxlength="20"></td></tr>
<tr><td>Course:</td><td> <select name="qcourse">
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
</td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>
<input type="hidden" name="query_id" value= <%=slabel %> />
</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>