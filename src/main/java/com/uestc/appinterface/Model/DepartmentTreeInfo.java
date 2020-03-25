package com.uestc.appinterface.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hr_dept_tree")
public class DepartmentTreeInfo {
    @Id
    @Column(name="FD_ID")
    private String FD_ID;

    @Column(name="SET_ID")
    private String SET_ID;

    @Column(name="TREE_NAME")
    private String TREE_NAME;

    @Column(name="EFFDT")
    private String EFFDT;

    @Column(name="TREE_NODE")
    private String TREE_NODE;

    @Column(name="PARENT_NODE_NAME")
    private String PARENT_NODE_NAME;

    @Column(name="TREE_LEVEL_NUM")
    private String TREE_LEVEL_NUM;

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

    public String getSET_ID() {
        return SET_ID;
    }

    public void setSET_ID(String SET_ID) {
        this.SET_ID = SET_ID;
    }

    public String getTREE_NAME() {
        return TREE_NAME;
    }

    public void setTREE_NAME(String TREE_NAME) {
        this.TREE_NAME = TREE_NAME;
    }

    public String getEFFDT() {
        return EFFDT;
    }

    public void setEFFDT(String EFFDT) {
        this.EFFDT = EFFDT;
    }

    public String getTREE_NODE() {
        return TREE_NODE;
    }

    public void setTREE_NODE(String TREE_NODE) {
        this.TREE_NODE = TREE_NODE;
    }

    public String getPARENT_NODE_NAME() {
        return PARENT_NODE_NAME;
    }

    public void setPARENT_NODE_NAME(String PARENT_NODE_NAME) {
        this.PARENT_NODE_NAME = PARENT_NODE_NAME;
    }

    public String getTREE_LEVEL_NUM() {
        return TREE_LEVEL_NUM;
    }

    public void setTREE_LEVEL_NUM(String TREE_LEVEL_NUM) {
        this.TREE_LEVEL_NUM = TREE_LEVEL_NUM;
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

