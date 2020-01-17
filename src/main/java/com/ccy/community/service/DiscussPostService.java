package com.ccy.community.service;

import com.ccy.community.dao.DiscussPostMapper;
import com.ccy.community.entity.DiscussPost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscussPostService {

    @Resource
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        // 业务层可能会有别的处理，所以尽管只是简单的调用mapper，也不能省掉service层，
        // 并且不能跨层调用，controller调用service，Service调用dao
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostsRows(int userId) {
        return discussPostMapper.selectDiscussPostsRows(userId);
    }
}
