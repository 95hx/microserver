package cn.luv2code.sample.consumermovie.feign;

import cn.luv2code.sample.userprovider.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * build ribbon balance
 */
@FeignClient(name = "user-provider")
public interface UserFeignClient {
    @GetMapping("user/{id}")
    Result findById(@PathVariable("id") Long id);

//    /**
//     * @param id
//     * @return User
//     * @discription using feign contract need create feign contract bean
//     */
//    @RequestLine("GET/{id}")
//    public User findById(@Param("id") Long id);
}
