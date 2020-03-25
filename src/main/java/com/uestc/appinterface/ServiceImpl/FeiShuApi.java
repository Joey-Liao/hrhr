package com.uestc.appinterface.ServiceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uestc.appinterface.Exception.MyException;
import com.uestc.appinterface.Model.StatusCode;
import com.uestc.appinterface.Result.FeiShuDepartment;
import com.uestc.appinterface.Result.SimpleFeishuUser;
import com.uestc.appinterface.ToolUtil.HttpHelper;
import com.uestc.appinterface.ToolUtil.LzyApp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;



/****
 * 重要事项：飞书开放平台提的的userid = employeeID
 *
 *
 *
 *
 */
@Service
public class FeiShuApi {

    @Autowired
    LzyApp lzyApp;
    Logger log = LoggerFactory.getLogger(this.getClass());
    /*****
     * 获取tenant_access_token
     * @return
     */

    public String GetTenantAccessToken(String AppId,String AppSecret) throws Exception {

        log.debug("请求Tenantaccesstoken：Appid："+AppId);
        log.debug("请求Tenantaccesstoken：AppSecret："+AppSecret);
        try{
            for(int sendtime = 0;sendtime<3;sendtime++){
                String Url = "https://open.feishu.cn/open-apis/auth/v3/tenant_access_token/internal/";
                JSONObject parm = new JSONObject();
                parm.put("app_id", AppId);
                parm.put("app_secret", AppSecret);
                HttpHeaders header = new HttpHeaders();
                JSONObject jsonobj = HttpHelper.sendPostRequest(Url, parm, header);
                if (jsonobj.get("code").toString().equals("0")) {//成功
                    return jsonobj.get("tenant_access_token").toString();
                }
                else
                {
                    log.error("获取tenant_access_token失败："+jsonobj.toString());

                }
            }
            throw new MyException(StatusCode.error_group_accesstoken,null,null);
        }
        catch(Exception ex) {
            throw  ex;
        }
    }
    /*****
     * 组装url
     * @param map
     * @return
     */
    private static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
            //s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }


