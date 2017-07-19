<%@ page language="java" import="java.util.*"%>

<link rel="stylesheet" href="styles.css">
<jsp:include page="adminheader.jsp" flush="true" />
<br/><br/>
<center>
<div align="center" style="color:black;width:800px;height:300px;background:white ">
<table>
<tr>
<h1>View/Edit Faculty Profile</h1>
</tr>
<tr>Search Options:</tr>
<form method=get action="FacSearch.jsp">
<tr><td><input type="radio" name="group1" value="id"> Search By Id<br/></td></tr>
<tr><td><input type="radio" name="group1" value="name" checked>  Search By Name<br/></td></tr>
<tr><td><input type="radio" name="group1" value="course">  Search By Course<br/></td></tr>
<tr><td><input type="radio" name="group1" value="batch">  Search By Batch<br/></td></tr>
<tr><td><input type="radio" name="group1" value="all">  All Entries</td></tr>
<tr><td><input type=submit name=Submit value=Submit></td>
</tr></form>
</table>
</div>
</center><br/>
<%@ include file="footer.html" %>
