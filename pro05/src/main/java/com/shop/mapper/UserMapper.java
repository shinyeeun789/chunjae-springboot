package com.shop.mapper;

import com.shop.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);
    List<User> findAll();
    User findById(Long id);
    User findByUsername(Long userName);
    User getLatestUser();
    void update(User user);

}
