# trafficker
Traffic data from clients to analytics engine

# Building the jar:

Gradlew will build the jar for you:
`./gradlew build`

To decompress the jar:
`java -jar build/libs/trafficker-<VERSION>-SNAPSHOT.jar`


# Running the application:

`gradle bootRun`

To access the hello world REST api:

`http://localhost:8080/greeting`
