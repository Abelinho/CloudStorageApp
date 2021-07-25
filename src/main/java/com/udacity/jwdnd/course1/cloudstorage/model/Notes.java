package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notes {
	private final Integer noteId;
	private final String noteTitle;
	private final String  noteDescription;
	private final Integer userId;
}
