package com.zcy.uploadfile.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author zhongchengye
 * @create 2021-09-03 17:23
 */
public interface FileStorageService {
    /**
     * 项目启动时初始化，在项目路径下新建一个存储上传文件的文件夹
     */
    void init();

    /**
     * 项目启动时初始化，先清理文件
     */
    void clear();

    /**
     *
     * @param multipartFile
     */
    void save(MultipartFile multipartFile);

    Resource load(String filename);

    Stream<Path> load();


}
