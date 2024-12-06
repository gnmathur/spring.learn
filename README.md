# spring.learn
Spring learning

# Running
## Stopping running PG container
```bash
~/wkspcs/spring-boot.learn/spring-boot-bookstore/deployment/docker-compose % docker-compose -f infra.yml down
[+] Running 2/2
 ✔ Container catalog-db            Removed                                                                                                                                                                                                                       0.2s
 ✔ Network docker-compose_default  Removed                                                                                                                                                                                                                       0.1s
~/wkspcs/spring-boot.learn/spring-boot-bookstore/deployment/docker-compose %
```

## Running PG container
```bash
~/wkspcs/spring-boot.learn/spring-boot-bookstore/deployment/docker-compose % docker-compose -f infra.yml up -d
[+] Running 2/2
 ✔ Network docker-compose_default  Created                                                                                                                                                                                                                       0.0s
 ✔ Container catalog-db            Started                                                                                                                                                                                                                       0.2s
~/wkspcs/spring-boot.learn/spring-boot-bookstore/deployment/docker-compose %
```

## Running the Catalog service
```bash
~/wkspcs/spring-boot.learn/spring-boot-bookstore/catalog-service % ./mvnw spring-boot:run
```

## Verify the Catalog service
```bash
~/wkspcs/spring-boot.learn/spring-boot-bookstore/catalog-service % curl http://localhost:8080/actuator/info
{"git":{"local":{"branch":{"ahead":"0","behind":"0"}},"commit":{"id":{"describe-short":"b098487-dirty","abbrev":"b098487","full":"b098487dd3e31ab733a58ce762e79b3736259a43","describe":"b098487-dirty"},"message":{"short":"Updated","full":"Updated"},"user":{"name":"Gaurav Mathur","email":"gaurav.mathur@ymail.com"},"author":{"time":"2024-12-02T21:06:03-08:00"},"committer":{"time":"2024-12-02T21:13:01-08:00"},"time":"2024-12-03T05:13:01Z"},"branch":"main","build":{"time":"2024-12-03T05:23:28Z","version":"0.0.1-SNAPSHOT","host":"zerodawn.local","user":{"name":"Gaurav Mathur","email":"gaurav.mathur@ymail.com"}},"tag":"","tags":"","total":{"commit":{"count":"4"}},"closest":{"tag":{"commit":{"count":""},"name":""}},"remote":{"origin":{"url":"git@github.com:gnmathur/spring.learn.git"}},"dirty":"true"},"build":{"artifact":"catalog-service","name":"catalog-service","time":"2024-12-03T05:27:56.791Z","version":"0.0.1-SNAPSHOT","group":"dev.gmathur"}}
```

# Notes
## Organizing the code
Three ways to organize the code:
1. By layer
```
   root 
   ├── controller
        ├── CustomerController.java
        ├── ProductController.java
   ├── services
        ├── CustomerService.java
        ├── ProductService.java
   |── repositories
        ├── CustomerRepository.java
        ├── ProductRepository.java
```   
This is not the best because over time a controller may have to call multiple services and a service may have to call 
multiple repositories. This will lead to a lot of dependencies between the layers. Isolating the layers will be 
difficult, as a change in, say ProductService, might then require a change in CustomerController or ProductController 
or a repository. This is not a good design.
2. By feature
```
    root 
    ├── customers
          ├── CustomerController.java
          ├── CustomerService.java
          ├── CustomerRepository.java
    ├── products
          ├── ProductController.java
          ├── ProductService.java
          ├── ProductRepository.java
```
This can become tangled too and there might be circular dependencies between the features and other issues.
3. By Component
```
root 
|-- web (package)
|   |-- Products
|   |   |-- ProductController.java
|   |-- Customers
|   |   |-- CustomerController.java
|-- domain (package)
|   |-- Products (feature)
|   |   |-- ProductService.java
|   |   |-- ProductRepository.java
|   |-- Customers (feature)
|   |   |-- CustomerService.java
|   |   |-- CustomerRepository.java
```

Web layer can be kept separate from the domain layer by packaging them separately. They can talk via messages. Also
package private access in the domain package can be used to restrict access to the domain layer. 

```
ProductController --> ProductService --> ProductRepository
                           ^
                           |
                   +-------
                   |
CustomerController --> CustomerService --> CustomerRepository
```

CustomerController and ProductController can talk to each other's services. No controller can talk to a repository. 
