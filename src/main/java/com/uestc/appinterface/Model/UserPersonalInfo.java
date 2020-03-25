package com.uestc.appinterface.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hr_personal")
public class UserPersonalInfo {
    @Id
    @Column(name="EMPL_ID")
    private String EMPL_ID;
    @Column(name="COUNTRY_NM_FORM")
    private String COUNTRY_NM_FORM;
    @Column(name="NAME_FORMAT")
    private String NAME_FORMAT;
    @Column(name="SEX")
    private String SEX;
    @Column(name="MAR_STATUS")
    private String MAR_STATUS;
    @Column(name="MAR_STATUS_DT")
    private String MAR_STATUS_DT;
    @Column(name="HIGHEST_EDUC_LVL")
    private String HIGHEST_EDUC_LVL;
    @Column(name="BIRTH_DATE")
    private String BIRTH_DATE;
    @Column(name="C_MOBILE")
    private String C_MOBILE;
    @Column(name="C_PHONE")
    private String C_PHONE;
    @Column(name="C_ADDRESS_HOME")
    private String C_ADDRESS_HOME;
    @Column(name="C_ADDRESS_HOME_NID")
    private String C_ADDRESS_HOME_NID;
    @Column(name="C_OPERATOR")
    private String C_OPERATOR;
    @Column(name="COMPANY_ID")
    private String COMPANY_ID;
    @Column(name="NATIONAL_ID")
    private String NATIONAL_ID;
    @Column(name="C_EMAIL")
    private String C_EMAIL;
    @Column(name="C_COMP_EMAIL")
    private String C_COMP_EMAIL;
    @Column(name="SET_ID")
    private String SET_ID;
    @Column(name="BATCH_NUM")
    private String BATCH_NUM;
    @Column(name="FD_HR_DATE")
    private String FD_HR_DATE;
    @Column(name="MODIFY_TIME")
    private String MODIFY_TIME;

    public String getEMPL_ID() {
        return EMPL_ID;
    }

    public void setEMPL_ID(String EMPL_ID) {
        this.EMPL_ID = EMPL_ID;
    }

    public String getCOUNTRY_NM_FORM() {
        return COUNTRY_NM_FORM;
    }

    public void setCOUNTRY_NM_FORM(String COUNTRY_NM_FORM) {
        this.COUNTRY_NM_FORM = COUNTRY_NM_FORM;
    }

    public String getNAME_FORMAT() {
        return NAME_FORMAT;
    }

    public void setNAME_FORMAT(String NAME_FORMAT) {
        this.NAME_FORMAT = NAME_FORMAT;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getMAR_STATUS() {
        return MAR_STATUS;
    }

    public void setMAR_STATUS(String MAR_STATUS) {
        this.MAR_STATUS = MAR_STATUS;
    }

    public String getMAR_STATUS_DT() {
        return MAR_STATUS_DT;
    }

    public void setMAR_STATUS_DT(String MAR_STATUS_DT) {
        this.MAR_STATUS_DT = MAR_STATUS_DT;
    }

    public String getHIGHEST_EDUC_LVL() {
        return HIGHEST_EDUC_LVL;
    }

    public void setHIGHEST_EDUC_LVL(String HIGHEST_EDUC_LVL) {
        this.HIGHEST_EDUC_LVL = HIGHEST_EDUC_LVL;
    }

    public String getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public void setBIRTH_DATE(String BIRTH_DATE) {
        this.BIRTH_DATE = BIRTH_DATE;
    }

    public String getC_MOBILE() {
        return C_MOBILE;
    }

    public void setC_MOBILE(String c_MOBILE) {
        C_MOBILE = c_MOBILE;
    }

    public String getC_PHONE() {
        return C_PHONE;
    }

    public void setC_PHONE(String c_PHONE) {
        C_PHONE = c_PHONE;
    }

    public String getC_ADDRESS_HOME() {
        return C_ADDRESS_HOME;
    }

    public void setC_ADDRESS_HOME(String c_ADDRESS_HOME) {
        C_ADDRESS_HOME = c_ADDRESS_HOME;
    }

    public String getC_ADDRESS_HOME_NID() {
        return C_ADDRESS_HOME_NID;
    }

    public void setC_ADDRESS_HOME_NID(String c_ADDRESS_HOME_NID) {
        C_ADDRESS_HOME_NID = c_ADDRESS_HOME_NID;
    }

    public String getC_OPERATOR() {
        return C_OPERATOR;
    }

    public void setC_OPERATOR(String c_OPERATOR) {
        C_OPERATOR = c_OPERATOR;
    }

    public String getCOMPANY_ID() {
        return COMPANY_ID;
    }

    public void setCOMPANY_ID(String COMPANY_ID) {
        this.COMPANY_ID = COMPANY_ID;
    }

    public String getNATIONAL_ID() {
        return NATIONAL_ID;
    }

    public void setNATIONAL_ID(String NATIONAL_ID) {
        this.NATIONAL_ID = NATIONAL_ID;
    }

    public String getC_EMAIL() {
        return C_EMAIL;
    }

    public void setC_EMAIL(String c_EMAIL) {
        C_EMAIL = c_EMAIL;
    }

    public String getC_COMP_EMAIL() {
        return C_COMP_EMAIL;
    }

    public void setC_COMP_EMAIL(String c_COMP_EMAIL) {
        C_COMP_EMAIL = c_COMP_EMAIL;
    }

    public String getSET_ID() {
        return SET_ID;
    }

    public void setSET_ID(String SET_ID) {
        this.SET_ID = SET_ID;
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
