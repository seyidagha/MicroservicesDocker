Example application with Microservices architecture, using Kafka, 
SocketIO, NodeJS, Spring Boot, PostGresql, Docker. Application is ready 
to run on Docker virtual address 192.168.99.100 and will try to run 
there if you uncompress the rar file "1500000 Sales Records.csv" and run 
"docker-compose up" in the root folder. For running in 
localhost, change "application.properties" files of Spring Boot 
projects to localhost. 
Logic:
TaskWebApp is an interface to start and stop process. After pressing 
Start, Ajax request is sent to Publisher, it starts to read a file 
"1500000 Sales Records.csv" 2 lines and send them to Kafka topic. 
Consumer application listens to this topic and gets data. Then it 
inserts it into Postgresql database and sends the message to SocketIO 
about successive insertion. After that TaskWebApp gets message from 
SocketIO and reads the current number of records in the database. 
Each 2 seconds publisher read 2 new lines of file. This process 
continues until one presses stop button.  
