+++++++++++++++++++++++
Technology Stack
+++++++++++++++++++++++
Java 8
Spring Boot 2.0.3.RELEASE
MySql  5.0.6

++++++++++++++++++++++
Application
++++++++++++++++++++++
Application structure is based on Controller -> Service -> Repository pattern. 
My Sql database is used and sample data is preloaded from 'sql/mysql_b_data.sql' file in classpath on application start.


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

Maven is used for dependencies and project build.

Go to jars folder and execute following command to run  the application

java -jar mysql_db_assignment.jar

Open the following URL in browser
localhost:9091/

Note: The default port set for this application is 9091 in application.properties file. Web server in the application will start on port 9091