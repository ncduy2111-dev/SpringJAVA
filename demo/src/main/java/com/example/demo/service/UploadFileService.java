package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadFileService {
    private final ServletContext servletContext;

    public UploadFileService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadFile(MultipartFile file, String targetFolder) {
        String rootPath = this.servletContext.getRealPath("/resources/images");
        String finnalName = "";
        try {
            byte[] bytes;

            bytes = file.getBytes();

            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();

            finnalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finnalName);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return finnalName;
    }

}
