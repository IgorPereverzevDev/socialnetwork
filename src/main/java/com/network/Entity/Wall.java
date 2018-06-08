package com.network.Entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
public class Wall {

    @Id
    private Long userId;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Message.class)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OrderBy("dateCreation DESC ")
    private Set<Message> messages = new TreeSet<>(Comparator.comparing(Message::getDateCreation));
}
