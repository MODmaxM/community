package com.ccy.community;

import com.ccy.community.dao.DiscussPostMapper;
import com.ccy.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@MapperScan("com.ccy.community.dao")
public class DiscussPostMapperTest {

    @Resource
    DiscussPostMapper discussPostMapper;

    @Test
    public void testDiscussPosts() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }

        int rows = discussPostMapper.selectDiscussPostsRows(0);
        System.out.println(rows);

    }
}
