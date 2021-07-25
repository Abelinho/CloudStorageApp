package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Files {

	private final Integer fileId;
	private final String fileName ;
	private final String contentType;
	private final String fileSize; 
	private final Integer  userId;
	private final byte[]  fileData;
}
