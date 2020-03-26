package com.wiwj.appinterface.Result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fei_user")
public class UserCompareInfo {
    @Id
    @Column(name="EMPL_ID")
    private String EMPL_ID;
    @Column(name="NAME_FORMAT")
    private String NAME_FORMAT;
    @Column(name="SEX")
    private String SEX;
    @Column(name="C_MOBILE")
    private String C_MOBILE;
    @Column(name="C_EMAIL")
    private String C_EMAIL;
    @Column(name="SET_ID")
    private String SET_ID;
    @Column(name="DEPT_ID")
    private String DEPT_ID;
    @Column(name="SUPERVISOR_ID")
    private String SUPERVISOR_ID;
    @Column(name="HR_STATUS")
    private String HR_STATUS;
    @Column(name="C_QUARTERS_ID")
    private String C_QUARTERS_ID;

    public String getEMPL_ID() {
        return EMPL_ID;
    }

    public void setEMPL_ID(String EMPL_ID) {
        this.EMPL_ID = EMPL_ID;
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

    public String getC_MOBILE() {
        return C_MOBILE;
    }

    public void setC_MOBILE(String c_MOBILE) {
        C_MOBILE = c_MOBILE;
    }

    public String getC_EMAIL() {
        return C_EMAIL;
    }

    public void setC_EMAIL(String c_EMAIL) {
        C_EMAIL = c_EMAIL;
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

    public String getC_QUARTERS_ID() {
        return C_QUARTERS_ID;
    }

    public void setC_QUARTERS_ID(String c_QUARTERS_ID) {
        C_QUARTERS_ID = c_QUARTERS_ID;
    }
}
