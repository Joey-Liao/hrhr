package com.wiwj.appinterface.Result;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class SimpleFeishuUser {
    private String name;
    private String email;
    private String mobile;
    private String[] departments;
    private int gender;
    private String leader_user_id;
    private String user_id;
    private String setId;
    private String HR_STATUS;
    private String C_QUARTERS_ID;
    private String post;

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
//    public String toJson(){
//        String re="{\"gender\":2,\"employee_id\":\"115407\",\"mobile\":\"13771983913\",\"name\":\"李曼\",\"leader_employee_id\":\"null\",\"department_ids\":[\"1000000061\"],\"email\":\"null\"}";
//        String re1="{";
//        re1+="\"gender\":"+gender+",";
//        re1+="\"employee_id\":"
//    }
    public SimpleFeishuUser(String name, String email, String mobile, String department_id, String gender, String leader_user_id, String user_id,String setId,String HR_STATUS,String C_QUARTERS_ID,String post) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.departments=new String[1];
        this.departments[0] = department_id;
        if(StringUtils.equals("M",gender)){
            this.gender=1;
        }else{
            this.gender =2;
        }
        this.leader_user_id = leader_user_id;
        this.user_id = user_id;
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

    public String[] getDepartments() {
        return departments;
    }

    public void setDepartments(String[] departments) {
        this.departments = departments;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLeader_user_id() {
        return leader_user_id;
    }

    public void setLeader_user_id(String leader_user_id) {
        this.leader_user_id = leader_user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
