package com.cybersoft.uniclub06.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public void saveFile(MultipartFile file);

    Resource loadFile(String filename);

}
