package com.uestc.appinterface.Contoller;

import com.uestc.appinterface.Exception.MyException;
import com.uestc.appinterface.Model.DepartmentInfo;
import com.uestc.appinterface.Model.ResponseObj;
import com.uestc.appinterface.Model.StatusCode;
import com.uestc.appinterface.Result.FeiShuDepartment;
import com.uestc.appinterface.Result.SimpleFeishuUser;
import com.uestc.appinterface.ServiceImpl.FeiShuApi;
import com.uestc.appinterface.ServiceImpl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserServiceImpl userService;
    @Autowired
    FeiShuApi feiShuApi;

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
