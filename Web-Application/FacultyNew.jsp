<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="fac" class="Database.Faculty" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<%!int slabel; %>
<%Db.rs=Db.stmt.executeQuery("select max(faculty_id) as highest from faculty");
			Db.rs.next();
			slabel=(Db.rs.getInt("highest"));
			slabel++;
	%>	
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:auto;background:white ">
<table>
<tr>
<h1>Create Faculty Profile</h1>
</tr>
<h2><form method=post action="CreateFac.jsp">
<tr><td>Faculty ID:F<%= slabel %></td></tr>
<tr><td>Name:</td><td><input type=text name=faculty_name required maxlength="20"></td></tr>
<tr><td>Mobile No:</td><td><input type=number name=faculty_mobile_no required min="999999999" max="9999999999"></td></tr>
<tr><td>Address:</td><td><textarea rows="4" cols="30" name="faculty_address" required maxlength="50"></textarea></td></tr>
<tr><td>Qualification:</td><td><select name="fac_qualification">
  <option value="B.Tech">B.Tech</option>
  <option value="M.Tech">M.Tech</option>
  <option value="Phd">Phd</option>
</select> </td></tr>
<tr><td>Course:</td><td> 	<select name="facourse">
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

		<input type="hidden" name="faculty_id" value= <%=slabel %> />
	</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>