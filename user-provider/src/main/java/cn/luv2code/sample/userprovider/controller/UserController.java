package cn.luv2code.sample.userprovider.controller;

import cn.luv2code.sample.userprovider.dao.UserRepository;
import cn.luv2code.sample.userprovider.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }
}
