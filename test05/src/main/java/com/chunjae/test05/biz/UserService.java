package com.chunjae.test05.biz;

import com.chunjae.test05.entity.Euser;

import java.util.List;

public interface UserService {
    public List<Euser> getUserList() throws Exception;
    public Euser getUser(String name) throws Exception;
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
