package org.demo.redis.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public void saveUser(String id, User user) {
        redisTemplate.opsForHash().put("user:" + id, "first_name", user.getFirstName());
        // Add other fields similarly...
    }

    public String getFirstName(String id) {
        return (String) redisTemplate.opsForHash().get("user:" + id, "first_name");
    }
}
