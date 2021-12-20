<html>
<head>
<title>JSP set and get properties</title>
</head>
<body>
<p>Student Score</p>
<jsp:useBean id="students"  class="ec.lab.StudentBean"> 
   <jsp:setProperty name="students" property="firstName" value="HB"/>
   <jsp:setProperty name="students" property="lastName"  value="F"/>
   <jsp:setProperty name="students" property="score" value="88"/>
</jsp:useBean>

<p>First Name:  <jsp:getProperty name="students" property="firstName"/> </p>
<p>Last Name:   <jsp:getProperty name="students" property="lastName"/> </p>
<p>Score:  <jsp:getProperty name="students" property="score"/> </p>

</body>
</html>