package com.chunjae.test05.per;

import com.chunjae.test05.entity.Euser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<Euser> getUserList();
    public Euser getUser(String name);
    public int getWithdraw(String name);
    public void getActivate(String name);
    public void getDormant(String name);
    public Euser getByEmail(String email);
    public Euser getByName(String name);
    public Euser findById(String email, String tel);
    public Euser findByPw(String email, String tel, String name);
    public void join(Euser euser);
    public void userEdit(Euser euser);
    public void userLevEdit(Euser euser);

}
