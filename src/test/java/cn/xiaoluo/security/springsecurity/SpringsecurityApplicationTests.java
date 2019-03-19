package cn.xiaoluo.security.springsecurity;

import cn.xiaoluo.security.springsecurity.entity.TbUser;
import cn.xiaoluo.security.springsecurity.properties.BrowserProperties;
import cn.xiaoluo.security.springsecurity.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringsecurityApplicationTests {

    private  Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TbUserService userService;



    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    private BrowserProperties browserProperties;


    @Test
    public void contextLoads() {
        TbUser user = userService.findOne(1);
        logger.info("用户名是："+user);

        String password = user.getPassword();
        boolean matches = passwordEncoder.matches("xiaoluo301817", password);

        logger.info("当前验证的："+matches);
        TbUser tbUser = userService.findOneByUsername("xiaoluo");

        logger.info("当前验证的tbUser："+tbUser);


        String loginPage = browserProperties.getLoginPage();
        logger.info("当前验证的登录页："+loginPage);

    }

}
