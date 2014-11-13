package com.damuzee.module.file.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

	public String upload(File file, String fileFileName)throws FileNotFoundException,IOException ;

    public String writeStringToFile(String path ,String name ,String context);
}
