package com.uestc.appinterface.Contoller;


import com.uestc.appinterface.Exception.MyException;
import com.uestc.appinterface.Model.DepartmentInfo;
import com.uestc.appinterface.Model.ResponseObj;
import com.uestc.appinterface.Model.StatusCode;
import com.uestc.appinterface.Result.DeptCompareInfo;
import com.uestc.appinterface.Result.FeiShuDepartment;
import com.uestc.appinterface.ServiceImpl.DepartmentServiceImpl;
import com.uestc.appinterface.ServiceImpl.FeiShuApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class DepartmentController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DepartmentServiceImpl departmentService;

    @Autowired
    FeiShuApi feiShuApi;

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

}
