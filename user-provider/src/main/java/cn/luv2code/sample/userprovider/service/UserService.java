package cn.luv2code.sample.userprovider.service;

import cn.luv2code.sample.userprovider.core.BaseService;
import cn.luv2code.sample.userprovider.dto.UserDto;

import java.util.List;

public interface UserService extends BaseService<UserDto,Long> {
    List<UserDto> findAll();
}
