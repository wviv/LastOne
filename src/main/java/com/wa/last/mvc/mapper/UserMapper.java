package com.wa.last.mvc.mapper;


import com.wa.last.mvc.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getById(Integer id);
}
