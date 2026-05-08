The system is composed of the following microservices:
User Service
	Port: 8080
Wallet Service
	Port: 8081
Offer Service
	Port: 8082
Booking Service
	Port: 8083
Notification Service
	Port: 8084

Run the Project
To run the system successfully, ensure the following prerequisites are met:

Prerequisites
	Java Development Kit (JDK) installed
	Maven installed and configured in system PATH
	RabbitMQ server running using docker(docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:4.3.0-management)

Running Each Service
Each microservice is an independent Spring Boot application.

To start a service:
Run the DemoApplication class (main Spring Boot entry point)

Technologies Used
Spring Boot (all microservices)
H2 database (each service has its own db)
RabbitMQ (asynchronous messaging)
REST APIs for inter-service communication

Assumptions
The system is designed based on the following assumptions:
	The system operates under the success path as the primary scenario
	All services are assumed to be running simultaneously for correct system behavior