package com.dept.filesite.vo;

/**
 * @className: ArchiveVO
 * @description:
 * @author: 201998
 * @create: 2020-04-20 14:56
 */

public class ArchiveVO {
    private String fileType;
    private String fileName;
    private String fileSize;
    private String uploadTime;
    private boolean dir = false;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public boolean isDir() {
        return dir;
    }

    public void setDir(boolean directory) {
        dir = directory;
    }
}
