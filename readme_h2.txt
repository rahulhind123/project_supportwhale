+++++++++++++++++++++++
Technology Stack
+++++++++++++++++++++++
Java 8
Spring Boot 2.0.3.RELEASE
h2  1.4

++++++++++++++++++++++
Application
++++++++++++++++++++++
Application structure is based on Controller -> Service -> Repository pattern. 
In memory database is used and sample data is preloaded from 'data.sql' file in classpath on application start. 

Note : h2_db_data.sql run only when we started our application

Go to below url and enter following properties
http://localhost:9090/sql-console

JDBC URL : jdbc:h2:mem:scheduler_db
Username :root
Pwd : root

On logged in run sql command of h2_db_data.sql file.


++++++++++++++++++++++
UI View
++++++++++++++++++++++
A simple web page with Bootstrap and jQuery is used to call the REST API and display the return data.
Spring thymeleaf is used for server-side rendering of the page.

++++++++++++++++++++++
REST API
+++++++++++++++++++++
URI: /genrate_schedule
Method: POST

Request Header
Content-Type: application/x-www-form-urlencoded
Request Body
Parameter Name: startDate
Parameter Name: endDate
Possible value: Date in format 'yyyy-MM-dd'
Example: startDate=2018-03-26

Response Header
Content-Type: application/json
Response Body
Object which contains list of engineer as per date

++++++++++++++++++++++
Build and Run
++++++++++++++++++++++
Maven is used for dependencies and project build.

Exceute following command to run the application
Go to jars folder and run following command
java -jar h2_db_assignment.jar

Open the following URL in browser
localhost:9090/

Note: The default port set for this application is 9090 in application.properties file. Web server in the application will start on port 9090