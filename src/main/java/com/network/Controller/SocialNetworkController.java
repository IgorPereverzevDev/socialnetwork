package com.network.Controller;

import com.network.Entity.Message;
import com.network.Service.FollowService;
import com.network.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class SocialNetworkController {

    private MessageService messageService;
    private FollowService followService;

    @Autowired
    public SocialNetworkController(MessageService messageService, FollowService followService) {
        this.messageService = messageService;
        this.followService = followService;
    }

    @CrossOrigin
    @PostMapping(value = "/send")
    public ResponseEntity send(@RequestBody @Valid Message message, @RequestParam Long userId) {
        return message != null ? new ResponseEntity<>(messageService.send(message, userId), HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @PostMapping(value = "/follow")
    public ResponseEntity follow(@RequestParam Long userId, @RequestParam Long userFollowId) {
        return userId != null ? new ResponseEntity<>(followService.follow(userId, userFollowId), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @PostMapping(value = "/unfollow")
    public ResponseEntity unFollow(@RequestParam Long userId, @RequestParam Long userUnFollowId) {
        return userId != null ? new ResponseEntity<>(followService.unFollow(userId, userUnFollowId), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @GetMapping(value = "/messages")
    public ResponseEntity<Set<Message>> getAll(@RequestParam Long userId) {
        return userId != null ? new ResponseEntity<>(messageService.findAll(userId), HttpStatus.FOUND) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
