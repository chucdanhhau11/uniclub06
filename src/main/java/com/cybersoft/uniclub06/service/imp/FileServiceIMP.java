package com.cybersoft.uniclub06.service.imp;


import com.cybersoft.uniclub06.exception.FileNotFoundException;
import com.cybersoft.uniclub06.exception.SaveFileException;
import com.cybersoft.uniclub06.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceIMP implements FileService {

    @Value("${root.path}")
    private String root;

    @Override
    public void saveFile(MultipartFile file) {
        try {
            Path rootPath = Paths.get(root);

            if (!Files.exists(rootPath)) {
                Files.createDirectory(rootPath);
            }

            Files.copy(file.getInputStream(),rootPath.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        }catch (Exception e){
            throw new SaveFileException("Lỗi lưu file " + e.getMessage());
        }
    }

    @Override
    public Resource loadFile(String filename) {
        try {
            Path rootPath = Paths.get(root); // lấy đường dẫn
            Path file = rootPath.resolve(filename); // nối đường dẫn vào filename
            Resource resource = new UrlResource(file.toUri()); // biến tên file thành resource
            if (resource.exists()){ // kiểm tra file có tồn tại hay không
                return resource;
            }else {
                throw new FileNotFoundException();
            }
        }catch(Exception e){
            throw new FileNotFoundException(e.getMessage());
        }

    }
}
