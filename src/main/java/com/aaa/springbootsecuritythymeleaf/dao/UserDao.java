package com.aaa.springbootsecuritythymeleaf.dao;

import com.aaa.springbootsecuritythymeleaf.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息持久层
 * @author 淮南King
 */
@Mapper
public interface UserDao {
    /**
     * 根据账号查询用户信息
     *
     * @param username 用户姓名
     * @return 用户信息
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id查询用户权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<String> findPermissionsByUserId(String userId);

    /**
     * 添加用户
     *
     * @param userDTO 用户信息
     * @return 修改条数
     */
    int addUser(User userDTO);
}
