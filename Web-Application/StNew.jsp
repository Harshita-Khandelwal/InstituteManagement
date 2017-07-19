<%@ page language="java" import="Database.Db"%>
<jsp:useBean id="stu" class="Database.Student" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<%! String c; 
	int slabel; 
	int ci;
%>
<% 			
	Db.rs=Db.stmt.executeQuery("select max(stu_id) as highest from student");
	Db.rs.next();
	slabel=(Db.rs.getInt("highest"));
	slabel++;
%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:auto;background:white ">
<table>
<tr>
<h1>Create New Profile</h1>
</tr>
<h2><form method=post action="CreateStu.jsp">
<tr><td>Student ID:S<%= slabel %></td></tr>
<tr><td>Name:</td><td><input type=text name=stname required maxlength="30"></td></tr>
<tr><td>Mobile No:</td><td><input type="number" name=stmobno required min="999999999" max="9999999999"></td></tr>
<tr><td>Father's Name:</td><td><input type=text name=stfname required maxlength="30"></td></tr>
<tr><td>Father's Mobile No:</td><td><input type=number name=stfmobno min="999999999" max="9999999999" required ></td></tr>
<tr><td>Address:</td><td><textarea rows="4" cols="30" name=staddress required maxlength="50"></textarea></td></tr>
<tr><td>College:</td><td><input type=text name=stclg required maxlength="20"></td></tr>
<tr><td>Batch:</td><td> <select name=stbatch >
<%
	c=request.getParameter("stcourse");
	Db.rs=Db.stmt.executeQuery("select course_id from course where course_name=\""+c+"\"");
	Db.rs.next();
	ci=Db.rs.getInt("course_id");
	Db.rs=Db.stmt.executeQuery("select batch_name from batch where course_id=\""+ci+"\"");
	while(Db.rs.next()==true)
	{
%>
<option>
<%=
	Db.rs.getString("batch_name")
%>
<%
	}
%>
	</option>

</select></td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
<td><input type=reset name=Cancel value=Cancel></td>
</tr>

		<input type="hidden" name="id" value= <%=slabel %> />
		<input type="hidden" name="cid" value= <%=ci %> />
</form></h2>


</table>
</div>
</center><br/>
<%@ include file="footer.html" %>