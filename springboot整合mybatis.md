



本文所使用的数据库表结构如图所示

![image-20210322170327231](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322170327231.png)



项目最终的目录结构如下：

![image-20210322170600872](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322170600872.png)





==新建一个springboot项目==

![image-20210322162531495](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322162531495.png)



==引入maven依赖==

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.1</version>
</dependency>

<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.46</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

此处引入lombok， 纯粹为了偷懒



==编写application.yml==

```yaml
spring:
  profiles:
    active: dev
```



==编写application-dev.yml==

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

mybatis:
  mapper-locations: classpath:mapper/*Mapper.xml

```

==编写User实体类==

![image-20210322171156312](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322171156312.png)

```java
package com.example.springboot_mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
}

```



==编写UserMapper接口==

![image-20210322171208776](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322171208776.png)

```java
package com.example.springboot_mybatis.mapper;

import com.example.springboot_mybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User getById(Integer id);
    List<User> getAll();
}

```



==编写UserMapper.xml==

![image-20210322171227594](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322171227594.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.springboot_mybatis.mapper.UserMapper">

    <select id="getById" resultType="com.example.springboot_mybatis.entity.User">
        select * from user where id = #{id}
    </select>
    <select id="getAll" resultType="com.example.springboot_mybatis.entity.User">
        select * from user
    </select>

</mapper>
```



==在启动类中添加MapperScan注解==，需要指明我们刚刚新建的UserMapper接口所在的包【此注解也可单独放在一个配置类中，为了简单起见，此处之间加在了启动类上】

![image-20210322170725070](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322170725070.png)



在测试类中编写测试代码

![image-20210322171038022](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322171038022.png)



```java
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
```



ok，测试完毕：

![image-20210322171108466](D:\My\2021\dev\java\springboot\springboot整合mybatis.assets\image-20210322171108466.png)