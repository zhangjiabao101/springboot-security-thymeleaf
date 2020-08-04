package com.aaa.springbootsecuritythymeleaf.service;

import com.aaa.springbootsecuritythymeleaf.dao.UserDao;
import com.aaa.springbootsecuritythymeleaf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息持久业务层
 *
 * @author 淮南King
 * @date 2020-07-21
 */
@Service
public class UserService {

    @Autowired UserDao dao;

    public User getUserByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    public List<String> findPermissionsByUserId(String userId) {
        return dao.findPermissionsByUserId(userId);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(User user) {
        //获取密码编码器
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //将用户的密码进行编码
        String password = passwordEncoder.encode(user.getPassword());
        //将编码后的密码覆盖到用户信息中
        user.setPassword(password.substring(8));
        //将用户信息持久化到数据库中
        return dao.addUser(user);
    }
}
