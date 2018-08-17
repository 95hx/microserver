package cn.luv2code.sample.userprovider.controller;

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
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    /**
     * 查
     */
    @GetMapping("/{id}")
    public Result<UserDto> findById(@PathVariable Long id) {
        return ResultUtils.success(userService.findById(id));
    }

    /**
     * 查
     */
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
     * 增
     */
    @PostMapping("/add")
    public Result add(@Valid UserDto userDto, BindingResult bindingResult) {
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

    /**
     * 改
     */
    @PutMapping
    public Result updateById(@Valid UserDto userDto, BindingResult bindingResult) {
        return add(userDto, bindingResult);
    }
}
