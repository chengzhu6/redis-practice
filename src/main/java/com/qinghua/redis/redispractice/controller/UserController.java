package com.qinghua.redis.redispractice.controller;

import com.qinghua.redis.redispractice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {

    private final RedisTemplate<Long, Long> customRedisTemplate;

    @PostMapping("/users/{userId}/friends")
    public void addFriends(@PathVariable Long userId, @RequestBody User newFriend) {
        customRedisTemplate.opsForSet()
                .add(userId, newFriend.getId());
    }

    @DeleteMapping("/users/{userId}/friends/{friendId}")
    public void deleteFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        customRedisTemplate.opsForSet()
                .remove(userId, friendId);
    }

    @GetMapping("/users/{userId}/friends")
    public Set<Long> allFriends(@PathVariable Long userId) {
        return customRedisTemplate.opsForSet()
                .members(userId);
    }

    @GetMapping("/users/{userId}/common-friends/{friendId}")
    public Set<Long> commonFriends(@PathVariable Long userId, @PathVariable Long friendId) {
        return customRedisTemplate.opsForSet()
                .intersect(userId, friendId);
    }

    @GetMapping("/users/{userId}/unique-friends/{friendId}")
    public Set<Long> uniqueFriends(@PathVariable Long userId, @PathVariable Long friendId) {
        return customRedisTemplate.opsForSet()
                .difference(userId, friendId);
    }
}
