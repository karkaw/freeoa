package com.damuzee.module.file.domain;

import com.damuzee.common.util.DateUtil;
import com.damuzee.module.file.persistence.FileInfoMapper;
import org.mybatis.sql.BaseBean;
import org.mybatis.sql.annotation.BaseResultMap;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;



/**
 * auto generator
 */
 

public class FileInfo extends BaseBean implements java.io.Serializable {
 
	@Id
  	@Column(name = "id")
  	private Long FileId ;								//
  	@Column(name = "uuid")
  	private String uuid ;								//
  	@Column(name = "path")
  	private String path ;								//
  	@Column(name = "size")
  	private Long size ;								//
  	@Column(name = "upload_time")
  	private String uploadTime ;								//
  	@Column(name = "type")
  	private String type ;								//
  	
  	public FileInfo(){
  		this.uuid =  UUID.randomUUID().toString().replaceAll("-", "") ;
  		this.uploadTime = DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATETIME);
  	}
 
	public Long getFileId() {
		return FileId;
	}

	public void setFileId(Long fileId) {
		FileId = fileId;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid( String uuid) {
		
		this.uuid = uuid;
	}
	public String getPath() {
		return this.path;
	}

	public void setPath( String path) {
		this.path = path;
	}
	public Long getSize() {
		return this.size;
	}

	public void setSize( Long size) {
		this.size = size;
	}
	public String getUploadTime() {
		
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		
		this.uploadTime = uploadTime;
	}
	public String getType() {
		return this.type;
	}

	public void setType( String type) {
		this.type = type;
	}
	
 
	 
}