# Spring Boost MVC JSP for WildFly
Author: HBF  
Date: 2020-10-18 (update)  

## About this project

This project ec-spring-boot-jsp-wildfly project  is converted from a ec-spring-boot-jsp project. It can be compiled to created ec-spring-boot-jsp-wildfly.war and deployed to WildFly.  

## Run within Eclipse

1. Run maven clean package to create ec-spring-boot-jsp-wildfly.war
2. Deploy ec-spring-boot-jsp-wildfly.war through WildFly web management console. (not by dragging within Eclipse). 
3. Testing

- [http://localhost:8080/ec-spring-boot-jsp-wildfly/json/ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/json/ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/xml/ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/xml/ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring?name=EC](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring?name=EC)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2?name=EC](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring2?name=EC)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/post-form](http://localhost:8080/ec-spring-boot-jsp-wildfly/post-form)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring3?name=ec](http://localhost:8080/ec-spring-boot-jsp-wildfly/ec-spring3?name=ec)
- [http://localhost:8080/ec-spring-boot-jsp-wildfly//login](http://localhost:8080/ec-spring-boot-jsp-wildfly/login)   username:admin password:cp630


## Difference

This ec-spring-boot-jsp-wildfly project is modified from ec-spring-boot-jsp project by the following changes in pom.xml

1. change  

~~~
<artifactId>ec-spring-boot-jsp</artifactId>

<packaging>jar</packaging>
 
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<finalName>ec-spring-boot-jsp</finalName>
~~~

to 

~~~
<artifactId>ec-spring-boot-jsp-wildfly</artifactId>
<packaging>war</packaging>

 
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<exclusions>
		<exclusion>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
		</exclusion>
	</exclusions>
</dependency>

<finalName>ec-spring-boot-jsp-wildfly</finalName>
~~~


2. Change the App class to the following one

~~~	
@ComponentScan(basePackages="ec.lab")
@SpringBootApplication
public class App extends SpringBootServletInitializer{
	
	@Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

	public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
~~~

3. Append  `server.servlet-path=/*` to the end of `application.properties`


## References

1. [Spring Boot with JSP and Thymeleaf](https://www.codejava.net/frameworks/spring-boot/how-to-create-a-spring-boot-web-application-spring-mvc-with-jsp-thymeleaf)
2. [Create Spring project template](https://start.spring.io/)

