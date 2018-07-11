package com.pls.thridproject.dao;

import com.pls.thridproject.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

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
    Users getById(int id);

    @Select("select name from users where id = #{id}")
    String getNameById(String id);

    @Insert("insert into users(id,name,age) values(#{id},#{name},#{age})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Users users);
}
