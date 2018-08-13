package cn.luv2code.sample.userprovider.controller;

import cn.luv2code.sample.userprovider.dao.UserRepository;
import cn.luv2code.sample.userprovider.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }
}
