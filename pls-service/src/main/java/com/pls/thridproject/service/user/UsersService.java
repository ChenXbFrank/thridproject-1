package com.pls.thridproject.service.user;

import com.pls.thridproject.dao.UsersDao;
import com.pls.thridproject.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by 81046 on 2018-07-11
 */
@Service
public class UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RedisTemplate redisTemplate;

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
    @Transactional
    public void save(Users users){
        usersDao.save(users);
    }

    /**
     * 根据年龄查询单个对象
     * @param id
     * @return
     *         localhost:8084/user/getUserById?id=poe45655aa9c612y989tpof33a56f80bw
     */
    public Users findUserById(String id){
        // 从缓存中获取Users信息
        String key = id;
        ValueOperations<String, Users> operations = redisTemplate.opsForValue();

        // 缓存存在  直接返回
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            Users user = operations.get(key);
            LOGGER.info("UsersService.findUserById() : 从缓存中获取了Users >> " + user.toString());
            return user;
        }

        // 从 DB 中获取accesstoken信息
        Users user = usersDao.findUserById(id);
        // 插入缓存
        operations.set(key, user, 1000, TimeUnit.SECONDS);
        LOGGER.info("UsersService.findUserById() : Users插入缓存 >> " + user.toString());
        return user;
    }

    /**
     * 保存用户对象
     * @param users
     */
    @Transactional
    public void insertUser(Users users){
        usersDao.insertUser(users);
    }

    /**
     * 根据名字查询集合对象
     * @param name
     * @return
     */
    public List<Users> selectUsersByName(String name){
        return usersDao.selectUsersByName(name);
    }
}
