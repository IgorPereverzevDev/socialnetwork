package com.network.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.network.Entity.Message;
import com.network.Service.FollowService;
import com.network.Service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SocialNetworkController.class)
public class SocialNetworkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService messageService;

    @MockBean
    FollowService followService;

    @Autowired
    ObjectMapper objectMapper;

    private final Long userId = 1L;
    private final Long userFollowId = 2L;


    @Test
    public void sendSuccess() throws Exception {
        Message message = new Message();
        message.setText("Test");
        Mockito.when(messageService.send(message, userId)).thenReturn(userId);
        mockMvc.perform(post("/send")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId","1")
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    public void sendFail() throws Exception {
        Message message = new Message();
        message.setText(new String(new char[150]).replace('\0', 'a'));
        Mockito.when(messageService.send(message, userId)).thenReturn(null);
        mockMvc.perform(post("/send")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId","1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void followSuccess() throws Exception {
        Mockito.when(followService.follow(userId, userFollowId)).thenReturn(true);
        mockMvc.perform(post("/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId","1")
                .param("userFollowId","2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void followFail() throws Exception {
        Mockito.when(followService.follow(userFollowId, userId)).thenReturn(false);
        mockMvc.perform(post("/follow")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void unFollowSuccess() throws Exception {
        Mockito.when(followService.unFollow(userId, userFollowId)).thenReturn(true);
        mockMvc.perform(post("/unfollow")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId","1")
                .param("userUnFollowId","2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void unFollowFail() throws Exception {
        Mockito.when(followService.unFollow(userFollowId, userId)).thenReturn(false);
        mockMvc.perform(post("/unfollow")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAll() throws Exception {

        Message message = new Message();
        message.setText("Test");

        Set<Message> set = new HashSet<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Message messageOne = new Message();
        messageOne.setText("Test");
        messageOne.setDateCreation(LocalDateTime.parse("2016-11-09 10:30", formatter));

        Message messageTwo = new Message();
        messageTwo.setText("Test1");
        messageTwo.setDateCreation(LocalDateTime.parse("2017-11-09 10:30", formatter));

        set.add(messageTwo);
        set.add(messageOne);

        Mockito.when(messageService.findAll(userId)).thenReturn(set);

        MockHttpServletResponse response = mockMvc.perform(get("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId","1"))
                .andExpect(status().isFound())
                .andReturn()
                .getResponse();
        String expected = "Test1";
        assertFalse(response.getContentAsString().isEmpty());
        assertThat(response.getContentAsString().lastIndexOf(expected), not(0));
    }

}
