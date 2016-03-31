ylog
====

A simple Java blog with Jetty SpringMVC and MyBatis (MySQL).

## Prerequisites ##
- JDK 7
- Maven 3.0.3 or newer

## Configure MySQL database ##

Run the following commands:  
```
mysql -u root -p
grant all privileges on cloud.* to 'root'@'localhost' identified by 'yunshan3302';
flush privileges;
```

Now, populate the database with the script provided: 
```
mysql -u root yunshan3302 < newblog.sql
```

## Test ##
In order to build a WAR package, run the following command:  
```
mvn clean package
```

In order to run program, type the following command:
```
java -jar ylog-4.0.0.jar
```

Point your browser to _http://localhost:8080