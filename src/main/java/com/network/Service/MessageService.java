package com.network.Service;

import com.network.Entity.Message;

import java.util.Set;

public interface MessageService {
    /**
     * @param userId must not be {@literal null}.
     * @return Returns the {@link Set} messages.
     */
    Set<Message> findAll(Long userId);

    /**
     * @param message must not be {@literal null}.
     * @param userId  must not be {@literal null}.
     * @return Returns unique id Message
     */
    Long send(Message message, Long userId);

}
