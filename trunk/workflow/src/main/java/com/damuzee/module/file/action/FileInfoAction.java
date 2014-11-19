package com.damuzee.module.file.action;

import com.damuzee.module.file.domain.FileInfo;
import com.damuzee.module.file.service.FileInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class FileInfoAction {

    public void downloadFile(Long id,HttpServletResponse response){  
        response.setCharacterEncoding("utf-8");  
        response.setContentType("multipart/form-data");  
  
        try {  
        	FileInfo fileInfo = new FileInfo();
        	fileInfo.setFileId(id);
        	//fileInfo = fileService.getFileInfo(fileInfo);
        	String filePath = fileInfo.getPath();
        	
        	File file = null; 
        	if(filePath != null)
            	 file=new File(filePath); 
            
            InputStream inputStream=new FileInputStream("file/"+file);  
            OutputStream os=response.getOutputStream();  
            byte[] b=new byte[1024];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
            inputStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
