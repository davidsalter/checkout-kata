# Checkout Kata

## Requirements
To build and run the application, the following requirements are necessary.

* Apache Maven
* Java 11
## Testing the application

Execute the following command to test the software.

`mvn clean test`

```
...
[INFO] Results:
[INFO] 
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  18.052 s
[INFO] Finished at: 2019-08-12T20:42:19+01:00
[INFO] ------------------------------------------------------------------------

```
## Build and Run Instructions



Execute the following command to build and start the server.

`mvn springboot:run`

```
 ~/Code/checkout $ mvn spring-boot:run
[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.davidsalter:checkout >----------------------
[INFO] Building checkout 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
...
2019-08-12 20:39:37.649  INFO 15111 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-08-12 20:39:37.659  INFO 15111 --- [           main] c.d.checkout.CheckoutApplication         : Started CheckoutApplication in 4.531 seconds (JVM running for 15.111)
```

## API

### Create Price List
POST http://localhost:8080/priceList

Headers: "Application/json"

Payload: 
``` 
[
	{
	  "sku" : "A",
	  "unitPrice" : 50,
	  "discountQuantity" : 3,
	  "discountPrice" : 130
	},
	{
	  "sku" : "B",
	  "unitPrice" : 30,
	  "discountQuantity" : 2,
	  "discountPrice" : 45
},
	{
	  "sku" : "C",
	  "unitPrice" : 20,
	  "discountQuantity" : 0,
	  "discountPrice" : 0
	},
	{
	  "sku" : "D",
	  "unitPrice" : 15,
	  "discountQuantity" : 0,
	  "discountPrice" : 0
}
]
```

### Reset Price List
DELETE http://localhost:8080/priceList

Headers: "Application/json"

Payload: NA

### Scan Item
POST http://localhost:8080/scan/items/B

Headers: "Application/json"

Payload: N/A

### Complete scanning items
DELETE http://localhost:8080/scan/items

Headers: "Application/json"

Payload: NA

