package cn.luv2code.sample.userprovider.service.impl;

import cn.luv2code.sample.userprovider.dao.UserDao;
import cn.luv2code.sample.userprovider.dto.UserDto;
import cn.luv2code.sample.userprovider.entity.User;
import cn.luv2code.sample.userprovider.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    /**
     * 查
     * @param id 主键
     * @return UserDto
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        Optional<User> optional = userDao.findById(id);
        User user = optional.orElseThrow(EntityNotFoundException::new);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    /**
     * 查
     * @return List<UserDto>
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(e -> dozerBeanMapper.map(e, UserDto.class))
                .collect(Collectors.toList());
    }

    /**
     * 增
     * @param userDto
     */
    @Override
    @Transactional(readOnly = false)
    public void save(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userDao.save(user);
    }

    /**
     * 删
     * @param id 主键
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }


}
