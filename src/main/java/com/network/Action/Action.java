package com.network.Action;

import com.network.Constants.MessageValues;
import com.network.Entity.Message;
import com.network.Entity.User;
import com.network.Entity.Wall;
import com.network.Repository.MessageRepository;
import com.network.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class Action {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    private static Logger log = LogManager.getLogger(Action.class);

    @Autowired
    public Action(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Long publish(Message message, Long userId) {
        Wall wall = messageRepository.findById(userId).orElse(new Wall());
        User user = userRepository.findById(userId).orElse(new User());
        wall.getMessages().add(message);
        for (Map.Entry<Long, Wall> ref : user.getRefToUser().entrySet()) {
            ref.getValue().getMessages().add(message);
        }
        for (Map.Entry<Long, Wall> followers : user.getFollowUsersWalls().entrySet()) {
            wall.getMessages().addAll(followers.getValue().getMessages());
        }
        wall.setUserId(userId);
        user.setId(userId);

        //save current user
        userRepository.save(user);

        log.info(MessageValues.RECEIVED);

        return messageRepository.save(wall).getUserId();
    }

    public Set<Message> getAllMessages(Long id) {
        return messageRepository.findById(id).orElse(new Wall()).getMessages();
    }

    public boolean follow(Long userId, Long userFollowId) {
        User user = userRepository.findById(userId).orElse(new User());
        User userFollow = userRepository.findById(userFollowId).orElse(new User());
        if (!user.getRefToUser().containsKey(userFollowId) && !user.getFollowUsersWalls().containsKey(userFollowId)) {
            //get wall current User
            Wall wall = messageRepository.findById(userId).orElse(new Wall());
            wall.setUserId(userId);

            //get Wall follow User
            Wall followWall = messageRepository.findById(userFollowId).orElse(new Wall());
            followWall.setUserId(userFollowId);

            //save follow User
            user.getFollowUsersWalls().put(userFollowId, followWall);
            userFollow.getRefToUser().put(userId, wall);

            //set id's for users
            user.setId(userId);
            userFollow.setId(userFollowId);

            //save
            userRepository.save(user);
            userRepository.save(userFollow);

            log.info(MessageValues.USER + userId + MessageValues.USER_FOLLOW + userFollowId);

            return true;
        }
        log.info(MessageValues.USER + userId + MessageValues.USER_NOT_FOLLOW + userFollowId);
        return false;
    }


    public boolean unFollow(Long userId, Long userUnFollowId) {
        User user = userRepository.findById(userId).orElse(new User());
        User userUnFollow = userRepository.findById(userUnFollowId).orElse(new User());
        Map<Long, Wall> unFollowUsersWalls = user.getFollowUsersWalls();
        Map<Long, Wall> unFollowRefToUsersWalls = userUnFollow.getRefToUser();
        if (unFollowUsersWalls.containsKey(userUnFollowId)) {
            //remove subscribers
            unFollowUsersWalls.remove(userUnFollowId);
            unFollowRefToUsersWalls.remove(userId);
            //save
            userRepository.save(user);
            userRepository.save(userUnFollow);
            log.info(MessageValues.USER + userId + MessageValues.USER_UNFOLLOW + userUnFollowId);
            return true;
        }
        log.info(MessageValues.USER + userId + MessageValues.USER_NOT_UNFOLLOW + userUnFollowId);
        return false;
    }

}
