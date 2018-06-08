package com.network.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class Message {
    @Id
    @GenericGenerator(name = "keygen", strategy = "increment")
    @GeneratedValue(generator = "keygen")
    private Long messageId;

    @Length(max = 140)
    private String text;

    @Column
    private LocalDateTime dateCreation = LocalDateTime.now();

}