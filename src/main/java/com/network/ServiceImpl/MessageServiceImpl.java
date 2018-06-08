package com.network.ServiceImpl;

import com.network.Entity.Message;
import com.network.Service.MessageService;
import com.network.Action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    private Action action;

    @Autowired
    public MessageServiceImpl(Action action) {
        this.action = action;
    }

    @Override
    public Set<Message> findAll(Long userId) {
        return action.getAllMessages(userId);
    }

    @Override
    public Long send(Message message, Long userId) {
        return action.publish(message ,userId);
    }
}
