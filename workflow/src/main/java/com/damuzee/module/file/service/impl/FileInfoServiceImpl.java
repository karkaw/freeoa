package com.damuzee.module.file.service.impl;

import com.damuzee.common.util.DateUtil;
import com.damuzee.common.web.spring.MessageResolver;
import com.damuzee.module.file.domain.FileInfo;
import com.damuzee.module.file.persistence.FileInfoMapper;
import com.damuzee.module.file.service.FileInfoService;
import org.mybatis.sql.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 
 * 文件上传下下载的管理服务
 * 
 * @author rka.w
 *
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {
	
	@Resource
	private  FileInfoMapper mapper ;
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param file  所上传的文件
	 * @return 文件在数据库中的唯一编号
	 */
	public Long uploadFile(HttpServletRequest request,MultipartFile file){
		FileInfo fileInfo = null; 
		try {
			String path = MessageResolver.getMessage(request, "server.file.path");
			String fileName = file.getOriginalFilename();
			if (path.lastIndexOf('/') == -1) {
				path += "/" ;
			}
			path  = path + DateUtil.getCurrentDateTime(DateUtil.FORMAT_DATE).replaceAll("-", "") ;
			
			int index = fileName.lastIndexOf(".");
			String suffer  = fileName.substring(index);
			fileName = UUID.randomUUID() + suffer ;
			
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			
			fileInfo = new FileInfo();
			fileInfo.setPath(path +"/"+ fileName);
			fileInfo.setSize(file.getSize());
			fileInfo.setType(file.getContentType());
			mapper.save(fileInfo);
	
		
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		Long id = fileInfo.getIdKey();
		
		return id;
	}
	
	public Page< FileInfo> getFileInfoList(Page<  FileInfo> page,   FileInfo  bean) {
		return mapper.selectPageByWhere(page,   bean);
	}

	public void saveFileInfo( FileInfo bean) {
		 
		mapper.insert(bean);
	}
	
 
	public List<FileInfo> getFileInfoList( FileInfo bean) {
		 
		return mapper.selectList(bean);
	}

	 
	public void updateFileInfo( FileInfo bean) {
		mapper.update(bean); 
	}
	
	public FileInfo getFileInfo(FileInfo bean){
		return mapper.select(bean); 
	}

}
