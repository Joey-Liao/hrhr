package com.uestc.appinterface.Contoller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.uestc.appinterface.Dao.DepartmentInfoRepository;
import com.uestc.appinterface.Dao.DeptCompareInfoRepository;
import com.uestc.appinterface.Dao.UserCompareInfoRepository;
import com.uestc.appinterface.Model.DepartmentInfo;
import com.uestc.appinterface.Result.DeptCompareInfo;
import com.uestc.appinterface.Result.FeiShuDepartment;
import com.uestc.appinterface.Result.SimpleFeishuUser;
import com.uestc.appinterface.ServiceImpl.DepartmentServiceImpl;
import com.uestc.appinterface.ServiceImpl.FeiShuApi;
import com.uestc.appinterface.ServiceImpl.UserServiceImpl;
import com.uestc.appinterface.ToolUtil.LzyApp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    DepartmentController departmentController;
    @Autowired
    UserController userController;
    @Autowired
    FeiShuApi feiShuApi;
    @Autowired
    DepartmentServiceImpl departmentService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    DeptCompareInfoRepository deptCompareInfoRepository;
    @Autowired
    UserCompareInfoRepository userCompareInfoRepository;
    @Autowired
    DepartmentInfoRepository departmentInfoRepository;
    @Autowired
    LzyApp lzyApp;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public void doMain(String fdDate){
        List<FeiShuDepartment> deptAddList=new ArrayList<>();
        List<FeiShuDepartment> deptUpdateList=new ArrayList<>();
        List<FeiShuDepartment> deptDeleteList=new ArrayList<>();
        List<DepartmentInfo> departmentInfos=new ArrayList<>();
        List<DeptCompareInfo> addDeptCompareInfos=new ArrayList<>();
        List<DeptCompareInfo> updateDeptCompareInfos=new ArrayList<>();
        List<DeptCompareInfo> deleteDeptCompareInfos=new ArrayList<>();
        List<SimpleFeishuUser> userAddList=new ArrayList<>();
        List<SimpleFeishuUser> userUpdateList=new ArrayList<>();
        List<SimpleFeishuUser> userDeleteList=new ArrayList<>();
        try {
            String TenanAccessToken = departmentService.getTenanAccessToken();
            //获得部门数据并新增部门反写数据
            departmentController.addAllDepartment(fdDate, deptAddList,addDeptCompareInfos, deptUpdateList,updateDeptCompareInfos, deptDeleteList,deleteDeptCompareInfos,departmentInfos);
            userController.addAllUserNew(fdDate, userAddList, userUpdateList, userDeleteList);
            //修改部门
            for(FeiShuDepartment feiShuDepartment:deptUpdateList){
                String re=feiShuApi.updateDepartment(TenanAccessToken,feiShuDepartment);
            }
            //反写修改部门结果
            for(DeptCompareInfo deptCompareInfo:updateDeptCompareInfos){
                deptCompareInfoRepository.save(deptCompareInfo);
            }
            //新增人员
            //list切片
            List<List<SimpleFeishuUser>> useraddLists = Lists.partition(userAddList, 999);
            for (List<SimpleFeishuUser> smallAddList : useraddLists) {
                JSONArray addArray=new JSONArray();
                for(SimpleFeishuUser simpleFeishuUser:smallAddList){
                    JSONObject jsonObject=JSONObject.parseObject(JSON.toJSONString(simpleFeishuUser));

                    JSONArray custom_attrs=new JSONArray();
                    JSONObject postKey=new JSONObject();
                    JSONObject value=new JSONObject();
                    value.put("text",simpleFeishuUser.getPost());
                    postKey.put("id",lzyApp.getJobpost_key());
                    postKey.put("value",value);
                    custom_attrs.add(postKey);
                    jsonObject.put("custom_attrs",custom_attrs);

                    addArray.add(jsonObject);


                }
                if ((!addArray.isEmpty()) && (!(addArray.size() < 1))) {
                    String re=feiShuApi.batchAddUser(TenanAccessToken,addArray);
                    log.info("批量新增人员返回结果："+re);
                }
            }
            //反写新增人员结果
            for(SimpleFeishuUser simpleFeishuUser:userAddList){
                userService.doChangeUserInfo(simpleFeishuUser);
            }
            //修改人员
            for (SimpleFeishuUser simpleFeishuUser : userUpdateList) {
                feiShuApi.updateFeiShuUser(TenanAccessToken, simpleFeishuUser);
                //反写修改人员结果
                userService.doChangeUserInfo(simpleFeishuUser);
            }
            //删除人员
            for (SimpleFeishuUser simpleFeishuUser : userDeleteList) {
                feiShuApi.simpleDeleteUser(TenanAccessToken, simpleFeishuUser.getUser_id());
                //反写结果
                userCompareInfoRepository.deleteByEMPL_ID(simpleFeishuUser.getUser_id());
            }
            //删除部门
            for(FeiShuDepartment feiShuDepartment:deptDeleteList){
                feiShuApi.deleteDepartment(TenanAccessToken,feiShuDepartment.getId());
            }
            //反写删除部门结果
            for(DeptCompareInfo deptCompareInfo:deleteDeptCompareInfos){
                deptCompareInfoRepository.deleteByDEPT_IDAndSET_ID(deptCompareInfo.getDEPT_ID(),deptCompareInfo.getSET_ID());
            }
            //更新部门负责人
            userController.updateLeader(deptUpdateList,departmentInfos,fdDate);
            //更新人员直接负责人
            for (SimpleFeishuUser simpleFeishuUser : userUpdateList) {
                feiShuApi.updateFeiShuUserLeader(TenanAccessToken, simpleFeishuUser);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("主程序出错");
        }
    }

}
