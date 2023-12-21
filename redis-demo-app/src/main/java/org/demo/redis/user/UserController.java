package org.demo.redis.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestParam String id, @RequestBody User user) {
        userService.saveUser(id, user);
    }

    @GetMapping("/{id}")
    public String getFirstName(@PathVariable String id) {
        return userService.getFirstName(id);
    }
}
