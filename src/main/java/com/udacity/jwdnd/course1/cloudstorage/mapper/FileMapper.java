package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Mapper
public interface FileMapper {
	@Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files getFile(String fileName);

    @Insert("INSERT INTO FILES (filename, contenttype,filesize,filedata) VALUES(#{fileName}, #{contentType},#{fileSize},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(Files file);
    
    @Update("Update FILES set filename= #{fileName},contenttype=#{contentType},filesize=#{fileSize},filedata=#{fileData} where fileid=#{fileId}")
    public void updateFile(Files file);
    
    @Delete("Delete from FILES where fileid=#{fileId}")
    public void deleteFileById(Integer fileId);
}
