package com.ccy.community.mapper;

import com.ccy.community.pojo.Comment;
import com.ccy.community.pojo.CommentExample;
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

public interface CommentMapper {
    @SelectProvider(type=CommentSqlProvider.class, method="countByExample")
    long countByExample(CommentExample example);

    @DeleteProvider(type=CommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(CommentExample example);

    @Delete({
        "delete from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into comment (id, parentId, ",
        "type, commentor, ",
        "gmtCreate, gmtModified, ",
        "likeCount, content)",
        "values (#{id,jdbcType=INTEGER}, #{parentid,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, #{commentor,jdbcType=VARCHAR}, ",
        "#{gmtcreate,jdbcType=BIGINT}, #{gmtmodified,jdbcType=BIGINT}, ",
        "#{likecount,jdbcType=BIGINT}, #{content,jdbcType=VARCHAR})"
    })
    int insert(Comment record);

    @InsertProvider(type=CommentSqlProvider.class, method="insertSelective")
    int insertSelective(Comment record);

    @SelectProvider(type=CommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="parentId", property="parentid", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="commentor", property="commentor", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmtCreate", property="gmtcreate", jdbcType=JdbcType.BIGINT),
        @Result(column="gmtModified", property="gmtmodified", jdbcType=JdbcType.BIGINT),
        @Result(column="likeCount", property="likecount", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR)
    })
    List<Comment> selectByExample(CommentExample example);

    @Select({
        "select",
        "id, parentId, type, commentor, gmtCreate, gmtModified, likeCount, content",
        "from comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="parentId", property="parentid", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="commentor", property="commentor", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmtCreate", property="gmtcreate", jdbcType=JdbcType.BIGINT),
        @Result(column="gmtModified", property="gmtmodified", jdbcType=JdbcType.BIGINT),
        @Result(column="likeCount", property="likecount", jdbcType=JdbcType.BIGINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR)
    })
    Comment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Comment record);

    @Update({
        "update comment",
        "set parentId = #{parentid,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "commentor = #{commentor,jdbcType=VARCHAR},",
          "gmtCreate = #{gmtcreate,jdbcType=BIGINT},",
          "gmtModified = #{gmtmodified,jdbcType=BIGINT},",
          "likeCount = #{likecount,jdbcType=BIGINT},",
          "content = #{content,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);
}