# reducedProducts
Reduced Products is a service to get reduced products from a category.

# Build
The service ris built with Java 11 and maven. It can be built with the command:
```
mvn clean install
```

# Run
The service runs on Spring Boot and can be ran locally once built with the command:
```
java -jar target/reducedProducts-0.0.1-SNAPSHOT.jar
```

# Endpoint
The reduced products for category endpoint can be called using:
```
curl localhost:8080/categories/{categoryId}/reducedProducts?labelType={labelType}
```
Where `{categoryId}` is the id of the category (600001506 works for this test) and `{labelType}` is the type of price label to be returned as part of the response.
