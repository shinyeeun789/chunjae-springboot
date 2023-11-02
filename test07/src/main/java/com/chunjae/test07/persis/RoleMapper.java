package com.chunjae.test07.persis;

import com.chunjae.test07.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

// @Component : @Autowired 처럼 하나로 묶어줌
@Component
@Mapper
public interface RoleMapper {
    public Role getRoleInfo(@Param("role") String role);

}
