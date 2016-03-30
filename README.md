# BootSocial
Spring Boot Web with Spring Security and Social Login(Facebook,Twitter,Github,Kakao) Using Mysql<br>
Description : 
```
http://blog.naver.com/wizardkyn/220670022848
```

# Environment
Maven<br>
Spring Boot 1.4.0 , Spring Security 4.0.3 , spring-security-oauth2 2.0.9 <br>
spring-social-core 1.1.4 , spring-social-web 1.1.4 , spring-social-config 1.1.4 , spring-social-security 1.1.4<br>
spring-social-facebook 2.0.3 , spring-social-twitter 1.1.2 , spring-social-github 1.0.0.M4<br>
mysql , mybatis 3.3.1 , mybatis-spring 1.2.4<br>
JSTL<br>
Logback<br>
External Tomcat 8<br>
Test URL : http://www.example.com:8080/BootSocial/login.do<br>
Edit C:\Windows\System32\drivers\etc\hosts<br>
127.0.0.1 www.example.com

# Tomcat Datasource JNDI
```
<GlobalNamingResources>
<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" 
loginTimeout="10" maxActive="200" maxIdle="8" maxWait="5000" 
name="jdbc/sim" username="dbuser" password="1234" 
type="javax.sql.DataSource"
url="jdbc:mysql://db.example.com:3306/exampledb?zeroDateTimeBehavior=convertToNull"/>      
</GlobalNamingResources>

<Context docBase="BootSocial" path="/BootSocial" reloadable="true">
<ResourceLink global="jdbc/sim" name="jdbc/sim" type="javax.sql.DataSource"/>
</Context>
```
