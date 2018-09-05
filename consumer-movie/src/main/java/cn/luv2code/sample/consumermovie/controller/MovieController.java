package cn.luv2code.sample.consumermovie.controller;

import cn.luv2code.sample.consumermovie.entity.User;
import cn.luv2code.sample.consumermovie.feign.UserFeignClient;
import cn.luv2code.sample.consumermovie.utils.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @Resource
    private UserFeignClient userFeignClient;

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("user/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return this.userFeignClient.findById(id);
    }

    /**
     * when execute hystrix
     * @param id
     * @return default value
     */
     public Result findByIdFallback(Long id) {
         Result result = new Result();
         User user = new User();
         user.setId(-1L);
         user.setName("default");
         result.setData(user);
         LOGGER.error(user.toString());
         return result;
    }

    /**
     *
     * @return details of user-provider
     */
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        //conclude metadata in user-provider yml
        return this.discoveryClient.getInstances("user-provider");
    }

    /**
     * @return RibbonServer{serviceId='user-provider', server=192.168.27.103:8001, secure=false, metadata=vlsi.utils.CompactHashMap@35e0a7eb}
     */
    @GetMapping("/user-instance-log")
    public String showInfoLog() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider");
        LOGGER.error("{}:{}:{}", serviceInstance.getPort(), serviceInstance.getHost(), serviceInstance.getServiceId());
        return serviceInstance.toString();
    }
}
