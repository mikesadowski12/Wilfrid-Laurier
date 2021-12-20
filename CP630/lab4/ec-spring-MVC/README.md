# Spring Web MVC
Author: HBF  
Date: 2020-10-18 (update)  

## About

This project shows the basics of Spring MVC tool for web component. Use Tomcat 9.0 for testing. 

## Testing 

1. Build the project by Maven with option `clean package`. This will create ec-spring-MVC.war in the target folder.
2. Start Tomcat 9.0 
3. Copy file ec-spring-MVC.war to the webapps directory. This will deploy and run ec-spring-MVC.   
4. Testing 

- Open URL [http://localhost:8080/ec-spring-MVC/ec-spring](http://localhost:8080/ec-spring-MVC/ec-spring). Read the ECSpringController.java,  src/main/webapp/WEB-INF/jsp/ecspring.jsp, ecspring2.jsp
- Open URL [http://localhost:8080/ec-spring-MVC/ec-spring?name=SpringMVC](http://localhost:8080/ec-spring-MVC/ec-spring?name=SpringMVC)
- Open [http://localhost:8080/ec-spring-MVC/post-form](http://localhost:8080/ec-spring-MVC/post-form)  and check ECSpringController2.java, postform.js, ecspring3.jsp. 
- Try URL  [http://localhost:8080/ec-spring-MVC/login](http://localhost:8080/ec-spring-MVC/login) for simple login example. 
- Check spring-servlet.xml, this file define Spring beans used in this project. 
- Check web.xml, again, this file specify the Servlet components. 

