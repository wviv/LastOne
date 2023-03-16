package com.wa.last.mvc.service.impl;

import com.wa.last.mvc.pojo.entity.User;
import com.wa.last.mvc.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author viv
 * @since 2020-06-07
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserMapper userMapper;
    
    /**
     * 用户信息
     * @param id
     * @return
     */
    public User info(Integer id) {
        User u1 = userMapper.getById(id);
        User u2 = userMapper.getById(id);
        User u3 = userMapper.getById(id);
        return u3;
    }
}
