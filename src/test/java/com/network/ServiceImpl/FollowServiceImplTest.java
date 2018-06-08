package com.network.ServiceImpl;


import com.network.Service.FollowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class FollowServiceImplTest {

    @MockBean
    FollowService followService;

    private final Long userId = 1L;
    private final Long userFollowId = 2L;


    @Test
    public void followSuccess() {
        Mockito.when(followService.follow(userId, userFollowId)).thenReturn(true);
        boolean actual = followService.follow(userId, userFollowId);
        assertTrue(actual);
    }

    @Test
    public void followFail() {
        Mockito.when(followService.follow(userFollowId, userId)).thenReturn(false);
        boolean actual = followService.follow(userFollowId, userId);
        assertFalse(actual);
    }

    @Test
    public void unFollowSuccess() {
        Mockito.when(followService.unFollow(userId, userFollowId)).thenReturn(true);
        boolean actual = followService.unFollow(userId, userFollowId);
        assertTrue(actual);
    }

    @Test
    public void unFollowFail() {
        Mockito.when(followService.unFollow(userFollowId, userId)).thenReturn(false);
        boolean actual = followService.unFollow(userFollowId, userId);
        assertFalse(actual);
    }

}