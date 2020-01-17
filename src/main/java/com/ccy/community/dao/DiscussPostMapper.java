package com.ccy.community.dao;

import com.ccy.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param给参数取别名，查询SQL时就按照别名查，拼接动态SQL时如果只有一个参数，必须加上别名
    int selectDiscussPostsRows(@Param("userId") int userId);

}
