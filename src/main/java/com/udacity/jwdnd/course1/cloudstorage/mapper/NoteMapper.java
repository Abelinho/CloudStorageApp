package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;


@Mapper
public interface NoteMapper {
	@Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Notes getNote(String noteTitle);

    @Insert("INSERT INTO NOTES (notetitle, notedescription) VALUES(#{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Notes note);
    
    @Update("Update NOTES set notetitle= #{noteTitle},notedescription=#{noteDescription} where noteid=#{noteId}")
    public void updateNote(Notes note);
    
    @Delete("Delete from NOTES where noteid=#{noteId}")
    public void deleteNoteById(Integer noteId);
}
