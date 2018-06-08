package com.network.ServiceImpl;

import com.network.Entity.Message;
import com.network.Service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@DataJpaTest
public class MessageServiceImplTest {


    @MockBean
    private MessageService messageService;

    private final Long value = 1L;

    @Test
    public void findAll() {
        Set<Message> expected = new HashSet<>();
        Message message = new Message();
        message.setText("Test");

        expected.add(message);

        Mockito.when(messageService.findAll(value)).thenReturn(expected);

        Set<Message> actual = messageService.findAll(value);

        assertTrue(actual.contains(message));
    }

    @Test
    public void send() {
        Message message = new Message();
        message.setText("Test");

        Mockito.when(messageService.send(message, value)).thenReturn(value);

        Long actualId = messageService.send(message, value);

        assertEquals(value, actualId);

    }
}
