# Recruitment-Project-Elevators
> The API represents an elevator management system. The project was written in Java using Spring. The database, as it is an implementation detail and the fact that it was not the main goal of the project was skipped, and its functions are performed by the 'Collections' library. The project is primarily focused on the implementation of the algorithm, but attention was also paid to the appropriate architecture of the application, clean code, and the use of good object-oriented programming practices.


## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Run and Test](#run-and-test)
* [Endpoints](#endpoints)
* [Algorithm](#algorithm)
* [Room for Improvement](#room-for-improvement)


## General Information
- The application gives us the ability to manage 16 elevators on 10 floors. We have the ability to make an emergency change 'UPDATE' to the elevator and at the same time remove all its planned steps. We can also call 'PICKUP' an elevator from any floor, while marking the direction of our journey. The entire simulation is done by selecting the 'STEP' option, which performs a step in the entire simulation. We can also list 'STATUS' the status of each elevator at any time. All 16 elevators are used, and they are selected in the most optimal way.

- The project is a recruitment task to demonstrate knowledge of Java tools, object-oriented programming and understanding of basic algorithms and data structures.

- The frontend of the application has also been implemented (very primitive btw), which allows you to manually test the algorithm.



## Technologies Used
- Java 18
- Spring Boot
- JUnit, Mockito

## Run and Test

To run the application type

```
mvn spring-boot:run
```

To execute unit tests

```
mvn '-Dtest=*Test' test
```

To execute integration tests

```
mvn '-Dtest=*IT' test
```

To run HTML page to manually test algorithm

```
open web/index.html
```

## Endpoints

* Update place of Elevator: `PUT/elevators/update/{elevatorId}/floor={}`
* Pickup an Elevator: `PUT/elevators/pickup?sourceFloor={}&destinationFloor={}`
* Make a step in simulation: `PUT/elevators/step`
* Display status of every Elevator: `GET/elevators/status`


## Algorithm

The algorithm is not perfect, but it can definitely be called optimal. The most key aspects are the right order of visiting the next floors and the selection of the right elevator.

Order of floors visited
- The priority is to stop at the floor where the user is to enter, only then is the target of that user.

Choosing the right elevator
- First we try to select the most cost-effective elevator, i.e. one that moves in the direction desired by the user, and the user's floor is between the elevator's current floor and its next simulation target.
- If we are unsuccessful, we select the closest elevator, which does not have any floors planned.


## Room for Improvement

- Taking passengers who are farther away than the final destination of the elevator. At one time this can be an advantage, because the trip in the current implementation is much faster, the problem is the energy cost of doing the work by the elevator. But due to the fact that they do not return to the first floor each time, this still generates a large savings.
- Writing e2e tests pertaining to the API, but also the entire web application.
- Writing a less primitive front-end website.
