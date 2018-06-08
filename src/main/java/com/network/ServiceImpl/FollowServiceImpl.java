package com.network.ServiceImpl;

import com.network.Service.FollowService;
import com.network.Action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {

    private Action action;

    @Autowired
    public FollowServiceImpl(Action action) {
        this.action = action;
    }

    @Override
    public boolean follow(Long userId, Long userFollowId) {
        return action.follow(userId, userFollowId);
    }

    @Override
    public boolean unFollow(Long userId, Long userUnFollowId) {
        return action.unFollow(userId, userUnFollowId);
    }
}
