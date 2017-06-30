# RetailerApp
********* Spring Boot MicroServices ******

This application has been developed using Spring boot, JPA Data, Security and Mysql.
Spring Boot Rest web services using Lomback and Orika libraries.

======== Steps to run the application ========

1) Check out the code using below git command.

  git clone https://github.com/rake93/RetailerApp.git

  or Download it as ZIP from below URL

  https://github.com/rake93/RetailerApp/archive/master.zip

2) Install MySQL and create retailer schema, don't need to create tables. It'll create tables when you run the application. Please update the DB connection details in application.properties file.

3) Please install maven before you run the below command, if its not there in your system.

4) Got to the root folder of application in command prompt.

   eg: >cd C:\Rakesh\RetailerApp

   run mvn clean install from the command line. Once it is success and If you look in the target directory you should see RetailerApp-0.0.1-SNAPSHOT.

5) To run that application, use the java -jar command:

   $ java -jar target/RetailerApp-0.0.1-SNAPSHOT.jar
 
6) Go to browser or Use any REST client and hit the below URL to check the Application status. You should see the Hello World..! message.
   http://localhost:8080/retailer/greeting
