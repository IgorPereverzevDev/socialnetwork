package com.network.Entity;


import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class User {

    @Id
    private Long id;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Map<Long, Wall> followUsersWalls = new HashMap<>();

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Map<Long, Wall> refToUser = new HashMap<>();

}
