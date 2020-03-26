package com.wiwj.appinterface.Contoller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wiwj.appinterface.Dao.DepartmentInfoRepository;
import com.wiwj.appinterface.Dao.DeptCompareInfoRepository;
import com.wiwj.appinterface.Dao.UserCompareInfoRepository;
import com.wiwj.appinterface.Exception.MyException;
import com.wiwj.appinterface.Model.DepartmentInfo;
import com.wiwj.appinterface.Model.ResponseObj;
import com.wiwj.appinterface.Model.StatusCode;
import com.wiwj.appinterface.Result.DeptCompareInfo;
import com.wiwj.appinterface.Result.FeiShuDepartment;
import com.wiwj.appinterface.Result.SimpleFeishuUser;
import com.wiwj.appinterface.ServiceImpl.DepartmentServiceImpl;
import com.wiwj.appinterface.ServiceImpl.FeiShuApi;
import com.wiwj.appinterface.ServiceImpl.UserServiceImpl;
import com.wiwj.appinterface.ToolUtil.LzyApp;
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
            addAllDepartment(fdDate, deptAddList,addDeptCompareInfos, deptUpdateList,updateDeptCompareInfos, deptDeleteList,deleteDeptCompareInfos,departmentInfos);
            addAllUserNew(fdDate, userAddList, userUpdateList, userDeleteList);
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
            updateLeader(deptUpdateList,departmentInfos,fdDate);
            //更新人员直接负责人
            for (SimpleFeishuUser simpleFeishuUser : userUpdateList) {
                feiShuApi.updateFeiShuUserLeader(TenanAccessToken, simpleFeishuUser);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("主程序出错");
        }
    }

    /**
     * 添加所有层级部门
     * @return
     */
    public ResponseObj addAllDepartment(String fdDate, List<FeiShuDepartment> addList, List<DeptCompareInfo> addDeptCompareInfos, List<FeiShuDepartment> updateList, List<DeptCompareInfo> updateDeptCompareInfos, List<FeiShuDepartment> deleteList, List<DeptCompareInfo> deleteDeptCompareInfos, List<DepartmentInfo> departmentInfos){
        int maxLevel;
        String TenanAccessToken="";
        try {
            TenanAccessToken=departmentService.getTenanAccessToken();
            //获得层级数量
            maxLevel=departmentService.getTreeLevel();
            for(int level=1;level<=maxLevel;level++) {
                //获得该层级新增，修改，删除list
                departmentService.addDepartmentByLevelNew(level,fdDate, addList,addDeptCompareInfos, updateList,updateDeptCompareInfos, deleteList,deleteDeptCompareInfos,departmentInfos,TenanAccessToken);
            }
        } catch (Exception ex) {
            MyException mex = new MyException(ex);
            ResponseObj re = new ResponseObj(mex.getStatuscode(),mex.getMessage(),mex.getData());
            ex.printStackTrace();
            log.error("批量添加部门错误" + re.tojson());
            return  re;
        }
        return  new ResponseObj(StatusCode.success,null,null);
    }

    /**
     * 获取所有部门列表
     * @param FD_HR_DATE
     * @param addList
     * @param updateList
     * @param deleteList
     * @return
     */
    public ResponseObj addAllUserNew(String FD_HR_DATE,List<SimpleFeishuUser> addList,List<SimpleFeishuUser> updateList,List<SimpleFeishuUser> deleteList){
        String TenanAccessToken="";
        try {
            TenanAccessToken=userService.getTenanAccessToken();
            //获得新增，修改，删除list
            userService.batchAddUser(FD_HR_DATE,addList,updateList,deleteList,TenanAccessToken);
        } catch (Exception ex) {
            MyException mex = new MyException(ex);
            ResponseObj re = new ResponseObj(mex.getStatuscode(),mex.getMessage(),mex.getData());
            ex.printStackTrace();
            log.error("批量添加部门错误" + re.tojson());
            return  re;
        }
        return  new ResponseObj(StatusCode.success,null,null);
    }

    /**
     * 更新部门负责人
     * @param deptupdateList
     * @param departmentInfos
     * @param fdDate
     */
    public void updateLeader(List<FeiShuDepartment> deptupdateList, List<DepartmentInfo> departmentInfos,String fdDate){
        String TenanAccessToken="";
        try {
            TenanAccessToken=userService.getTenanAccessToken();
            for(int i=0;i<deptupdateList.size();i++){
                FeiShuDepartment feiShuDepartment=deptupdateList.get(i);
                DepartmentInfo departmentInfo=departmentInfos.get(i);
                if((StringUtils.isEmpty(feiShuDepartment.getLeader_user_id())||StringUtils.equals("null",feiShuDepartment.getLeader_user_id()))&& (StringUtils.isEmpty(feiShuDepartment.getLeader_open_id())||StringUtils.equals("null",feiShuDepartment.getLeader_open_id()))){
                    String setId=departmentInfo.getSET_ID();
                    String deptId=departmentInfo.getDEPT_ID();
                    List<SimpleFeishuUser> leaders=userService.getLeader(fdDate,setId,deptId,departmentInfo.getMANAGER_POSN());
                    SimpleFeishuUser leader=leaders.get(0);
                    feiShuDepartment.setLeader_user_id(leader.getLeader_user_id());
                }
                feiShuApi.updateDepartmentLeader(TenanAccessToken,feiShuDepartment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
