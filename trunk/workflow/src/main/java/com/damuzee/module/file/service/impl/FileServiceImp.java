package com.damuzee.module.file.service.impl;

import com.damuzee.core.util.ConfigUtil;
import com.damuzee.module.file.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {

	public String upload(File file,String fileName)throws FileNotFoundException,IOException   {

        // 设置上传文件目录  
        String uploadPath = ConfigUtil.properties.getProperty("path") ;

        String type = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID()+ type ;

        writeFileToDisk(file,uploadPath,fileName) ;

		return fileName;
	}

    public String writeFileToDisk(File file,String path , String fileName)
            throws FileNotFoundException,IOException {

        // 设置目标文件
        File toFile = new File(path);

        if(!toFile.exists()){
            toFile.mkdirs();
        }

        toFile = new File(path,fileName);


        //基于myFile创建一个文件输入流
        InputStream is = new FileInputStream(file);

        // 创建一个输出流
        OutputStream os = new FileOutputStream(file);

        //设置缓存
        byte[] buffer = new byte[1024];

        int length = 0;

        //读取myFile文件输出到toFile文件中
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }

        //关闭输入流
        is.close();

        //关闭输出流
        os.close();

        return null ;
    }

    public String writeStringToFile(String path ,String name ,String context){
        try {
            FileOutputStream fos = new FileOutputStream(path +name );
            fos.write(context.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
