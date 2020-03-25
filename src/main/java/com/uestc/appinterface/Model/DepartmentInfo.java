package com.uestc.appinterface.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hr_dept")
public class DepartmentInfo {
    @Id
    @Column(name="FD_ID")
    private String FD_ID;

    @Column(name="SET_ID")
    private String SET_ID;

    @Column(name="DEPT_ID")
    private String DEPT_ID;

    @Column(name="EFFDT")
    private String EFFDT;

    @Column(name="EFF_STATUS")
    private String EFF_STATUS;

    @Column(name="DESCR")
    private String DESCR;

    @Column(name="DESCR_SHORT")
    private String DESCR_SHORT;

    @Column(name="COMPANY")
    private String COMPANY;

    @Column(name="C_DEPT_TYPE")
    private String C_DEPT_TYPE;

    @Column(name="C_DEPT_STYPE")
    private String C_DEPT_STYPE;

    @Column(name="C_BUSINESS_TYPE")
    private String C_BUSINESS_TYPE;

    @Column(name="C_BUSINESS_SUB")
    private String C_BUSINESS_SUB;

    @Column(name="C_PHYSI_STORE")
    private String C_PHYSI_STORE;

    @Column(name="MANAGER_ID")
    private String MANAGER_ID;

    @Column(name="MANAGER_POSN")
    private String MANAGER_POSN;

    @Column(name="C_FINANCE_CD")
    private String C_FINANCE_CD;

    @Column(name="C_DEPT_ID_TYPE")
    private String C_DEPT_ID_TYPE;

    @Column(name="DESCR4")
    private String DESCR4;

    @Column(name="BATCH_NUM")
    private String BATCH_NUM;

    @Column(name="FD_HR_DATE")
    private String FD_HR_DATE;

    @Column(name="MODIFY_TIME")
    private String MODIFY_TIME;

    @Column(name="FEISHU_DEPT_ID")
    private String FEISHU_DEPT_ID;

    public String getFEISHU_DEPT_ID() {
        return FEISHU_DEPT_ID;
    }

    public void setFEISHU_DEPT_ID(String FEISHU_DEPT_ID) {
        this.FEISHU_DEPT_ID = FEISHU_DEPT_ID;
    }

    public String getFD_ID() {
        return FD_ID;
    }

    public void setFD_ID(String FD_ID) {
        this.FD_ID = FD_ID;
    }

    public String getSET_ID() {
        return SET_ID;
    }

    public void setSET_ID(String SET_ID) {
        this.SET_ID = SET_ID;
    }

    public String getDEPT_ID() {
        return DEPT_ID;
    }

    public void setDEPT_ID(String DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public String getEFFDT() {
        return EFFDT;
    }

    public void setEFFDT(String EFFDT) {
        this.EFFDT = EFFDT;
    }

    public String getEFF_STATUS() {
        return EFF_STATUS;
    }

    public void setEFF_STATUS(String EFF_STATUS) {
        this.EFF_STATUS = EFF_STATUS;
    }

    public String getDESCR() {
        return DESCR;
    }

    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }

    public String getDESCR_SHORT() {
        return DESCR_SHORT;
    }

    public void setDESCR_SHORT(String DESCR_SHORT) {
        this.DESCR_SHORT = DESCR_SHORT;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getC_DEPT_TYPE() {
        return C_DEPT_TYPE;
    }

    public void setC_DEPT_TYPE(String c_DEPT_TYPE) {
        C_DEPT_TYPE = c_DEPT_TYPE;
    }

    public String getC_DEPT_STYPE() {
        return C_DEPT_STYPE;
    }

    public void setC_DEPT_STYPE(String c_DEPT_STYPE) {
        C_DEPT_STYPE = c_DEPT_STYPE;
    }

    public String getC_BUSINESS_TYPE() {
        return C_BUSINESS_TYPE;
    }

    public void setC_BUSINESS_TYPE(String c_BUSINESS_TYPE) {
        C_BUSINESS_TYPE = c_BUSINESS_TYPE;
    }

    public String getC_BUSINESS_SUB() {
        return C_BUSINESS_SUB;
    }

    public void setC_BUSINESS_SUB(String c_BUSINESS_SUB) {
        C_BUSINESS_SUB = c_BUSINESS_SUB;
    }

    public String getC_PHYSI_STORE() {
        return C_PHYSI_STORE;
    }

    public void setC_PHYSI_STORE(String c_PHYSI_STORE) {
        C_PHYSI_STORE = c_PHYSI_STORE;
    }

    public String getMANAGER_ID() {
        return MANAGER_ID;
    }

    public void setMANAGER_ID(String MANAGER_ID) {
        this.MANAGER_ID = MANAGER_ID;
    }

    public String getMANAGER_POSN() {
        return MANAGER_POSN;
    }

    public void setMANAGER_POSN(String MANAGER_POSN) {
        this.MANAGER_POSN = MANAGER_POSN;
    }

    public String getC_FINANCE_CD() {
        return C_FINANCE_CD;
    }

    public void setC_FINANCE_CD(String c_FINANCE_CD) {
        C_FINANCE_CD = c_FINANCE_CD;
    }

    public String getC_DEPT_ID_TYPE() {
        return C_DEPT_ID_TYPE;
    }

    public void setC_DEPT_ID_TYPE(String c_DEPT_ID_TYPE) {
        C_DEPT_ID_TYPE = c_DEPT_ID_TYPE;
    }

    public String getDESCR4() {
        return DESCR4;
    }

    public void setDESCR4(String DESCR4) {
        this.DESCR4 = DESCR4;
    }

    public String getBATCH_NUM() {
        return BATCH_NUM;
    }

    public void setBATCH_NUM(String BATCH_NUM) {
        this.BATCH_NUM = BATCH_NUM;
    }

    public String getFD_HR_DATE() {
        return FD_HR_DATE;
    }

    public void setFD_HR_DATE(String FD_HR_DATE) {
        this.FD_HR_DATE = FD_HR_DATE;
    }

    public String getMODIFY_TIME() {
        return MODIFY_TIME;
    }

    public void setMODIFY_TIME(String MODIFY_TIME) {
        this.MODIFY_TIME = MODIFY_TIME;
    }
}

