package com.ccy.community.mapper;

import com.ccy.community.pojo.LoginTicket;
import com.ccy.community.pojo.LoginTicketExample;
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

public interface LoginTicketMapper {
    @SelectProvider(type=LoginTicketSqlProvider.class, method="countByExample")
    long countByExample(LoginTicketExample example);

    @DeleteProvider(type=LoginTicketSqlProvider.class, method="deleteByExample")
    int deleteByExample(LoginTicketExample example);

    @Delete({
        "delete from login_ticket",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into login_ticket (id, user_id, ",
        "ticket, status, ",
        "expired)",
        "values (#{id,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{ticket,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{expired,jdbcType=TIMESTAMP})"
    })
    int insert(LoginTicket record);

    @InsertProvider(type=LoginTicketSqlProvider.class, method="insertSelective")
    int insertSelective(LoginTicket record);

    @SelectProvider(type=LoginTicketSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="ticket", property="ticket", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="expired", property="expired", jdbcType=JdbcType.TIMESTAMP)
    })
    List<LoginTicket> selectByExample(LoginTicketExample example);

    @Select({
        "select",
        "id, user_id, ticket, status, expired",
        "from login_ticket",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="ticket", property="ticket", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="expired", property="expired", jdbcType=JdbcType.TIMESTAMP)
    })
    LoginTicket selectByPrimaryKey(Integer id);

    @UpdateProvider(type=LoginTicketSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LoginTicket record, @Param("example") LoginTicketExample example);

    @UpdateProvider(type=LoginTicketSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LoginTicket record, @Param("example") LoginTicketExample example);

    @UpdateProvider(type=LoginTicketSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(LoginTicket record);

    @Update({
        "update login_ticket",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "ticket = #{ticket,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "expired = #{expired,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LoginTicket record);
}