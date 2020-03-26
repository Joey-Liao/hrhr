package com.wiwj.appinterface.Result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fei_dept")
public class DeptCompareInfo {
    @Id
    @Column(name="FD_ID")
    private String FD_ID;

    @Column(name="SET_ID")
    private String SET_ID;

    @Column(name="DEPT_ID")
    private String DEPT_ID;

    @Column(name="EFF_STATUS")
    private String EFF_STATUS;

    @Column(name="DESCR")
    private String DESCR;

    @Column(name="MANAGER_ID")
    private String MANAGER_ID;

    @Column(name="PARENT_NODE_NAME")
    private String PARENT_NODE_NAME;

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

    public String getMANAGER_ID() {
        return MANAGER_ID;
    }

    public void setMANAGER_ID(String MANAGER_ID) {
        this.MANAGER_ID = MANAGER_ID;
    }

    public String getPARENT_NODE_NAME() {
        return PARENT_NODE_NAME;
    }

    public void setPARENT_NODE_NAME(String PARENT_NODE_NAME) {
        this.PARENT_NODE_NAME = PARENT_NODE_NAME;
    }
}
