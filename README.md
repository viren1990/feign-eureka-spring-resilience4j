## building

`mvn package`

# eureka
run `java -jar eureka/target/eureka-0.0.1-SNAPSHOT.jar`

verify it is functioning at [http://localhost:8761](http://localhost:8761)


## hello server

run `java -jar server/target/feign-eureka-hello-server-0.0.1-SNAPSHOT.jar`

verify it is functioning at [http://localhost:7111](http://localhost:7111)

You should see `Hello World: HelloServer:myhostname:7111`

## hello client

run `java -jar client/target/feign-eureka-hello-client-0.0.1-SNAPSHOT.jar`

verify it is functioning at [http://localhost:7211](http://localhost:7211)

You should see `Hello World: HelloServer:myhostname:7111`

## See round robin load balancing in action

run `java -jar server/target/feign-eureka-hello-server-0.0.1-SNAPSHOT.jar --server.port=7112`

Go back to [http://localhost:7211](http://localhost:7211) and you should see both ports `7111` and `7112` in the output after a minute or two as you keep refreshing.
