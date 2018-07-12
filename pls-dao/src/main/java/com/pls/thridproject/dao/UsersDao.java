package com.pls.thridproject.dao;

import com.pls.thridproject.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 81046 on 2018-07-11
 */
public interface UsersDao {
    /**
     * 这里没有在xml中配置  直接在这里将对象和数据库的列名进行一一的对应
     * @param name
     * @return
     */
    @Select("select * from users where name = #{name}")
	/*@Results({
		@Result(property="id",column="id"),
		@Result(property="name",column="name"),
		@Result(property="sex",column="sex")
	})*/
	List<Users> getByName(String name);

    @Select("select * from users where id = #{id}")
    Users getById(String id);

    @Select("select name from users where id = #{id}")
    String getNameById(String id);

    @Insert("insert into users(id,name,age) values(#{id},#{name},#{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Users users);

    /**
     * 根据年龄查询单个对象
     * @param id
     * @return
     */
    Users findUserById(String id);

    /**
     * 保存用户对象
     * @param users
     */
    void insertUser(Users users);

    /**
     * 根据名字查询集合对象
     * @param name
     * @return
     */
    List<Users> selectUsersByName(String name);
}
