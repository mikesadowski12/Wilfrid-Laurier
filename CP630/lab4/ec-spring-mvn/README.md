# Spring Framework IoC and AOP
Author: HBF  
Date: 2020-10-18 (update)  

This task checks the basics of IoC and AOP of Spring framework.


## IoC examples

### BeanFactory container

1. Check ECSpring.java in folder ec.lab.bean
2. Check AppBeanFactory.java and SpringBeans.xml in resources folder. The xml file contains the configuration bean object with bean id ec-spring-bean for the ECSpring bean.
3. Run Maven test
4. Run Maven clean package. This will create a runnable ec-spring-mvn.jar in the target directory.
5. Run AppBeanFactory.java, to see the result on console tab. Check the console output, and JUnit tab. 
6. Open a cmd console, cd to the C:\cp630\labs\lab4\ec-spring-mvn\target, and type command

java -jar  ec-spring-mvn.jar

Check the console output. Now you see how can we use Spring IOC develop general Java applications. 

### IoC ApplicationContext container

1. Check AppContext.java
2. Run src/main/java/ec.lab.ioc/AppContext.java as Java application, and see the result on console tab.
3. Run src/test/java/ec.lab/ECSpring.Test.java as JUnit Test. 

### ApplicationContext with property configuration

1. Check AppContextConfig.java and ECSpringConfig.java
2. check ECSpring.properties in resources folder
3. Run AppContext.java as Java application, and see the result on console tab.

### Multiple beans

1. Check AppPlayer.java and Players.xml multiple bean object definition.
2. Run AppPlayer.java as Java application, and see the result on console tab.

## AOP examples

1. Check BeforeMethod.java, AroundMethod.java, AfterMethod.java, ThrowException.java, these defined the methods interceptions.
2. Check resources/PlayerAop.xml, it defines the bean configurations for advises.
3. Run AppAop.java as Java application, and see the result on console tab. 

