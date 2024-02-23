package com.li.dao;

import com.li.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserDao {
    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    SysUser getByUserName(@Param("username") String username);
}
