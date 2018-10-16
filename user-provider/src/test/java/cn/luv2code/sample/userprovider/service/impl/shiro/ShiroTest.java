package cn.luv2code.sample.userprovider.service.impl.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest()
@WebAppConfiguration
public class ShiroTest {
    @Resource
    DefaultSecurityManager manager;

    @Test
    public void test0() {
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken admin = new UsernamePasswordToken("admin", "111111");
        subject.login(admin);
        subject.checkRole("admin");
        subject.checkPermissions("user:*");
        System.out.println(subject.isAuthenticated());
    }

    @Test
    public void test1() {
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken admin = new UsernamePasswordToken("hx", "111111");
        subject.login(admin);

        subject.checkRole("user");
//        subject.checkPermission("asdas");
        subject.checkPermission("user:list");
        System.out.println(subject.isAuthenticated());
    }
}
