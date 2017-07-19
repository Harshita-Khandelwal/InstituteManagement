<link rel="stylesheet" href="styles.css"><html>
<head>
<style> 
.roundborder /* to round the corners of input fields*/
{
    -moz-border-radius:10px; /* Firefox */
    -webkit-border-radius: 10px; /* Safari, Chrome */
    -khtml-border-radius: 10px; /* KHTML */
    border-radius: 10px; /* CSS3 */
    behavior:url("border-radius.htc");
}
h2{
    text-align: justify;
}
li,table
{
	 white-space: nowrap;
}

li
{
font-size:120%;
float:left;
position:relative;
padding-right:50; 
display:block;
}
 li ul 
 { 
 display: none;
 position:absolute;
 }
 li:hover ul 
 { 
 display: block;
 height:auto;
 width:auto; 
 }
 li ul li
{ 
font-size:100%;
clear:both;
 border-style:none;
 } 
 .nav
 {
background-color:white;
color:#FF7F50; 
 }
 .subnav
 {
	background-color:#FFF8DC;
color:blue; 
 }
.view{ 
    display: table;
    border-collapse: separate;
    border-spacing: 2px;
    border-color: gray;
}
 </style>
</head>
<body>
	
	<%@ include file="header.jsp" %>
	<br/><br/>
<div class="right">
<font size="4" face="verdana"><a href="logout.jsp"><h3>Logout</h3></a></font>
</div>
	
	<%--<div id="log" align="right"><a href="logout.jsp"><h3>Logout</h3></a></div>--%>
	<ul >
	<li class="nav">Student Profile
	<ul class="subnav">
	<li><a href="StNewC.jsp">New Profile</a></li>
	<li><a href="StSearchOpt.jsp">View/Edit</a></li>
	</ul>
	</li>
	<li class="nav">Queries
	<ul class="subnav">
	<li><a href="QueryNew.jsp">New Query</a></li>
	<li><a href="QuSearchOpt.jsp">View/Edit</a></li>
	<li>Export Query</li>
	</ul>
	</li>
	<li class="nav">Courses
	<ul class="subnav">
	<li><a href="CourseNew.jsp">New Course</a></li>
	<li><a href="CourseSearchOpt.jsp">View/Edit</a></li>
	</ul>
	</li>
	<li class="nav">Batch
	<ul class="subnav">
	<li><a href="BatchNewC.jsp">New Batch</a></li>
	<li><a href="BatchSearchOpt.jsp">View/Edit</a></li>
	</ul>
	</li>
	<li class="nav">User Account
	<ul class="subnav">
	<li><a href="UserNewD.jsp">New User</a></li>
	<li><a href="UserSearchOpt.jsp">View/Edit</a></li>
	</ul>
	</li>
	<li class="nav">Fee Details
	<ul class="subnav">
	<li><a href="FeeNewS.jsp">Deposit Fee</a></li>
	<li><a href="FeeSearchOpt.jsp">View/Edit</a></li>
	</ul>
	</li>
	<li class="nav">Faculty Profile
	<ul class="subnav">
	<li><a href="FacultyNew.jsp">New Faculty</a></li>
	<li><a href="FacSearchOpt.jsp">View/Edit</a></li>
	</ul>
	</li>
	</ul>
	</body>
	</html>