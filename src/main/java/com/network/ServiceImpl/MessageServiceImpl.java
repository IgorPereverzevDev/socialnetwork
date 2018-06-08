package com.network.ServiceImpl;

import com.network.Entity.Message;
import com.network.Service.MessageService;
import com.network.Action.ActionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    private ActionImpl actionImpl;

    @Autowired
    public MessageServiceImpl(ActionImpl actionImpl) {
        this.actionImpl = actionImpl;
    }

    @Override
    public Set<Message> findAll(Long userId) {
        return actionImpl.getAllMessages(userId);
    }

    @Override
    public Long send(Message message, Long userId) {
        return actionImpl.publish(message ,userId);
    }
}
