package com.zcy.uploadfile.config;

import com.zcy.uploadfile.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * @author zhongchengye
 * @create 2021-09-03 17:45
 */
@Service
public class FileUploadConfiguration implements CommandLineRunner {
    @Autowired
    FileStorageService fileStorageService;

    @Override
    public void run(String... args) throws Exception {
        fileStorageService.clear();
        fileStorageService.init();
    }
}
