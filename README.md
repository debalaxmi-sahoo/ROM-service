# ROM

Room Occupancy Management (ROM)  is a Spring Boot based Restful Mircoservice application to
manage the process of selecting a room (either premium or economic )based on the customer's payment.

This API has used Open JDK 11 and sprint boot 2.3.4 version.

To run ROM, clone or download the source code from GITHub.
As it is a public repository , no need of permission to download the code.

Import and Open the down loaded project in any IDE ,recommended would be Spring Tool Suite . 

In sprint boot, default embedded server is tomcat .

Start the application server by right clicking on the project. 

Once the server is  up, the application would be accessible  by copying the below url on brwoser .

This application.yml has used 8082 as server port, any other valid port can be used based on the availability on particular host where the application will run.

http://localhost:8082/rom-service/api/processBookings?availablePremRoomCount=3&availableEconoRoomCount=3

Also to access and see the response any API , POSTMAN is the recommended UI .

open the postman and access the above mentioned URL.Postman will show the response in more structured way.

alternatively can run the Junit bundled with this source code for different use cases. 
for Junit run , no need to start server .



