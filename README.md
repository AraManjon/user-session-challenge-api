# User Session Token Challenge

User Session Token Challenge is a project created to solve a needed: know when a user has been logged in the system.
The solution has been implement a User Session Token Generator that works from a username given.

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

The project has been build in two parts and each has an endpoint and responsability. 

From an outside-in TDD approach, the first step was the controller implementation and project has been growing doing unit test in all work flow. 
Finally, it's realized a test end-to-end for each endpoint. And to test all project, has been included an acceptance test with all project requirements.

##### Requirements:

- One endpoint to return session token

Notes: **Session Token** = **User Session Token**:  Username + token + current logging date

- One endpoint to add this information to a specific user

Notes: **Add this information to a specific user**: create User Session Token where is added token and date to a username.

#### :rocket: User Session Token Service

##### Implementation:

:mag: Create a token by username.

:mag: Add token and date to user in a user session token.

:mag: Return token.

##### Constraints:

:x: Username cannot be empty.

**Create User Session Token**
 
> POST/session

**Parameters**

| name | required | type |
|:------|:----------|:------|
| username | true | string |

#### :rocket: User Data Service

##### Implementation:

:mag: Retrieve User Session Token by username and token.


##### Constraints:

:x: Token should have a correct format. 

:x: Valid token and username.

**Retrieve User Session Token**
 
> GET/info/{username}/{token}

**Notes:** Is created an InMemoryRepository to persist User Session Token.
