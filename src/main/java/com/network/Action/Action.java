package com.network.Action;

import com.network.Entity.Message;

import java.util.Set;

public interface Action {

    Long publish(Message message, Long userId);

    Set<Message> getAllMessages(Long id);

    boolean follow(Long userId, Long userFollowId);

    boolean unFollow(Long userId, Long userUnFollowId);

}
