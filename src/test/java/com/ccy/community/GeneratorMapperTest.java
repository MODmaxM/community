package com.ccy.community;

import com.ccy.community.mapper.UserMapper;
import com.ccy.community.mapper.UserSqlProvider;
import com.ccy.community.pojo.User;
import com.ccy.community.pojo.UserExample;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
@MapperScan("com.ccy.community.mapper")
public class GeneratorMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUser() {

        UserExample example = new UserExample();
        example.createCriteria().andIdBetween(100, 110);
        List<User> users = userMapper.selectByExample(example);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("ccy");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("948810029@qq.com");
        user.setHeaderUrl("http://www.123.com/1.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insert(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate() {
        UserSqlProvider provider = new UserSqlProvider();
    }


    @Test
    public void testDelete() {
        int key = userMapper.deleteByPrimaryKey(151);
        System.out.println(key);
    }
}
