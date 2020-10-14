package com.mzx.springbootweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    @Select({"select name from account where id = #{id}"})
    String getAccountNameById(String id);
    @Select({"select id from account where name = #{name}"})
    String getAccountIdByName(String name);
}
