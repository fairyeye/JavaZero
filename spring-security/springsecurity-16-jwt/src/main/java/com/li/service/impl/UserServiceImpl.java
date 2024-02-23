package com.li.service.impl;

import com.li.dao.SysMenuDao;
import com.li.dao.SysUserDao;
import com.li.entity.SysUser;
import com.li.vo.SecurityUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.getByUserName(username);
        if (Objects.isNull(sysUser)) {
            throw new UsernameNotFoundException("账号不存在");
        }
        List<String> strList = sysMenuDao.queryPermissionByUserId(sysUser.getUserId());
        //使用stream流来转换
        List<SimpleGrantedAuthority> grantedAuthorities = strList.stream().map(SimpleGrantedAuthority::new).collect(toList());

        SecurityUser securityUser = new SecurityUser(sysUser);
        securityUser.setSimpleGrantedAuthorities(grantedAuthorities);
        return securityUser;
    }
}
