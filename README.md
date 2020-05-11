# User Session Challenge

User Session Challenge is a project created to solve a needed: know when a user has been logged in a system.
The solution was implement a User Session Token Generator that works from a username given.

Api is a spring boot project written in Java 8.

### Installation

> git clone git@gitlab.com:Ara_Manjon/user-session-challenge.git
>
> cd user-session-challenge/src/main
>
> run Application
>
> visit http://localhost:8080/swagger-ui.html
--------------------------

### Description

The project has been build in two parts and each has an end-point and responsability. 

From an outside-in TDD approach, the first step was the controller implementation and all project was growing doing unit test in all work flow. 
Finally, it's realized a test end-to-end for each end-point. And to test all project, is included an acceptance test with all requirements.

#### :rocket: User Session Service

##### Implementation:

:mag: Create a token by username.

:mag: Add token to user session that includes: username, token and current date.

:mag: Return token.

##### Constraints:

:x: Username cannot be empty.

**Create session**
 
> POST/session

**Parameters**

| name | required | type |
|:------|:----------|:------|
| username | true | string |

#### :rocket: User Data Service

##### Implementation:

:mag: Retrieve user session by username and token.

:mag: Return username, token and session date.

##### Constraints:

:x: Token should have a correct format. 

:x: Token and username should exist.

**Retrieve User Data**
 
> GET/info/{username}/{token}

**Notes:** Is created an InMemoryRepository to persist user sessions.
