package com.network.Repository;

import com.network.Entity.Wall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Wall, Long> {}
