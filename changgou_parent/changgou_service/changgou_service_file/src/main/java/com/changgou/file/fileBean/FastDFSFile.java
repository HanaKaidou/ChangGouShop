package com.changgou.file.fileBean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/5/31 21:36
 */
public class FastDFSFile implements Serializable {
    //文件名字
    private String fileName;
    //文件内容
    private byte[] content;
    //文件扩展名
    private String ext;
    //文件MD5摘要值
    private String md5;
    //文件创建作者
    private String author;
    //上传人
    private String uploader;
    //上传时间
    private Date uploadDate;

    public FastDFSFile(String fileName, byte[] content, String ext) {
        this.fileName = fileName;
        this.content = content;
        this.ext = ext;
    }

    public FastDFSFile(String fileName, byte[] content, String ext, String md5, String author, String uploader) {
        this.fileName = fileName;
        this.content = content;
        this.ext = ext;
        this.md5 = md5;
        this.author = author;
        this.uploader = uploader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
