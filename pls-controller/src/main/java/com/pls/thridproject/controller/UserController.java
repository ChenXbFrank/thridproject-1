package com.pls.thridproject.controller;

import com.pls.thridproject.model.ResultVO;
import com.pls.thridproject.model.Users;
import com.pls.thridproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

/**
 * Created by 81046 on 2018-07-10
 *                               http://localhost:8084/getUsers
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/getUsers",produces = "application/json; charset=utf-8")
    public ResultVO getUsers(){
        List<Users> usersList = userRepository.findAll();
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(usersList);
        resultVO.setMsg("查询成功");
        return resultVO;
    }

    @PostMapping(value = "/saveUser",produces = "application/json; charset=utf-8")
    public ResultVO saveUser(@RequestParam("name") String name,
                          @RequestParam("age") Integer age){
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        Users users = userRepository.save(user);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(users);
        resultVO.setMsg("保存成功");
        return resultVO;
    }

}
