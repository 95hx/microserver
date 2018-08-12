package cn.luv2code.sample.consumermovie.controller;

import cn.luv2code.sample.consumermovie.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://user-provider/user/" + id, User.class);
    }
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        //conclude metadata in user-provider yml
        return this.discoveryClient.getInstances("user-provider");
    }
    @GetMapping("/user-instance-log")
    public Integer  showInfoLog() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider");
        LOGGER.error("{}:{}:{}",serviceInstance.getPort(),serviceInstance.getHost(),serviceInstance.getServiceId());
        return serviceInstance.getPort();
    }
}
