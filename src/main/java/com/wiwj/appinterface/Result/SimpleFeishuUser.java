package com.wiwj.appinterface.Result;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class SimpleFeishuUser {
    private String name;
    private String email;
    private String mobile;
    private String[] department_ids;
    private String gender;
    private String leader_employee_id;
    //数据库中的id
    private String EMPL_ID;
    private String setId;
    private String HR_STATUS;
    private String C_QUARTERS_ID;
    private String post;
    private String myUserId;

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String[] getDepartment_ids() {
        return department_ids;
    }

    public void setDepartment_ids(String[] department_ids) {
        this.department_ids = department_ids;
    }

    public String getEMPL_ID() {
        return EMPL_ID;
    }

    public void setEMPL_ID(String EMPL_ID) {
        this.EMPL_ID = EMPL_ID;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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


    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public SimpleFeishuUser(){}

    public SimpleFeishuUser(String name, String email, String mobile, String department_id, String gender, String leader_employee_id, String EMPL_ID,String setId,String HR_STATUS,String C_QUARTERS_ID,String post) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.department_ids=new String[1];
        this.department_ids[0] = department_id;
        if(StringUtils.equals("M",gender)){
            this.gender="1";
        }else{
            this.gender ="2";
        }
        this.leader_employee_id = leader_employee_id;
        this.EMPL_ID = EMPL_ID;
        this.setId=setId;
        this.HR_STATUS=HR_STATUS;
        this.C_QUARTERS_ID=C_QUARTERS_ID;
        this.post=post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLeader_employee_id() {
        return leader_employee_id;
    }

    public void setLeader_employee_id(String leader_employee_id) {
        this.leader_employee_id = leader_employee_id;
    }
}
