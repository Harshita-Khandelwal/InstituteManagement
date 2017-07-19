<%@ page language="java" import="Database.Db,java.util.*"%>
<jsp:useBean id="course" class="Database.Course" scope="request" />
<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<%!int slabel; %>
<%Db.rs=Db.stmt.executeQuery("select max(course_id) as highest from course");
			Db.rs.next();
			slabel=(Db.rs.getInt("highest"));
			slabel++;
	%>
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>Add Course</h1>
</tr>
<h2><form method=post action="CreateCourse.jsp">
<tr><td>Course ID:C<%= slabel %></td></tr>
<tr><td>Course Name:</td><td><input type=text name=course_name required maxlength="10" class="roundborder"></td></tr>
<tr><td>Course Fees:</td><td><input type=number name=course_fees required max="65535" class="roundborder"></td></tr>
<tr><td>Approx Duration:</td><td><input type=number step="0.5" name=approx required class="roundborder">
<select name="duration" class="roundborder" >
  <option value="Days">Days</option>
  <option value="Months">Months</option>
  <option value="Year">Year</option>
</select> </td></tr>
<tr><td><input type=submit name=Submit value=Submit class="roundborder"></td>
<td><input type=reset name=Cancel value=Cancel class="roundborder"></td>
</tr>

		<input type="hidden" name="course_id" value= <%=slabel %> />
	</form></h2>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>