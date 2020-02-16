package com.ccy.community.service;

import com.ccy.community.dao.DiscussPostMapper;
import com.ccy.community.entity.DiscussPost;
import com.ccy.community.util.SensitiveFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscussPostService {

    @Resource
    private DiscussPostMapper discussPostMapper;

    @Resource
    private SensitiveFilter sensitiveFilter;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        // 业务层可能会有别的处理，所以尽管只是简单的调用mapper，也不能省掉service层，
        // 并且不能跨层调用，controller调用service，Service调用dao
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostsRows(int userId) {
        return discussPostMapper.selectDiscussPostsRows(userId);
    }

    public int addDiscussPost(DiscussPost post) {
        if (post == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        // 转义html标签
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        // 过滤敏感词
        post.setTitle(sensitiveFilter.filter(post.getTitle()));
        post.setContent(sensitiveFilter.filter(post.getContent()));

        return discussPostMapper.insertDiscussPost(post);
    }

    public DiscussPost findDiscussPostByd(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    public int updateCommentCount(int id, int commentCount) {
        return discussPostMapper.updateCommentCount(id, commentCount);
    }
}
