package cn.luv2code.sample.userprovider.controller;

import cn.luv2code.sample.userprovider.annotation.TargetDataSource;
import cn.luv2code.sample.userprovider.dto.UserDto;
import cn.luv2code.sample.userprovider.service.UserService;
import cn.luv2code.sample.userprovider.utils.Result;
import cn.luv2code.sample.userprovider.utils.ResultStatus;
import cn.luv2code.sample.userprovider.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    /**
     * 查
     */
    @TargetDataSource("ds1")
    @GetMapping("/{id}")
    public Result<UserDto> findById(@PathVariable Long id) {
        return ResultUtils.success(userService.findById(id));
    }

    /**
     * 查
     */
    @TargetDataSource("ds2")
    @GetMapping("/all")
    public Result findAll() {
        return ResultUtils.success(userService.findAll());
    }

    /**
     * 查
     */
    @GetMapping("/all/{page}/{size}")
    public Result findPageAll(@PathVariable Integer page, @PathVariable Integer size) {
        return ResultUtils.success(userService.findAll(new PageRequest(page+1, size)));
    }

    /**
     * 增/更新
     */
    @PostMapping("/add")
    public Result add(@Valid @RequestBody UserDto  userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultUtils.error(ResultStatus.UNKNOWN_ERROR);
        userService.save(userDto);
        return ResultUtils.success();
    }

    /**
     * 删
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResultUtils.success();
    }
}
