package com.example.springboot_mybatis.mapper;


import com.example.springboot_mybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User getById(Integer id);
    List<User> getAll();
}
