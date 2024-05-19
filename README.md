# Fabrick Coding Challenge Task 1 - Asteroids Path

<div align="center">
  <img src="https://api.nasa.gov/assets/img/favicons/favicon-192.png" alt="nasa logo" style="max-height: 300px;">
</div>

This project is a REST API that provides an endpoint to retrieve the path of an asteroid as it passes through the solar system. The API is a Spring Boot and Java 22 application development project. The project also includes unit testing of the service layer using JUnit and Mockito. To reduce the number of requests to the [NASA API](https://api.nasa.gov/), the project uses Redis as a cache to store the asteroid data.

## Configuration

- **Java 22**
- **Apache Maven 3.9.6**
- **Redis Stack** running on localhost:6379 (default configuration) (Docker is recommended)
  
### Redis docker image configuration

[Redis Docker documentation](https://redis.io/learn/operate/orchestration/docker)

```shell
$ docker run -d --name my-redis-stack -p 6379:6379  redis/redis-stack-server:latest
```

## Test

- In order to test remember to start the Redis Stack server
- a Postman collection is provided at the following path:
  
  ```http\Task 1 Test Collection.postman_collection.json```





## Task 1 - Asteroids path

The goal of this task is to code a **REST** API project that expose an API that, given an asteroid, it describes its path across the Solar System.

Here is an example of the API we want to expose:

```javascript
GET /api/fabrick/v1.0/asteroids/{asteroidId}/paths?fromDate={fromDate?now-100years}&toDate={toDate?now}

[
    {
        "fromPlanet": "Juptr",
        "toPlanet": "Earth",
        "fromDate": "YYYY-MM-DD",
        "toDate": "YYYY-MM-DD"
    },
    {
        "fromPlanet": "Earth",
        "toPlanet": "Venus",
        "fromDate": "YYYY-MM-DD",
        "toDate": "YYYY-MM-DD"
    },
    ...
]
```

You can rely on the official [NASA Open API](https://api.nasa.gov/); have a look at the **Asteroids - NeoWs** service, in particular at the **Neo - Lookup** API.

### Constraints

* The query parameters **fromDate** and **toDate** are optional, by default:
  * ```fromDate=now-100years```
  * ```toDate=now```
* Each date value should have the format ```YYYY-MM-DD```
* ```asteroidId``` is the **Asteroid SPK-ID** correlates to the **NASA JPL** small body
* For each tuple ```(fromPlanet, toPlanet)```, ```fromPlanet```&ne;```toPlanet```
* Only paths from ```fromDate```  up to ```toDate``` should be displayed
* ```fromDate``` should be the very first day related to the passage from ```fromPlanet```, while ```toDate``` should be the very first day related to the passage from ```toPlanet``` (have a look at ```close_approach_date```); so for example if we have:

```json
[
  {
    "close_approach_date": "1917-04-30",
    "orbiting_body": "Juptr"
  },
  {
    "close_approach_date": "1920-05-01",
    "orbiting_body": "Juptr"
  },
  {
    "close_approach_date": "1930-06-01",
    "orbiting_body": "Earth"
  },
  {
    "close_approach_date": "1937-02-04",
    "orbiting_body": "Earth"
  },
  {
    "close_approach_date": "1950-08-07",
    "orbiting_body": "Juptr"
  },
  {
    "close_approach_date": "1991-06-22",
    "orbiting_body": "Juptr"
  }
]
```

The output should be:

```json
[
    {
        "fromPlanet": "Juptr",
        "toPlanet": "Earth",
        "fromDate": "1917-04-30",
        "toDate": "1930-06-01"
    },
    {
        "fromPlanet": "Earth",
        "toPlanet": "Juptr",
        "fromDate": "1930-06-01",
        "toDate": "1950-08-07"
    }
]
```

### Bonus points

* Implement unit testing using some mocking stub framework
* Implement a local cache or database layer; please note that the database layer should be bootstrapped by the application (we don't want to deal with external systems)