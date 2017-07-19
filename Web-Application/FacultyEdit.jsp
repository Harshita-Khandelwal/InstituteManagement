<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="fac" class="Database.Faculty" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp"/>
<%!int slabel,cid; 
String cname;%>
<% slabel=Integer.parseInt(request.getParameter("hidden"));%>	
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:auto;background:white ">
<table>
<tr>
<h1>Update Faculty Profile</h1>
</tr>
<%Db.rs=Db.stmt.executeQuery("select * from faculty where faculty_id="+slabel);
Db.rs.next();%>
<h2><form method=post action="CreateFac.jsp">
<tr><td>Faculty ID:F<%= slabel %></td></tr>
<tr><td>Name:</td><td><input type=text name=faculty_name required maxlength="20" value="<%=Db.rs.getString("faculty_name")%>"></td></tr>
<tr><td>Mobile No:</td><td><input type=number name=faculty_mobile_no required min="999999999" max="9999999999" value="<%=Db.rs.getString("faculty_mobile_no")%>"></td></tr>
<tr><td>Address:</td><td><textarea rows="4" cols="30" name="faculty_address" required maxlength="50"> <%=Db.rs.getString("faculty_address")%></textarea></td></tr>
<tr><td>Qualification:</td><td>
<%! String qual;%>
<%qual=Db.rs.getString("fac_qualification");%></font>
<select name="fac_qualification">
<%if(qual.equals("B.Tech"))
	{
	%>
<option value="B.Tech" selected="selected">B.Tech</option>
  <option value="M.Tech">M.Tech</option>
  <option value="Phd">Phd</option>
<%
}
else if(qual.equals("M.Tech"))
{
%>
  <option value="B.Tech">B.Tech</option>
  <option value="M.Tech" selected="selected">M.Tech</option>
  <option value="Phd">Phd</option>
<%
}
else if(qual.equals("Phd"))
{
%>
 <option value="B.Tech">B.Tech</option>
  <option value="M.Tech">M.Tech</option>
  <option value="Phd" selected="selected">Phd</option>
<%
}
%>
</select>
</td></tr>
<%cid=Db.rs.getInt("course_id");%>
<%
 Database.Db.rs=Database.Db.stmt.executeQuery("select course_name from course where course_id="+cid);
	Database.Db.rs.next();
	cname=Db.rs.getString("course_name");%>
<tr>
<td>Course:</td>
<td>
 	<select name="facourse">
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
	</td></tr>
<tr><td><input type=submit name=Submit value=Update></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>

		<input type="hidden" name="faculty_id" value= <%=slabel %> />
<input type="hidden" name="updateF" value="true"/>
	</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>