package com.zcy.uploadfile.valueobject;

/**
 * @author zhongchengye
 * @create 2021-09-03 17:20
 */
public class UploadFile {

    private String fileName;
    private String url;

    public UploadFile(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
