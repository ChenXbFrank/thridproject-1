package com.pls.thridproject.service;

import com.pls.thridproject.dao.UsersDao;
import com.pls.thridproject.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 81046 on 2018-07-11
 */
@Service
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    /**
     *  根据名字查询用户集合
     * @param name
     * @return
     */
    public List<Users> getByName(String name){
        return usersDao.getByName(name);
    }

    /**
     * 根据id查询用户的名称
     * @param id
     * @return
     */
    public String getNameById(String id){
        return usersDao.getNameById(id);
    }

    /**
     * 保存用户对象
     */
    public void save(Users users){
        usersDao.save(users);
    }
}
