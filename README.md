# jetdevs-demo
Jet Devs Spring Boot Demo

### Requirements – For Java Developers
#### Tech Stack
- JDK 1.8 (JDK 8) or JDK 18
- Spring boot
- MySQL

#### Features
- As admin, I can upload records into the database from an Excel file, including
   headers and rows. 
  - Endpoint for file uploading. 
  - Endpoint for the progress checking. 
- As a user and admin, I can list all the files that are uploaded. 
  - Endpoint for list files.
- As a user and admin, I can review specific files. 
  - Endpoint for list records. 
    - Update last access time for the file. 
    - Log the user’s information who reviewed the file. 
- As admin, I can delete specific files. 
  - Endpoint for file delete. 
    - Delete the records which belong to the file as well.

NOTE: 
- The file from the older version Excel was acceptable. 
- Documentation based on swagger is required.

##### Additional bonus points
- User authentication 
- Role management 
- Unit test


#### Steps to run application

1. With Docker File
```shell
 Open Terminal
 cd <app-source-foler-path>
 docker build -t upload-app .
 docker-compose up
```

2. Jar File
```shell
 Open Terminal
 cd <app-source-foler-path>
 mvn clean compile package
 java -jar .\target\jetdevs-testtask-1.0-SNAPSHOT.jar com/jetdevs/JetDevsTaskApplication

```

- Once Started: Go to browser and run http://localhost:8080/swagger-ui/index.html.
- GDrive link for App Demo: https://drive.google.com/file/d/1ckLHlol4FHB99IhYzCEjBeoVA0sOSV8W/view?usp=sharing 
