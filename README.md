

SpringBoot Gradle MVC application

    1.JPA
    2.Spring boot, Spring MVC
    3.Gradle

Git

    1. git clone https://github.com/zorg9pi9/socialnetwork.git
    2. go to this directory

Gradle

    Gradle Command Line
    Clean the project:

    $ gradle clean
    Build the project:

    $ gradle build
    Run the project:

    $ gradle run

API
    Send the message
    1. https://localhost:8080/send?userId = 1

    Request:
    {	
        "text" : "test"	
    }

    Response:
    messageId = 1

    Following the user
    2. https://localhost:8080/follow?userId=1&userFollowId=2

    Response:
    true 

    The next attempt will return false for these IDs

    Unfollowed the user
    3. https://localhost:8080/unfollow?userId=1&userFollowId=2

    Response:
    true

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
