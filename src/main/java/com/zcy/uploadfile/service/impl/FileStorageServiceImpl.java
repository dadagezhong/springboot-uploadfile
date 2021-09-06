package com.zcy.uploadfile.service.impl;

import com.zcy.uploadfile.service.FileStorageService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Files、Path和Paths是java.nio.file提供的类，Resource是org.springframework.core.io包中提供的类。
 * @author zhongchengye
 * @create 2021-09-03 17:24
 */
@Service("fileStorageService")
public class FileStorageServiceImpl implements FileStorageService {
    private final Path path = Paths.get("fileStorage");

    /**
     * 初始化路径，启动项目时在项目路径下新建一个fileStorage的文件夹
     */
    @Override
    public void init() {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    /**
     * 在项目启动时，先调用clear()方法清理历史文件
     */
    @Override
    public void clear() {
        FileSystemUtils.deleteRecursively(path.toFile());
    }


    /**
     * 文件上传
     * @param multipartFile
     */
    @Override
    public void save(MultipartFile multipartFile) {
        try {
            Files.copy(multipartFile.getInputStream(),path.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error:"+e.getMessage());
        }
    }

    /**
     * 文件下载
     * @param filename
     * @return
     */
    @Override
    public Resource load(String filename) {
        Path file = path.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("Could not read the file.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error:"+e.getMessage());
        }
    }

    /**
     * 文件列表
     * @return
     */
    @Override
    public Stream<Path> load() {
        try {
            return Files.walk(this.path,1)
                    .filter(path -> !path.equals(this.path))
                    .map(this.path::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files.");
        }
    }
}
