

# SocialNetwork application

The application is a social network with the ability to send messages, subscribe to other users, unsubscribe from users and receive a list of own messages and users who are subscribed in chronological order

    1.JPA
    2.Spring boot, Spring MVC
    3.Gradle

# Git

    1. git clone https://github.com/zorg9pi9/socialnetwork.git
    2. go to this directory

# Gradle

    Gradle Command Line
    Clean the project:

    $ gradle clean
    Build the project:

    $ gradle build
    Run the project:

    $ java -jar socialnetwork.jar

# API

    Send the message
    1. https://localhost:8080/send?userId=1
    
    Following the user
    2. https://localhost:8080/follow?userId=1&userFollowId=2
    
    Unfollowed the user
    3. https://localhost:8080/unfollow?userId=1&userFollowId=2
    
    getMessages
    4. https://localhost:8080/messages?userId=1
    
# HTTP requests

    POST Send the message
    POST Following the user
    POST Unfollowed the user
    GET  Get a list of messages
    
# Request and Response

    Request:
    {	
        "text" : "test"	
    }

    Response:
    messageId = 1

   

    Response:
    true 

    The next attempt will return false for these IDs

    Unfollowed the user
    3. https://localhost:8080/unfollow?userId=1&userFollowId=2

    Response:
    true
    
# HTTP requests
    POST Create a resource
    GET Get a resource or list of resource

    The next attempt will return false for these IDs

    4. https://localhost:8080/messages?userId = 1
    Response:

    [
        {
            "messageId": 1,
            "text": "test",
            "dateCreation": "2018-06-07T21:27:48.935"
        }
    ]
