package com.futurecode.springbootmybatis2.mapper;

import com.futurecode.springbootmybatis2.bean.RoncooUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoncooUserMapper {
    int insert(RoncooUser record);
    RoncooUser selectByPrimaryKey(Integer id);
}