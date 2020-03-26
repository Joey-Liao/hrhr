package com.wiwj.appinterface.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hr_emp")
public class UserInfo {
    @Id
    @Column(name="FD_ID")
    private String FD_ID;

    @Column(name="EMPL_ID")
    private String EMPL_ID;

    @Column(name="EMPL_RCD")
    private String EMPL_RCD;

    @Column(name="EFF_DT")
    private String EFF_DT;

    @Column(name="EFF_SEQ")
    private String EFF_SEQ;

    @Column(name="SETID_DEPT")
    private String SETID_DEPT;

    @Column(name="DEPT_ID")
    private String DEPT_ID;

    @Column(name="SET_ID_JOB_CODE")
    private String SET_ID_JOB_CODE;

    @Column(name="COMPANY_ID")
    private String COMPANY_ID;

    @Column(name="JOB_CODE")
    private String JOB_CODE;

    @Column(name="C_RANK_CODE")
    private String C_RANK_CODE;

    @Column(name="C_RANK_DESCR")
    private String C_RANK_DESCR;

    @Column(name="C_QUARTERS_ID")
    private String C_QUARTERS_ID;

    @Column(name="SUPERVISOR_ID")
    private String SUPERVISOR_ID;

    @Column(name="HR_STATUS")
    private String HR_STATUS;

    @Column(name="C_WORK_SHOP")
    private String C_WORK_SHOP;

    @Column(name="C_WORK_SHOP_DEPT_ID")
    private String C_WORK_SHOP_DEPT_ID;

    @Column(name="LAST_HIRE_DT")
    private String LAST_HIRE_DT;

    @Column(name="TERMINATION_DT")
    private String TERMINATION_DT;

    @Column(name="ACTION")
    private String ACTION;

    @Column(name="BATCH_NUM")
    private String BATCH_NUM;

    @Column(name="FD_HR_DATE")
    private String FD_HR_DATE;

    @Column(name="MODIFY_TIME")
    private String MODIFY_TIME;

    public String getFD_ID() {
        return FD_ID;
    }

    public void setFD_ID(String FD_ID) {
        this.FD_ID = FD_ID;
    }

    public String getEMPL_ID() {
        return EMPL_ID;
    }

    public void setEMPL_ID(String EMPL_ID) {
        this.EMPL_ID = EMPL_ID;
    }

    public String getEMPL_RCD() {
        return EMPL_RCD;
    }

    public void setEMPL_RCD(String EMPL_RCD) {
        this.EMPL_RCD = EMPL_RCD;
    }

    public String getEFF_DT() {
        return EFF_DT;
    }

    public void setEFF_DT(String EFF_DT) {
        this.EFF_DT = EFF_DT;
    }

    public String getEFF_SEQ() {
        return EFF_SEQ;
    }

    public void setEFF_SEQ(String EFF_SEQ) {
        this.EFF_SEQ = EFF_SEQ;
    }

    public String getSETID_DEPT() {
        return SETID_DEPT;
    }

    public void setSETID_DEPT(String SETID_DEPT) {
        this.SETID_DEPT = SETID_DEPT;
    }

    public String getDEPT_ID() {
        return DEPT_ID;
    }

    public void setDEPT_ID(String DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public String getSET_ID_JOB_CODE() {
        return SET_ID_JOB_CODE;
    }

    public void setSET_ID_JOB_CODE(String SET_ID_JOB_CODE) {
        this.SET_ID_JOB_CODE = SET_ID_JOB_CODE;
    }

    public String getCOMPANY_ID() {
        return COMPANY_ID;
    }

    public void setCOMPANY_ID(String COMPANY_ID) {
        this.COMPANY_ID = COMPANY_ID;
    }

    public String getJOB_CODE() {
        return JOB_CODE;
    }

    public void setJOB_CODE(String JOB_CODE) {
        this.JOB_CODE = JOB_CODE;
    }

    public String getC_RANK_CODE() {
        return C_RANK_CODE;
    }

    public void setC_RANK_CODE(String c_RANK_CODE) {
        C_RANK_CODE = c_RANK_CODE;
    }

    public String getC_RANK_DESCR() {
        return C_RANK_DESCR;
    }

    public void setC_RANK_DESCR(String c_RANK_DESCR) {
        C_RANK_DESCR = c_RANK_DESCR;
    }

    public String getC_QUARTERS_ID() {
        return C_QUARTERS_ID;
    }

    public void setC_QUARTERS_ID(String c_QUARTERS_ID) {
        C_QUARTERS_ID = c_QUARTERS_ID;
    }

    public String getSUPERVISOR_ID() {
        return SUPERVISOR_ID;
    }

    public void setSUPERVISOR_ID(String SUPERVISOR_ID) {
        this.SUPERVISOR_ID = SUPERVISOR_ID;
    }

    public String getHR_STATUS() {
        return HR_STATUS;
    }

    public void setHR_STATUS(String HR_STATUS) {
        this.HR_STATUS = HR_STATUS;
    }

    public String getC_WORK_SHOP() {
        return C_WORK_SHOP;
    }

    public void setC_WORK_SHOP(String c_WORK_SHOP) {
        C_WORK_SHOP = c_WORK_SHOP;
    }

    public String getC_WORK_SHOP_DEPT_ID() {
        return C_WORK_SHOP_DEPT_ID;
    }

    public void setC_WORK_SHOP_DEPT_ID(String c_WORK_SHOP_DEPT_ID) {
        C_WORK_SHOP_DEPT_ID = c_WORK_SHOP_DEPT_ID;
    }

    public String getLAST_HIRE_DT() {
        return LAST_HIRE_DT;
    }

    public void setLAST_HIRE_DT(String LAST_HIRE_DT) {
        this.LAST_HIRE_DT = LAST_HIRE_DT;
    }

    public String getTERMINATION_DT() {
        return TERMINATION_DT;
    }

    public void setTERMINATION_DT(String TERMINATION_DT) {
        this.TERMINATION_DT = TERMINATION_DT;
    }

    public String getACTION() {
        return ACTION;
    }

    public void setACTION(String ACTION) {
        this.ACTION = ACTION;
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
