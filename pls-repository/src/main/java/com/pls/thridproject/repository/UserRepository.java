package com.pls.thridproject.repository;

import com.pls.thridproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 81046 on 2018-07-10
 */
public interface UserRepository extends JpaRepository<Users,String> {

    //根据年龄来查询用户
    List<Users> findByAge(Integer age);

}
