package cn.luv2code.sample.userprovider.service;

import cn.luv2code.sample.userprovider.core.BaseService;
import cn.luv2code.sample.userprovider.dto.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends BaseService<UserDto,Long> {
    List<UserDto> findAll();
    List<UserDto> findAll(Pageable pageable);
}
