package com.wiwj.appinterface.Model;

public enum  StatusCode {

    success(0,"成功"),
    success_part(1,"部分成功:有些号码无法查询到用户"),
    error_parm(50000,"传入参数异常"),
    error_code(50001,"code或者access_token无效"),
    error_excel(50002,"文件后缀错误"),
    error_group_create(50003,"创建群失败"),
    error_group_unsserid(50004,"创建群失败：手机号未找到老师或者学生"),
    error_group_unsetadmin(50005,"未设置群主"),
    error_group_update(50006,"更新群主失败"),
    error_group_botdete(50007,"机器人移除失败"),
    error_io(50008,"ExcelIo错误"),
    error_json(50009,"未识别到多租户配置文件"),
    error_auth(50010,"超时，请重新开启建群机器人"),
    error_jsonText(50011,"多租户配置文件中参数配置异常"),
    error_getuserinfo(50012,"通讯录批量获取用户信息异常"),
    error_addusergroup(50012,"200人以上拉人进群异常"),
    error_group_createusers0(50013,"符合建群的用户数量不足"),
    error_group_codefindstudentworkstudent(50014,"未找到对应的用户请联系管理员"),
    error_group_accesstoken(50015,"重试三次后获取accesstoken失败"),
    error_unkown(59999,"未知错误"),

    //------------------------新增
    error_batch_add_department(50101,"批量添加部门失败"),
    error_delete_department(50102,"删除部门失败"),
    error_update_department(50103,"更新部门失败"),
    error_add_user(50201,"新增用户失败"),
    error_batchadd_user(50202,"批量新增用户失败"),
    error_delete_user(50203,"删除用户失败")
    ;

    StatusCode(Integer code, String message) {
        Code = code;
        Message = message;
    }

    public Integer Code;
    public  String Message;


    @Override
    public String toString() {
        return "StatusCode{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                '}';
    }
}
