package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM USERS WHERE username = #{username}")
    Users getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(Users user);
    
    @Update("Update USERS set username= #{userName},salt=#{salt},password= #{password},firstname= #{firstName},lastname= #{lastName}, where userid=#{userId}")
    public void updateUser(Users user);
    
    @Delete("Delete from USERS where userid=#{userId}")
    public void deleteUserById(Integer userId);
}
