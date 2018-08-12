package cn.luv2code.sample.userprovider.controller;

import cn.luv2code.sample.userprovider.dao.UserRepository;
import cn.luv2code.sample.userprovider.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }
}
