package com.chunjae.test06.biz;

import com.chunjae.test06.entity.Euser;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    public PasswordEncoder passwordEncoder();
    public List<Euser> getUserList();
    Euser getUser(String name);
    int getWithdraw(Integer id);
    int getActivate(String name);
    int getDormant(String name);
    Euser getByEmail(String email);
    public Euser getByName(String name);
    Euser getUserById(Long id);
    Euser findById(String email, String tel);
    Euser findByPw(String email, String tel, String name);
    int userJoin(Euser euser);
    int updateUser(Euser euser);
    int updateLevel(String name, String lev);
    int removeUser(Euser euser);

}
