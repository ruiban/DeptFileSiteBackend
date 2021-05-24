package com.dept.filesite.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dept.filesite.utils.DateUtil;


/**
 * @className: MaskVO
 * @description:
 * @author: 201998
 * @create: 2020-04-27 08:58
 */

public class MaskVO {
    @ExcelProperty("科室")
    private String office;

    @ExcelProperty("总人数")
    private Long persons;

    @ExcelProperty("上午")
    private String submitAM = "未提交";

    @ExcelProperty("下午")
    private String  submitPM = "未提交";

    @ExcelProperty("口罩发放")
    private int maskTotal = 0;

    @ExcelProperty("时间")
    private String today = DateUtil.getToday();

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Long getPersons() {
        return persons;
    }

    public void setPersons(Long persons) {
        this.persons = persons;
    }

    public int getMaskTotal() {
        return maskTotal;
    }

    public void setMaskTotal(int maskTotal) {
        this.maskTotal = maskTotal;
    }

    public String getSubmitAM() {
        return submitAM;
    }

    public void setSubmitAM(String submitAM) {
        this.submitAM = submitAM;
    }

    public String getSubmitPM() {
        return submitPM;
    }

    public void setSubmitPM(String submitPM) {
        this.submitPM = submitPM;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }


}
