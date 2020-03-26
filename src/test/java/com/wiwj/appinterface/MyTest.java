package com.wiwj.appinterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wiwj.appinterface.Contoller.TestController;
import com.wiwj.appinterface.Dao.DepartmentInfoRepository;
import com.wiwj.appinterface.Dao.DepartmentTreeInfoRepository;
import com.wiwj.appinterface.Dao.DeptCompareInfoRepository;
import com.wiwj.appinterface.Dao.UserCompareInfoRepository;
import com.wiwj.appinterface.Model.*;
import com.wiwj.appinterface.Result.*;
import com.wiwj.appinterface.ServiceImpl.DepartmentServiceImpl;
import com.wiwj.appinterface.ServiceImpl.FeiShuApi;
import com.wiwj.appinterface.ServiceImpl.UserServiceImpl;
import com.wiwj.appinterface.ToolUtil.LzyApp;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    LzyApp lzyApp;
    @Autowired
    FeiShuApi feiShuApi;
    @Autowired
    DeptCompareInfoRepository deptCompareInfoRepository;
    @Autowired
    DepartmentServiceImpl departmentService;
@Autowired
    DepartmentTreeInfoRepository departmentTreeInfoRepository;
@Autowired
    UserServiceImpl userService;
@Autowired
    UserCompareInfoRepository userCompareInfoRepository;
@Autowired
    DepartmentInfoRepository departmentInfoRepository;
    @Autowired
    TestController testController;
    @Test
    public void test(){
        String appId=lzyApp.getAppId();
        String appSecret=lzyApp.getAppSecret();
        String token="";
        try {
            token=feiShuApi.GetTenantAccessToken(appId,appSecret);
            System.out.println("token____________________________________________________________"+token);
            //feiShuApi.getDepartmentList(token,"0",0,10,true);
            Thread.sleep(300000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        try {
            int re=departmentService.getTreeLevel();
            System.out.println(re+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6(){
        String appId=lzyApp.getAppId();
        String appSecret=lzyApp.getAppSecret();
        String token="";
        try {
            token=feiShuApi.GetTenantAccessToken(appId,appSecret);
            String re=departmentService.getChildDeptList(0+"",0,10,true,token);
            System.out.println(re+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test7(){
        try {
            String re=departmentService.getDeptDetail("100000000110003",departmentService.getTenanAccessToken());
            if ((!StringUtils.isEmpty(re))&&(!StringUtils.equals("null",re))){//判断是否存在部门
                System.out.println("存在-----------------------------------------");
            }else {
                System.out.println("空——————————————————————————");
            }
            System.out.println(re+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test8(){
        String appId=lzyApp.getAppId();
        String appSecret=lzyApp.getAppSecret();
        String token="";
        try {
            token=feiShuApi.GetTenantAccessToken(appId,appSecret);
            departmentService.deleDepartmentById("100000000110003",token);
            String re=departmentService.getDeptDetail("100000000110003",departmentService.getTenanAccessToken());
            System.out.println(re+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test9(){
        String appId=lzyApp.getAppId();
        String appSecret=lzyApp.getAppSecret();
        String token="";
        try {
            token=feiShuApi.GetTenantAccessToken(appId,appSecret);
            SimpleFeishuUser simpleFeishuUser=new SimpleFeishuUser();
            simpleFeishuUser.setUser_id("7777777777");
            simpleFeishuUser.setMobile("13699999911");
            simpleFeishuUser.setName("阿强");
            String[] de=new String[1];
            de[0]="100001000000001";
            simpleFeishuUser.setDepartments(de);
            simpleFeishuUser.setPost("测试员工777777");
//            String re=feiShuApi.updateFeiShuUser(token,simpleFeishuUser);
            JSONObject jsonObject=JSONObject.parseObject(JSON.toJSONString(simpleFeishuUser));
            JSONArray custom_attrs=new JSONArray();
            JSONObject postKey=new JSONObject();
            JSONObject value=new JSONObject();
            value.put("text",simpleFeishuUser.getPost());
            postKey.put("id",lzyApp.getJobpost_key());
            postKey.put("value",value);
            custom_attrs.add(postKey);
            jsonObject.put("custom_attrs",custom_attrs);
            JSONArray jsonArray=new JSONArray();
            jsonArray.add(jsonObject);
            String re=feiShuApi.batchAddUser(token,jsonArray);
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test14(){
        try {
            //testController.doMain("1000-11-11");
            String Token=departmentService.getTenanAccessToken();
            List<FeiShuDepartment> deptaddList=new ArrayList<>();
            List<FeiShuDepartment> deptupdateList=new ArrayList<>();
            List<FeiShuDepartment> deptdeleteList=new ArrayList<>();
            List<DepartmentInfo> departmentInfos=new ArrayList<>();
            List<DeptCompareInfo> addDeptCompareInfos=new ArrayList<>();
            List<DeptCompareInfo> updateDeptCompareInfos=new ArrayList<>();
            List<DeptCompareInfo> deleteDeptCompareInfos=new ArrayList<>();
            departmentService.addDepartmentByLevelNew(1,"1000-11-11",deptaddList,addDeptCompareInfos,deptupdateList,updateDeptCompareInfos,deptdeleteList,deleteDeptCompareInfos,departmentInfos,Token);
            departmentService.addDepartmentByLevelNew(2,"1000-11-11",deptaddList,addDeptCompareInfos,deptupdateList,updateDeptCompareInfos,deptdeleteList,deleteDeptCompareInfos,departmentInfos,Token);
            //departmentService.addDepartmentByLevelNew(3,"1000-11-11",deptaddList,addDeptCompareInfos,deptupdateList,updateDeptCompareInfos,deptdeleteList,deleteDeptCompareInfos,departmentInfos,Token);
                        for(FeiShuDepartment feiShuDepartment:deptaddList){
                System.out.println(feiShuDepartment.getId());
            }
            System.out.println("deptaddList.size()="+deptaddList.size());
            //删除人员
            //删除部门
//            for(FeiShuDepartment feiShuDepartment:deptaddList){
//
//                departmentService.deleDepartmentById(feiShuDepartment.getId(),Token);
//            }
//            //新增部门
//            //list切片
//            List<List<FeiShuDepartment>> deptaddLists=Lists.partition(deptaddList,999);
//            for(List<FeiShuDepartment> smallAddList:deptaddLists) {
//                for(FeiShuDepartment feiShuDepartment:smallAddList){
//                    System.out.println(feiShuDepartment.getId());
//                }
//                System.out.println("smallAddList.size()="+smallAddList.size());
//                JSONArray addArray = JSONArray.parseArray(JSON.toJSONString(smallAddList));
//                if ((!addArray.isEmpty()) && (!(addArray.size() < 1))) {
//
//                    String re=feiShuApi.batchAddDepartment(Token, addArray);
//                    System.out.println(re);
//                }else{
//                    System.out.println("为空");
//                }
//            }
//            //反写新增部门结果
//            for(DeptCompareInfo deptCompareInfo:addDeptCompareInfos){
//                deptCompareInfoRepository.save(deptCompareInfo);
//            }
//            //修改部门
//            for(FeiShuDepartment feiShuDepartment:deptupdateList){
//                feiShuApi.updateDepartment(Token,feiShuDepartment);
//            }
//            //反写修改部门结果
//            for(DeptCompareInfo deptCompareInfo:updateDeptCompareInfos){
//                deptCompareInfoRepository.save(deptCompareInfo);
//            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
