package com.pls.thridproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pls.thridproject.model.ResultVO;
import com.pls.thridproject.model.Users;
import com.pls.thridproject.repository.UserRepository;
import com.pls.thridproject.service.user.UsersService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by 81046 on 2018-07-10
 *                            测试访问链接： http://localhost:8084/user/getUsers
 * 页面跳转不能使用 @RestController  而是使用@Controller
 *   @CrossOrigin 该注解解决跨域问题的
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService usersService;

    /**
     * 查询所有的对象 repository
     * @return
     * @CrossOrigin(origins = "http://localhost:8084")   也可以加在具体的某个方法上面的
     */
    @GetMapping(value = "/getUsers",produces = "application/json; charset=utf-8")
    public ResultVO getUsers(){
        List<Users> usersList = userRepository.findAll();
        //返回集合对象给页面
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(usersList);
        resultVO.setMsg("查询成功");
        return resultVO;
    }

    /**
     * 保存用户对象 repository
     * @param name
     * @param age
     * @return
     */
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

    /**
     * 根据名字查询用户对象集合  dao-mybatis-注解
     * @param name
     * @return
     */
    @PostMapping(value = "/getByName",produces = "application/json; charset=utf-8")
    public ResultVO getByName(@RequestParam("name") String name){
        List<Users> usersList = usersService.getByName(name);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(usersList);
        resultVO.setMsg("查询成功");
        return resultVO;
    }

    /**
     * 保存用户对象  dao-mybatis-注解
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/save",produces = "application/json; charset=utf-8")
    public ResultVO save(@RequestParam("name") String name,
                         @RequestParam("age") Integer age){
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        Users user = new Users();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        usersService.save(user);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(user);
        resultVO.setMsg("保存 "+name+" 成功");
        return resultVO;
    }

    /**
     *  根据ID查询单个对象
     */
    @PostMapping(value = "/getNameById",produces = "application/json; charset=utf-8")
    public ResultVO getNameById(@RequestParam("id") String id){
        String name = usersService.getNameById(id);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(name);
        resultVO.setMsg("根据ID查询 "+name+" 成功");
        return resultVO;
    }

    /**
     *  根据ID查询单个对象  dao-mybatis-配置
     */
    @PostMapping(value = "/getUserById",produces = "application/json; charset=utf-8")
    public ResultVO getUserById(@RequestParam("id") String id){
        Users user = usersService.findUserById(id);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(user);
        resultVO.setMsg("根据年龄查询 "+user.getName()+" 成功");
        return resultVO;
    }

    /**
     *  保存单个对象  dao-mybatis-配置
     */
    @PostMapping(value = "/saveUsers",produces = "application/json; charset=utf-8")
    public ResultVO saveUsers(@RequestParam("name") String name,
                             @RequestParam("age") Integer age){
        Users users=new Users();
        users.setName(name);
        users.setAge(age);
        users.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        usersService.insertUser(users);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(users);
        resultVO.setMsg("保存 "+users +" 成功");
        return resultVO;
    }

    /**
     * 根据名称查询用户集合 带分页了
     * @param name
     * @return
     */
    @PostMapping(value = "/selectUsersByName",produces = "application/json; charset=utf-8")
    public ResultVO selectUsersByName(@RequestParam("name") String name){
        // int pageNum, 当前页
        //int pageSize, 每页的条数
        PageHelper.startPage(1,2);
        List<Users> usersList = usersService.selectUsersByName(name);
        //返回集合对象给页面
        PageInfo<Users> pageInfo=new PageInfo<>(usersList);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(200);
        resultVO.setData(pageInfo);
        resultVO.setMsg("查询成功");
        return resultVO;
    }

}
