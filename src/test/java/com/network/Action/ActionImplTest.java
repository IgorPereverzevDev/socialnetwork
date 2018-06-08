package com.network.Action;

import com.network.Entity.Message;
import com.network.Entity.Wall;
import com.network.Service.FollowService;
import com.network.Service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static junit.framework.Assert.*;

@RunWith(SpringRunner.class)
public class ActionImplTest {

    @MockBean
    MessageService messageService;

    @MockBean
    FollowService followService;

    private final Long userId = 1L;
    private final Long userFollowId = 2L;

    @Test
    public void publish() {
        Wall wall = new Wall();
        wall.setUserId(userId);
        Message message = new Message();

        message.setText("Test");
        wall.getMessages().add(message);

        Mockito.when(messageService.send(message, userId)).thenReturn(userId);

        Long actual = messageService.send(message, userId);

        assertEquals(userId, actual);

    }

    @Test
    public void getAllMessages() {
        Wall wall = new Wall();
        wall.setUserId(userId);
        Message message = new Message();

        message.setText("Test");
        wall.getMessages().add(message);

        Mockito.when(messageService.findAll(userId)).thenReturn(wall.getMessages());

        Set<Message> actual = messageService.findAll(userId);

        assertTrue(actual.contains(message));
    }

    @Test
    public void followSuccess() {
        Mockito.when(followService.follow(userId, userFollowId)).thenReturn(true);
        boolean actual = followService.follow(userId, userFollowId);
        assertTrue(actual);
    }

    @Test
    public void followFail() {
        followService.follow(userId, userFollowId);
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
        followService.unFollow(userId, userFollowId);
        boolean actual = followService.unFollow(userId, userFollowId);
        assertFalse(actual);
    }
}