package com.ccy.community;

import com.ccy.community.dao.UserMapper;
import com.ccy.community.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@MapperScan("com.ccy.community.dao")
//@ContextConfiguration(classes = CommunityApplication.class)
public class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void testById() {
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void testByEmail() {
        User user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testByName() {
        User user = userMapper.selectByName("liubei");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("ccy123");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("948810029@qq.com");
        user.setHeaderUrl("http://www.123.com/1.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }


    @Test
    public void testUpdateStatus() {
        userMapper.updateStatus(150, 1);
        User user = userMapper.selectById(150);
        System.out.println(user);
    }

    @Test
    public void testUpdatePassword() {
        userMapper.updatePassword(150, "asdfghjkl");
        User user = userMapper.selectById(150);
        System.out.println(user);
    }

    @Test
    public void testUpdateHeader() {
        userMapper.updateHeader(150, "http://www.cqwe.com/123.jpg");
        User user = userMapper.selectById(150);
        System.out.println(user);
    }
}