    /*****
     * 获取app_access_token
     * @return
     */
    public String GetAppAccessToken(String AppId,String AppSecret) throws  Exception {

        log.debug("请求appaccesstoken：Appid："+AppId);
        log.debug("请求appaccesstoken：AppSecret："+AppSecret);
        try{
            for(int sendtime = 0;sendtime<3;sendtime++) {
                String Url = "https://open.feishu.cn/open-apis/auth/v3/app_access_token/internal/";
                JSONObject parm = new JSONObject();
                parm.put("app_id", AppId);
                parm.put("app_secret", AppSecret);
                HttpHeaders header = new HttpHeaders();
                JSONObject jsonobj = HttpHelper.sendPostRequest(Url, parm, header);
                if (jsonobj.get("code").toString().equals("0")) {//成功
                    return jsonobj.get("app_access_token").toString();
                }
                else
                {
                    log.error("获取app_access_token失败："+jsonobj.toString());

                }
            }
            throw new MyException(StatusCode.error_group_accesstoken,null,null);
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    /**
     * 获得部门下所有员工
     * @param AccessToken
     * @param department_id
     * @return
     * @throws Exception
     */
    public JSONArray getUsersByDepartment(String AccessToken,String department_id) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            parm.put("department_id", department_id);
            parm.put("offset", "0");
            parm.put("page_size", "100");
            parm.put("fetch_child", "true");
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/user/list"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            JSONArray result=new JSONArray();
            if(jsonObj.getJSONObject("data").getJSONArray("user_list") != null)
            {
                result = jsonObj.getJSONObject("data").getJSONArray("user_list");
            }
            return result;

        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * 删除用户
     * @param AccessToken
     * @param id
     * @throws Exception
     */
    public void simpleDeleteUser(String AccessToken, String id) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("employee_id",id);
            String Url = "https://open.feishu.cn/open-apis/contact/v1/user/delete";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_delete_user,jsonObj.toString(),null);
            }


        } catch (Exception ex) {
            throw ex;
        }
    }



    /**
     * 批量新增用户
     * @param AccessToken
     * @param feishuUsers
     * @return
     * @throws Exception
     */
    public String batchAddUser(String AccessToken, JSONArray feishuUsers) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("users",feishuUsers);
            String Url = "https://open.feishu.cn/open-apis/contact/v2/user/batch_add";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_batchadd_user,jsonObj.toString(),null);
            }
            else
            {
                //return  jsonObj.getJSONObject("data").get("task_id").toString();
                return jsonObj.toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * 获取用户详情
     * @param AccessToken
     * @param employee_ids
     * @return
     * @throws Exception
     */
    public String getFeiShuUserDetail(String AccessToken,String employee_ids) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            parm.put("employee_ids", employee_ids);
            String Url = "https://open.feishu.cn/open-apis/contact/v1/user/batch_get"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            String result = "";
            if(jsonObj.getJSONObject("data").getJSONArray("user_infos") != null)
            {
                result = jsonObj.getJSONObject("data").getJSONArray("user_infos").toString();
            }
            return result;

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 获取部门详情
     * @param AccessToken
     * @param departmentId
     * @return
     * @throws Exception
     */
    public String getDepartmentDetail(String AccessToken,String departmentId) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            parm.put("department_id", departmentId);
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/info/get"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            String result = "";
            if(jsonObj.getJSONObject("data").getJSONObject("department_info") != null)
            {
                result = jsonObj.getJSONObject("data").toString();
            }
            return result;

        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * 批量获取部门详情
     * @param AccessToken
     * @param departmentIds
     * @return
     * @throws Exception
     */
    public String batchGetDepartmentDetail(String AccessToken,String[] departmentIds) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            if(departmentIds != null) {
                for (String departmentId : departmentIds) {
                    parm.put("department_ids", departmentId);
                }
            }
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/detail/batch_get"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            String result = "";
            if(jsonObj.getJSONObject("data").getJSONArray("department_infos") != null)
            {
                result = jsonObj.getJSONObject("data").getJSONArray("department_infos").toString();
            }
            return result;

        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * 获取子部门列表
     * @param AccessToken
     * @param department_id
     * @param offset
     * @param page_size
     * @param fetch_child
     * @return
     * @throws Exception
     */
    public String getDepartmentList(String AccessToken,String department_id,int offset,int page_size,boolean fetch_child) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            parm.put("department_id", department_id);
            parm.put("offset", offset+"");
            parm.put("page_size", page_size+"");
            parm.put("fetch_child",fetch_child+"");
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/simple/list"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            String result = "";
            if(jsonObj.getJSONObject("data").getJSONArray("department_infos") != null)
            {
                result = jsonObj.getJSONObject("data").getJSONArray("department_infos").toString();
            }
            return result;

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 获取子部门id列表
     * @param AccessToken
     * @param department_id
     * @return
     * @throws Exception
     */
    public List<String> getChildDepartmentIdList(String AccessToken,String department_id) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            parm.put("department_id", department_id);
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/list"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            JSONArray result=new JSONArray();
            if(jsonObj.getJSONObject("data").getJSONArray("departments_list") != null)
            {
                result = jsonObj.getJSONObject("data").getJSONArray("departments_list");
            }
            List<String> idList=result.toJavaList(String.class);
            return idList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 更新用户
     * @param AccessToken
     * @param simpleFeishuUser
     * @return
     * @throws Exception
     */
    public String updateFeiShuUser(String AccessToken, SimpleFeishuUser simpleFeishuUser) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();

            if((!StringUtils.isEmpty(simpleFeishuUser.getEmail()))&&(!StringUtils.equals("null",simpleFeishuUser.getEmail()))) {
                json.put("email", simpleFeishuUser.getEmail());
            }
            if((!StringUtils.isEmpty(simpleFeishuUser.getMobile()))&&(!StringUtils.equals("null",simpleFeishuUser.getMobile()))) {
                json.put("mobile",simpleFeishuUser.getMobile());
            }
            JSONObject custom_attrs=new JSONObject();
            JSONObject postKey=new JSONObject();
            postKey.put("value",simpleFeishuUser.getPost());
            custom_attrs.put(lzyApp.getJobpost_key(),postKey);
            if((!StringUtils.isEmpty(simpleFeishuUser.getPost()))&&(!StringUtils.equals("null",simpleFeishuUser.getPost()))) {
                json.put("custom_attrs",custom_attrs);
            }
            if((!StringUtils.isEmpty(simpleFeishuUser.getName()))&&(!StringUtils.equals("null",simpleFeishuUser.getName()))) {
                json.put("name",simpleFeishuUser.getName());
            }else{
                json.put("name","无名");
            }
            json.put("employee_id", simpleFeishuUser.getUser_id());
            json.put("department_ids",simpleFeishuUser.getDepartments());
            if(simpleFeishuUser.getGender()!=0) {
                json.put("gender", simpleFeishuUser.getGender());
            }
            String Url = "https://open.feishu.cn/open-apis/contact/v1/user/update";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_update_department,jsonObj.toString(),null);
            }
            else
            {
                return  jsonObj.getJSONObject("data").toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 更新人员直接负责人
     * @param AccessToken
     * @param simpleFeishuUser
     * @return
     * @throws Exception
     */
    public String updateFeiShuUserLeader(String AccessToken, SimpleFeishuUser simpleFeishuUser) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            if((!StringUtils.isEmpty(simpleFeishuUser.getLeader_user_id()))&&(!StringUtils.equals("null",simpleFeishuUser.getLeader_user_id()))) {
                json.put("leader_employee_id",simpleFeishuUser.getLeader_user_id());
            }
            json.put("employee_id", simpleFeishuUser.getUser_id());
            String Url = "https://open.feishu.cn/open-apis/contact/v1/user/update";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_update_department,jsonObj.toString(),null);
            }
            else
            {
                return  jsonObj.getJSONObject("data").toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * 更新部门负责人
     * @param AccessToken
     * @param feiShuDepartment
     * @return
     * @throws Exception
     */
    public String updateDepartmentLeader(String AccessToken, FeiShuDepartment feiShuDepartment) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("id",feiShuDepartment.getId());
            if((!StringUtils.isEmpty(feiShuDepartment.getLeader_user_id()))&&(!StringUtils.equals("null",feiShuDepartment.getLeader_user_id()))) {
                json.put("leader_employee_id",feiShuDepartment.getLeader_user_id());
            }
            if((!StringUtils.isEmpty(feiShuDepartment.getLeader_open_id()))&&(!StringUtils.equals("null",feiShuDepartment.getLeader_open_id()))) {
                json.put("leader_open_id",feiShuDepartment.getLeader_open_id());
            }
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/update";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_update_department,jsonObj.toString(),null);
            }
            else
            {
                return  jsonObj.getJSONObject("data").get("department_info").toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * 更新部门
     * @param AccessToken
     * @param feiShuDepartment
     * @return
     * @throws Exception
     */
    public String updateDepartment(String AccessToken, FeiShuDepartment feiShuDepartment) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            if((!StringUtils.isEmpty(feiShuDepartment.getName()))&&(!StringUtils.equals("null",feiShuDepartment.getName()))){
                json.put("name", feiShuDepartment.getName());
            }
            if((!StringUtils.isEmpty(feiShuDepartment.getParent_id()))&&(!StringUtils.equals("null",feiShuDepartment.getParent_id()))) {
                json.put("parent_id", feiShuDepartment.getParent_id());
            }
            json.put("id",feiShuDepartment.getId());

            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/update";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_update_department,jsonObj.toString(),null);
            }
            else
            {
                return  jsonObj.getJSONObject("data").get("department_info").toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 删除该部门和子部门
     * @param AccessToken
     * @param id
     * @throws Exception
     */
    public void  deleteAllChildDepartment(String AccessToken, String id) throws  Exception {
        List<String> lists=getChildDepartmentIdList(AccessToken,id);
        JSONArray users=getUsersByDepartment(AccessToken,id);
        String re=getDepartmentDetail(AccessToken,id);
        if ((!StringUtils.isEmpty(re)) && (!StringUtils.equals("null", re))) {//飞书中部门存在
            for (int i=0;i<users.size();i++){
                JSONObject user=users.getJSONObject(i);
                simpleDeleteUser(AccessToken,(String)user.get("employee_id"));
            }
            if (lists.size()==0){//没有子部门
                deleteDepartment(AccessToken,id);
            }else{//有子部门
                for(String childId:lists){
                    deleteAllChildDepartment(AccessToken,childId);
                }
                deleteDepartment(AccessToken,id);
            }
        }

    }
    /**
     * 删除部门
     * @param AccessToken
     * @param id
     * @throws Exception
     */
    public void deleteDepartment(String AccessToken, String id) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("id",id);
            String Url = "https://open.feishu.cn/open-apis/contact/v1/department/delete";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_delete_department,jsonObj.toString(),null);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }
    /**
     * 批量新增部门
     * @param AccessToken
     * @param departments
     * @return
     * @throws Exception
     */
    public String batchAddDepartment(String AccessToken, JSONArray departments) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("departments",departments);
            String Url = "https://open.feishu.cn/open-apis/contact/v2/department/batch_add";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_batch_add_department,jsonObj.toString(),null);
            }
            else
            {
                //return  jsonObj.getJSONObject("data").get("task_id").toString();
                return jsonObj.toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    /****
     * 按照手机号码查询userid和openid
     * @param AccessToken
     * @param Mobile
     * @return
     */
    public String FindUserId(String AccessToken,String Mobile) throws  Exception{
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            Map<String, String> parm = new IdentityHashMap<>();
            parm.put("mobiles", Mobile);

            String Url = "https://open.feishu.cn/open-apis/user/v1/batch_get_id"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            String result = "";
            if(jsonObj.getJSONObject("data").getJSONObject("mobile_users") != null)
            {
                result = jsonObj.getJSONObject("data").getJSONObject("mobile_users").getJSONArray(Mobile).getJSONObject(0).get("user_id").toString();
            }

            return result;

        } catch (Exception ex) {
            throw ex;
        }
    }

    /*****
     * 创建群
     * @param AccessToken
     * @param OpenIds
     * @param UserIds
     * @param Name
     * @param Description
     * @return
     * @throws Exception
     */
    public String CreateGroup(String AccessToken, JSONArray OpenIds, JSONArray UserIds, String Name, String Description) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("Authorization","Bearer "+AccessToken);
            json.put("open_ids",OpenIds);
            json.put("user_ids",UserIds);
            json.put("name",Name);
            json.put("description","机器人创建群");

            String Url = "https://open.feishu.cn/open-apis/chat/v4/create/";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_group_create,jsonObj.toString(),null);
            }
            else
            {
                return  jsonObj.getJSONObject("data").get("chat_id").toString();
            }

        } catch (Exception ex) {
            throw ex;
        }
    }


    /****
     * 拉人进群
     * @param AccessToken
     * @param OpenIds
     * @param UserIds
     * @throws Exception
     */
    public void AddUserGroup(String Chat_id, String AccessToken, JSONArray OpenIds, JSONArray UserIds) throws  Exception {
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("chat_id",Chat_id);
            json.put("open_ids",OpenIds);
            json.put("user_ids",UserIds);

            String Url = "https://open.feishu.cn/open-apis/chat/v4/chatter/add/";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);
            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_addusergroup,jsonObj.toString(),null);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }



    /*****
     * 更新群信息
     * @param AccessToken
     * @param ChatId
     * @param OpenId
     * @param UserId
     * @return
     * @throws Exception
     */
    public void UpdateGroup(String AccessToken,String ChatId,String OpenId,String UserId) throws MyException{
        try{
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("Authorization","Bearer "+AccessToken);
            json.put("chat_id",ChatId);
            json.put("owner_open_id",OpenId);
            json.put("owner_user_id",UserId);

            String Url = "https://open.feishu.cn/open-apis/chat/v4/update/";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);

            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_group_update,jsonObj.toString(),null);
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /*****
     * 将机器人移除
     * @param AccessToken
     * @param ChatId
     * @return
     * @throws MyException
     */
    public  void BootRemove(String AccessToken,String ChatId) throws Exception {
        try{
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+AccessToken);
            JSONObject json = new JSONObject();
            json.put("chat_id",ChatId);

            String Url = "https://open.feishu.cn/open-apis/bot/v4/remove";
            JSONObject jsonObj  =  HttpHelper.sendPostRequest(Url, json,header);

            if(!jsonObj.get("code").toString().equals("0"))
            {
                throw  new MyException(StatusCode.error_group_botdete,jsonObj.toString(),null);
            }
        }
        catch (Exception ex)
        {
            throw  ex;
        }
    }


    public JSONObject GetUserInfo(String code,String App_Access_token) throws Exception {

        try {
            HttpHeaders header = new HttpHeaders();
            JSONObject json = new JSONObject();
            json.put("app_access_token", App_Access_token);
            json.put("grant_type", "authorization_code");
            json.put("code", code);

            String Url = "https://open.feishu.cn/open-apis/authen/v1/access_token";
            JSONObject jsonObj = HttpHelper.sendPostRequest(Url, json, header);

            if (!jsonObj.get("code").toString().equals("0")) {
                throw new MyException(StatusCode.error_code, jsonObj.toString(),null);
            } else {
                return jsonObj.getJSONObject("data");
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public JSONObject BathGetUserInfo(String[] openIds,String []employeeIds,String TenantAccessToken) throws Exception {

        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+TenantAccessToken);
            Map<String, String> parm = new HashMap<>();
            if(openIds != null) {
                for (String openid : openIds) {
                    parm.put("open_ids", openid);
                }
            }
            if(employeeIds != null) {
                for (String employeeId : employeeIds) {
                    parm.put("employee_ids", employeeId);
                }
            }

            String Url = "https://open.feishu.cn/open-apis/contact/v1/user/batch_get"+"?"+getUrlParamsByMap(parm);
            JSONObject jsonObj  =  HttpHelper.sendGetRequest(Url, header);
            String result = "";
            if(jsonObj.getString("code").equals("0"))
            {
                return  jsonObj.getJSONObject("data");
            }
            else
            {
                throw  new MyException(StatusCode.error_getuserinfo,jsonObj.toString(),null);
            }

        } catch (Exception ex) {
            throw ex;
        }
    }

    //code2session :小程序专用，拿取用户信息
    public JSONObject GetCode2session(String code,String App_Access_token) throws  Exception {

        try{
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer "+App_Access_token);
            JSONObject json = new JSONObject();
            json.put("code", code);

            String Url = "https://open.feishu.cn/open-apis/mina/v2/tokenLoginValidate";
            JSONObject jsonObj = HttpHelper.sendPostRequest(Url, json, header);

            if (!jsonObj.get("code").toString().equals("0")) {
                throw new MyException(StatusCode.error_code, jsonObj.toString(),null);
            } else {
                return jsonObj.getJSONObject("data");
            }
        }
        catch(Exception ex) {
            throw ex;
        }
    }





}
