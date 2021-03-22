package com.example.springboot_mybatis;

import com.example.springboot_mybatis.entity.User;
import com.example.springboot_mybatis.mapper.UserMapper;
import org.apache.ibatis.ognl.DynamicSubscript;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getAll(){
        List<User> users = userMapper.getAll();

        System.out.println("查询所有：");
        System.out.println(users);

        System.out.println("根据id查询：");
        User byId = userMapper.getById(1);
        System.out.println(byId);
    }

}
