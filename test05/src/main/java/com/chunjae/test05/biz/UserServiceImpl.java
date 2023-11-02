package com.chunjae.test05.biz;

import com.chunjae.test05.entity.Euser;
import com.chunjae.test05.per.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Euser> getUserList() throws Exception {
        return userMapper.getUserList();
    }

    @Override
    public Euser getUser(String name) throws Exception {
        return userMapper.getUser(name);
    }

    @Override
    public int getWithdraw(String name) {
        return userMapper.getWithdraw(name);
    }

    @Override
    public void getActivate(String name) {
        userMapper.getActivate(name);
    }

    @Override
    public void getDormant(String name) {
        userMapper.getDormant(name);
    }

    @Override
    public Euser getByEmail(String email) {
        return userMapper.getByEmail(email);
    }

    @Override
    public Euser getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public Euser findById(String email, String tel) {
        return userMapper.findById(email, tel);
    }

    @Override
    public Euser findByPw(String email, String tel, String name) {
        return userMapper.findByPw(email, tel, name);
    }

    @Override
    public void join(Euser euser) {
        euser.setPassword(pwEncoder.encode(euser.getPassword()));
        userMapper.join(euser);
    }

    @Override
    public void userEdit(Euser euser) {
        if(euser.getPassword() == null) {
            Euser before = userMapper.getUser(euser.getName());
            euser.setPassword(before.getPassword());
        }
        userMapper.userEdit(euser);
    }

    @Override
    public void userLevEdit(Euser euser) {
        userMapper.userLevEdit(euser);
    }
}
