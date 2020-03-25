package com.uestc.appinterface.Result;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeiShuUser {
    private String name;
    private String email;
    private String mobile;
    private String[] department_ids;
    private boolean mobile_visible;
    private String city;
    private String country;
    private int gender;
    private int employee_type;
    private int join_time;
    private String leader_employee_id;
    private String leader_open_id;
    private String employee_id;
    private String employee_no;
    private boolean need_send_notification;
    private String custom_attrs;
    private String work_station;

    public FeiShuUser(){

    }
//    public FeiShuUser(String name, String email, String mobile, String[] department_ids, boolean mobile_visible, String city, String country, String gender, int employee_type, int join_time, String leader_employee_id, String leader_open_id, String employee_id, String employee_no, boolean need_send_notification, String custom_attrs, String work_station) {
//        this.name = name;
//        this.email = email;
//        this.mobile = mobile;
//        this.department_ids = department_ids;
//        this.mobile_visible = mobile_visible;
//        this.city = city;
//        this.country = country;
//        if(StringUtils.equals("M",gender)){
//            this.gender=1;
//        }else{
//            this.gender =2;
//        }
//        this.employee_type = employee_type;
//        this.join_time = join_time;
//        this.leader_employee_id = leader_employee_id;
//        this.leader_open_id = leader_open_id;
//        this.employee_id = employee_id;
//        this.employee_no = employee_no;
//        this.need_send_notification = need_send_notification;
//        this.custom_attrs = custom_attrs;
//        this.work_station = work_station;
//    }
    public FeiShuUser(String name, String email, String mobile, String department_id, String gender, String leader_employee_id, String employee_id) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.department_ids=new String[1];
        this.department_ids[0] = department_id;
        if(StringUtils.equals("M",gender)){
            this.gender=1;
        }else{
            this.gender =2;
        }
        this.leader_employee_id = leader_employee_id;
        this.employee_id = employee_id;
        //this.mobile_visible = mobile_visible;
//        this.city = city;
////        this.country = country;

        //this.employee_type = employee_type;
        //this.join_time = join_time;  暂时没加，要求转为以秒为单位的 Unix 时间戳

        //this.leader_open_id = leader_open_id;

        //this.employee_no = employee_no; 工号不知道是什么
        //暂时不填
//        this.need_send_notification = need_send_notification;
//        this.custom_attrs = custom_attrs;
//        this.work_station = work_station;
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

    public String[] getDepartment_ids() {
        return department_ids;
    }

    public void setDepartment_ids(String[] department_ids) {
        this.department_ids = department_ids;
    }

    public boolean isMobile_visible() {
        return mobile_visible;
    }

    public void setMobile_visible(boolean mobile_visible) {
        this.mobile_visible = mobile_visible;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(int employee_type) {
        this.employee_type = employee_type;
    }

    public int getJoin_time() {
        return join_time;
    }

    public void setJoin_time(int join_time) {
        this.join_time = join_time;
    }

    public String getLeader_employee_id() {
        return leader_employee_id;
    }

    public void setLeader_employee_id(String leader_employee_id) {
        this.leader_employee_id = leader_employee_id;
    }

    public String getLeader_open_id() {
        return leader_open_id;
    }

    public void setLeader_open_id(String leader_open_id) {
        this.leader_open_id = leader_open_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public boolean isNeed_send_notification() {
        return need_send_notification;
    }

    public void setNeed_send_notification(boolean need_send_notification) {
        this.need_send_notification = need_send_notification;
    }

    public String getCustom_attrs() {
        return custom_attrs;
    }

    public void setCustom_attrs(String custom_attrs) {
        this.custom_attrs = custom_attrs;
    }

    public String getWork_station() {
        return work_station;
    }

    public void setWork_station(String work_station) {
        this.work_station = work_station;
    }
}
