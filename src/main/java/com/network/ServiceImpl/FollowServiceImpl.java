package com.network.ServiceImpl;

import com.network.Service.FollowService;
import com.network.Action.ActionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private ActionImpl actionImpl;

    @Autowired
    public FollowServiceImpl(ActionImpl actionImpl) {
        this.actionImpl = actionImpl;
    }

    @Override
    public boolean follow(Long userId, Long userFollowId) {
        return actionImpl.follow(userId, userFollowId);
    }

    @Override
    public boolean unFollow(Long userId, Long userUnFollowId) {
        return actionImpl.unFollow(userId, userUnFollowId);
    }
}
