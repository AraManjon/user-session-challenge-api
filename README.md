# User Session Challenge Api

User Session Challenge Api is a project created to solve a needed: know when a user has been logged in the system.
The solution has been implement a User Session Generator that works from a username given.

Api is a spring boot project written in Java 8.

### Installation

> git clone git@github.com:AraManjon/user-session-challenge-api.git
>
> in the project directory
>
> cd src/main
>
> run Application
>
> visit http://localhost:8080/swagger-ui.html
--------------------------

### Description

The project has been build in two parts and each has an endpoint and responsibility. 

From an outside-in TDD approach, the first step was implement controllers and project has been growing doing unit test in all work flow. 
Finally, it's realized a test end-to-end for each endpoint. To test project requirements, has been included an acceptance test.

##### Requirements:

- One endpoint to return session token

Notes: **Session Token** = **User Session**:  Username + token + session date

- One endpoint to add this information to a specific user

Notes: **Add this information to a specific user**: generate User Session adding token and date to a username.

#### :rocket: User Session Service

##### Implementation:

:mag: Create a token by the username.

:mag: Add token and date to user in a User Session.

:mag: Return token.

##### Constraints:

:x: Username should have a valid format.

**Create User Session**
 
> POST/session

**Parameters**

| name | required | type |
|:------|:----------|:------|
| username | true | string |

#### :rocket: User Data Service

##### Implementation:

:mag: Retrieve User Session by the username and token.


##### Constraints:

:x: Token should have a correct format. 

:x: Username should have a correct format. 

:x: Valid token and username.

**Retrieve User Session Token**
 
> GET/info/{username}/{token}

**Notes:** Is created a repository to persist User Session. In a first version, was raised an inMemoryRepository. In a second version, it was replaced by a SQLRepository. Project works with both, for change repository to inMemory, remove annotation @Primary from SQLRepository and add to inMemoryRepository.  
