

SpringBoot Gradle MVC application

1.JPA
2.MVC
3.Gradle

1. git clone https://github.com/zorg9pi9/socialnetwork.git
2. go to this directory

Gradle Command Line
Clean the project:

$ gradle clean
Build the project:

$ gradle build
Run the project:

$ gradle run

API
Send the message
1. https://localhost:8080/send
params:
userId = 1

Request:
{	
  "text" : "test"	
}
Response:
messageId = 1

Following the user
2. https://localhost:8080/follow/
params: 
userId=1
userFollowId=2

Response:
true 

The next attempt will return false for these IDs

Unfollowed the user
3. https://localhost:8080/unfollow
params: 
userId=1
userFollowId=2

Response:
true

The next attempt will return false for these IDs

4. https://localhost:8080/messages
params:
userId = 1

Response:

