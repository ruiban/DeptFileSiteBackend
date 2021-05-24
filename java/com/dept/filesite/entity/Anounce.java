package com.dept.filesite.entity;


/**
 * @className: Anounce
 * @description: 文档实体类
 * @author: 201998
 * @create: 2019-12-25 09:21
 */

public class Anounce {


    private int id;
    private String fileNo;
    private String fileName;
    private String fileType;
    private String issueDate;
    private String editOffice;
    private String editPerson;
    private String oldFileNo;
    private String major;
    private String note;
    private String filePath;
    private String newVersion ;
    private String category;
    private String plate;
    private String status;
    private String pdmNo;
    private String authorization;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getEditOffice() {
        return editOffice;
    }

    public void setEditOffice(String editOffice) {
        this.editOffice = editOffice;
    }

    public String getEditPerson() {
        return editPerson;
    }

    public void setEditPerson(String editPerson) {
        this.editPerson = editPerson;
    }

    public String getOldFileNo() {
        return oldFileNo;
    }

    public void setOldFileNo(String oldFileNo) {
        this.oldFileNo = oldFileNo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPdmNo() {
        return pdmNo;
    }

    public void setPdmNo(String pdmNo) {
        this.pdmNo = pdmNo;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "Anounce{" +
                "id=" + id +
                ", fileNo='" + fileNo + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", editOffice='" + editOffice + '\'' +
                ", editPerson='" + editPerson + '\'' +
                ", oldFileNo='" + oldFileNo + '\'' +
                ", major='" + major + '\'' +
                ", note='" + note + '\'' +
                ", filePath='" + filePath + '\'' +
                ", newVersion='" + newVersion + '\'' +
                ", category='" + category + '\'' +
                ", plate='" + plate + '\'' +
                ", status='" + status + '\'' +
                ", pdmNo='" + pdmNo + '\'' +
                ", authorization='" + authorization + '\'' +
                '}';
    }
}
