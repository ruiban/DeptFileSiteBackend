package com.dept.filesite.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @className: Page
 * @description: 分页实体类
 * @author: 201998
 * @create: 2019-12-25 09:21
 */

public class Page implements Serializable {

    //当前页
    private int currrenPageNum;
    //每页显示条数
    private int pageSize =20;
    //总记录数
    private int totalCount;
    //查询的开始索引
    private int startIndex;
    //总页数
    private int totalPageNum;
    //上一页
    private int prePageNum;
    //下一页
    private int nextPageNum;
    //结果集
    private List records;

    //页码
    private int beginPageNum;
    private int endPageNum;

    public Page(int currrenPageNum, int totalCount){

        this.currrenPageNum = currrenPageNum;
        this.totalCount = totalCount;

        startIndex = (currrenPageNum-1)*pageSize;

        totalPageNum = totalCount%pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1;



        //计算页码
    }

    public int getPrePageNum(){
        prePageNum = currrenPageNum-1;
        if (prePageNum < 1){
            prePageNum = 1;
        }
        return prePageNum;
    }

    public int getNextPageNum(){
        nextPageNum = currrenPageNum + 1;
        if (nextPageNum > totalPageNum){
            nextPageNum = totalPageNum;
        }
        return nextPageNum;
    }

    public int getCurrrenPageNum() {
        return currrenPageNum;
    }

    public void setCurrrenPageNum(int currrenPageNum) {
        this.currrenPageNum = currrenPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getBeginPageNum() {
        return beginPageNum;
    }

    public void setBeginPageNum(int beginPageNum) {
        this.beginPageNum = beginPageNum;
    }

    public int getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }
}
