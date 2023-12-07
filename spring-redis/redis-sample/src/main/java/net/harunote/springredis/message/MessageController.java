package net.harunote.springredis.message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodeVillains
 * @description :
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    private final RedisPublisher redisPublisher;

    public MessageController(RedisPublisher redisPublisher) {
        this.redisPublisher = redisPublisher;
    }

    @PostMapping("/{channel}")
    public ResponseEntity<String> sendMessage(@PathVariable String channel, @RequestBody String message) {

        redisPublisher.sendMessage(channel, message);
        return ResponseEntity.ok("message send to chnanel : " + channel + " and message : " + message);
    }
}
