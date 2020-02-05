# Nevernote Application

## Setup

1) From the terminal, navigate to the project root directory.
2) Run the following commands:
    ```
    mvn clean package
    docker build -t nevernote-docker .       
    ```
   
## Run the Application
1) To run the docker container locally, run the following command:
    ```
    docker run -p 8080:8080 -t nevernote-docker
    ```
2) To view the API documentation, navigate to http://localhost:8080/swagger-ui.html in your browser.
3) To view the in-memory database, navigate to http://localhost:8080/h2
   * Username: admin
   * JDBC URL: jdbc:h2:mem:testdb