<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="query" class="Database.Query" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<%! int slabel,cid;
String cname; 
%>
<% slabel=Integer.parseInt(request.getParameter("hidden"));%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:auto;background:white ">
<table>
<tr>
<h1>Update Query</h1>
</tr>
<%Db.rs=Db.stmt.executeQuery("select * from query where query_id="+slabel);
Db.rs.next();%>
<h2><form method=post action="CreateQuery.jsp">
<tr><td>Query ID:Q<%= slabel %></td></tr>
<tr><td>Name:</td><td><input type=text name=query_name required maxlength="30" value="<%=Db.rs.getString("query_name")%>"></td></tr>
<tr><td>Mobile No:</td><td><input type=number name=query_mobile_no required min="999999999 max="9999999999" value="<%=Db.rs.getString("query_mobile_no")%>"></td></tr>
<tr><td>Father's Name:</td><td><input type=text name=query_father_name required maxlength="30" value="<%=Db.rs.getString("query_father_name")%>"></td></tr>
<tr><td>Father's Mobile No:</td><td><input type=number name=query_father_mobile_no required min="999999999 max="9999999999" value="<%=Db.rs.getString("query_father_mobile_no")%>"></td></tr>
<tr><td>Address:</td><td><textarea rows="4" cols="30" name="query_address" required maxlength="50"><%=Db.rs.getString("query_address")%></textarea></td></tr>
<tr><td>College:</td><td><input type=text name=query_clg required maxlength="20" value="<%=Db.rs.getString("query_clg")%>"></td></tr>
<tr><td>Course:</td><td> <select name="qcourse">

<%cid=Db.rs.getInt("course_id");%>
<%
 Database.Db.rs=Database.Db.stmt.executeQuery("select course_name from course where course_id="+cid);
	Database.Db.rs.next();
	cname=Db.rs.getString("course_name");%>
	
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
		String s=(String)itr.next();
		if(s.equals(cname))
		{
%>
			<option selected="selected">
			<%= s %>
<%
		}
		else
		{
%>
			<option>
			<%= s %>
<%
		}
	}
%>
  </option>

</select>
</td>
</tr>
<tr><td><input type=submit name=Submit value=Update></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>
<input type="hidden" name="query_id" value= <%=slabel %> />
<input type="hidden" name="updateQ" value="true"/>
</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>