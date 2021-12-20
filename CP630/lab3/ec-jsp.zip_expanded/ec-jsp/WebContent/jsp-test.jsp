<html><head>
<title>JSP examples</title>
</head><body>
<h1>JSP by examples</h1>
<h2>Scriptlet</h2>

<% 
out.print("JSP example"); 
int a=10; 
int i;
%>
<p><% 
for (i=0; i<a; i++)  out.print(i+","); 
%></p>

<% 
int num1 = 1; 
int num2 = 2; 
out.print(num1+num2);
%> 

<p><% 
int num3 = num1 + num2; 
out.print(num3);
%></p>

<h2>JSP Declarations</h2>
 
<%! int a, b; %> 
<p><% 
a = 10;
b = 5; 
out.print(a+b);
%></p>

<h2>JSP Directives</h2>

<%@ page import="java.util.Date" %>  
Today is: <%= new Date() %>  

<h2>JSP Expression</h2>

<p><%= num1+num2 %></p>
<p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>


<h2>Custom tags</h2>

<%@ taglib prefix = "ex" uri = "WEB-INF/ec.tld"%>
<p><ex:ectag/></p>
<p><ex:ectag2>message body</ex:ectag2></p>
<p><ex:ectag3 message = "message in attribute"/></p>

<h2>JSTL</h2>

<a href ="db-jstl.jsp">Use JSTL in JSP to connect MySQL database to get user list. This requires MySQL on and User table with id and name columns.</a>

<h2>JSP Expression Language (EL)</h2>

<p>${1+2}</p>
<p>${1>2}</p>


<h2>Error page</h2>

<a href="divider-client.jsp">Divider. When submitting a 0 denominator it returns an error page with exception.</a>  


<h2>JSP and bean</h2>


<a href="student.jsp">User defined student bean, and use JPS set, get, and display student bean object.</a>  


</body>
</html>