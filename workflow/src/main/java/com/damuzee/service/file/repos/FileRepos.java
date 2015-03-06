package com.damuzee.service.file.repos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileRepos {

	public String upload(File file, String fileFileName)throws FileNotFoundException,IOException ;

    public String writeStringToFile(String path, String name, String context);
}
