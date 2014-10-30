package com.damuzee.module.file.service;

import com.damuzee.module.file.domain.FileInfo;
import org.mybatis.sql.page.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * 文件上传下下载的管理服务
 * 
 * @author rka.w
 *
 */

public interface   FileInfoService{

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param file  所上传的文件
	 * @return 文件在数据库中的唯一编号
	 */
	public Long uploadFile(HttpServletRequest request,MultipartFile file) ;
	
	public Page< FileInfo> getFileInfoList(Page<  FileInfo> page,   FileInfo  bean) ;

	public void saveFileInfo( FileInfo bean);
	
 
	public List<FileInfo> getFileInfoList( FileInfo bean);

	 
	public void updateFileInfo( FileInfo bean);
	
	public FileInfo getFileInfo(FileInfo bean);

}
