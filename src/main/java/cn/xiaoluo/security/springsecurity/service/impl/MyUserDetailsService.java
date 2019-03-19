package cn.xiaoluo.security.springsecurity.service.impl;

import cn.xiaoluo.security.springsecurity.entity.TbUser;
import cn.xiaoluo.security.springsecurity.service.TbUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luowenqin
 * @create 2019-03-11 16:11
 */


@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private TbUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users =null;
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("当前登录的用户名是："+username);
        TbUser user = userService.findOneByUsername(username);
        List<GrantedAuthority> grantedAuthorityList =new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("USER_SELLER"));
        users =(user !=null)?(user.getStatus().equals("1")?
                new User(username,user.getPassword(),
                        grantedAuthorityList):null):null;
        return  users;
    }
}
