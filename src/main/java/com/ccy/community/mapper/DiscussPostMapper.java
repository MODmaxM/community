package com.ccy.community.mapper;

import com.ccy.community.pojo.DiscussPost;
import com.ccy.community.pojo.DiscussPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DiscussPostMapper {
    @SelectProvider(type=DiscussPostSqlProvider.class, method="countByExample")
    long countByExample(DiscussPostExample example);

    @DeleteProvider(type=DiscussPostSqlProvider.class, method="deleteByExample")
    int deleteByExample(DiscussPostExample example);

    @Delete({
        "delete from discuss_post",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into discuss_post (id, user_id, ",
        "title, type, status, ",
        "create_time, comment_count, ",
        "score, content)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{commentCount,jdbcType=INTEGER}, ",
        "#{score,jdbcType=DOUBLE}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(DiscussPost record);

    @InsertProvider(type=DiscussPostSqlProvider.class, method="insertSelective")
    int insertSelective(DiscussPost record);

    @SelectProvider(type=DiscussPostSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.DOUBLE),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<DiscussPost> selectByExampleWithBLOBs(DiscussPostExample example);

    @SelectProvider(type=DiscussPostSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.DOUBLE)
    })
    List<DiscussPost> selectByExample(DiscussPostExample example);

    @Select({
        "select",
        "id, user_id, title, type, status, create_time, comment_count, score, content",
        "from discuss_post",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="comment_count", property="commentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="score", property="score", jdbcType=JdbcType.DOUBLE),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    DiscussPost selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DiscussPostSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DiscussPost record, @Param("example") DiscussPostExample example);

    @UpdateProvider(type=DiscussPostSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") DiscussPost record, @Param("example") DiscussPostExample example);

    @UpdateProvider(type=DiscussPostSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DiscussPost record, @Param("example") DiscussPostExample example);

    @UpdateProvider(type=DiscussPostSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DiscussPost record);

    @Update({
        "update discuss_post",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "comment_count = #{commentCount,jdbcType=INTEGER},",
          "score = #{score,jdbcType=DOUBLE},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(DiscussPost record);

    @Update({
        "update discuss_post",
        "set user_id = #{userId,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "comment_count = #{commentCount,jdbcType=INTEGER},",
          "score = #{score,jdbcType=DOUBLE}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DiscussPost record);


    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param用于给列名取别名，动态拼接sql文件时如果只有一个参数，就必须加上别名@Param("xxx")
    @Select({"select",
            "id, user_id, title, type, status, create_time, comment_count, score, content",
            "from discuss_post",
            "where status != 2 and #{id,jdbcType=INTEGER}"
    })
    int selectDiscussPostsRows(@Param("userId") int userId);
}