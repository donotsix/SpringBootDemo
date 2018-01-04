package com.futurecode.springbootmybatis1.mapper;
import com.futurecode.springbootmybatis1.bean.RoncooUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// 运行时会扫描注解
//@Component
@Repository
@Mapper
public interface RoncooUserMapper {
    @Insert(value = "insert into roncoo_user (name, create_time) values (#{name,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP})")
    int insert(RoncooUser record);

    @Select(value = "select id, name, create_time from roncoo_user where id = #{id,jdbcType=INTEGER}")
    @Results(value = { @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP) })
    RoncooUser selectByPrimaryKey(Integer id);
}