<%@ page language="java" %>
<link rel="stylesheet" href="styles.css">
<html>
<head>
<style>
a{color:white}
</style>
</head>
<body>
<div class="overlay" style="color:white;height:80px">
<br>
<div class="left">
<font size="6" face="Cursive"><strong>THE INSTITUTE</strong></font>
</div>
</div>
<% application.removeAttribute("name"); %>
<% application.removeAttribute("designation"); %>
<br><br>
<center>
<h2> <font color="white">Logout Successfully !!</font></h2>
<br>
<font size="4" face="verdana"><a href="login.jsp"> SignIn </a><font color="white">Through another user</font></font>
</center>
</body>
</html>