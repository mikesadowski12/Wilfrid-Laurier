# Spring Boost MVC
Author: HBF  
Date: 2020-10-18 (update)  

## About this project

This project is to get hand on 

- Spring Boot for stand alone web server with JSP component 
- Spring controller for GET and POST HTTP requests

## Run within Eclipse

1. Run App.java as Java application
2. Test by the following URL 

- [http://localhost:8080/json/ec](http://localhost:8080/json/ec)
- [http://localhost:8080/xml/ec](http://localhost:8080/xml/ec)
- [http://localhost:8080/ec-spring](http://localhost:8080/ec-spring)
- [http://localhost:8080/ec-spring?name=EC](http://localhost:8080/ec-spring?name=EC)
- [http://localhost:8080/ec-spring2](http://localhost:8080/ec-spring2)
- [http://localhost:8080/ec-spring2?name=EC](http://localhost:8080/ec-spring2?name=EC)
- [http://localhost:8080/post-form](http://localhost:8080/post-form)
- [http://localhost:8080/ec-spring3](http://localhost:8080/ec-spring3)
- [http://localhost:8080//login](http://localhost:8080/login)   username:admin password:cp630


## Standalone program

1. Open cmd console, cd to the folder of ec-spring-boot-jsp.
2. Run Maven command: mvn clean package
3. Run command: java -jar target/ec-spring-boot-jsp.jar this will start a web server.
4. Test using as the above.  


## Cross-Origin Resource Sharing (CORS)

CORS allows RESTful web services access from different locations. 

1. Save this index-jqery-cors.html on the desktop, open it using a browser (Chrome, FireFox, Edge). 
2. Test the two input boxes and submit.
3. Read App.java ControllerJson for API and CORS configuration. It allows AJax access from different location. 
4. Read App.java ControllerXML, CORS is not set. It only allows Ajax access from the same web server. The following gets the index-jqery-cors.html from the web server. Both submissions will get the return value. 

[http://localhost:8080/index-jquery-cors.html](http://localhost:8080/index-jquery-cors.html)

## Read files 

- src/main/resources/application.properties contains the configuraion for using jsp 
- src/main/webapp/WEB-INF/jsp  contains jsp pages  
- pom.xml contains the following two dependents for jsp

~~~
<dependency>
	<groupId>org.apache.tomcat.embed</groupId>
	<artifactId>tomcat-embed-jasper</artifactId>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>
~~~

**Note** JSP and Thymeleaf pages should not be in the same project. 


## References

1. [Spring Boot with JSP and Thymeleaf](https://www.codejava.net/frameworks/spring-boot/how-to-create-a-spring-boot-web-application-spring-mvc-with-jsp-thymeleaf)
2. [Create Spring project template](https://start.spring.io/)


