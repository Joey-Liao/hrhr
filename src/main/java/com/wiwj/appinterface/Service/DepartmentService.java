package com.wiwj.appinterface.Service;

import com.wiwj.appinterface.Model.DepartmentInfo;
import com.wiwj.appinterface.Result.DeptCompareInfo;
import com.wiwj.appinterface.Result.FeiShuDepartment;

import java.util.List;

public interface DepartmentService {

    void deleDepartmentById(String id,String TenanAccessToken)throws Exception;

    String updateFeishuDepartment(FeiShuDepartment feiShuDepartment,String TenanAccessToken)throws Exception;

    String getChildDeptList(String department_id,int offset,int page_size,boolean fetch_child,String TenanAccessToken)throws Exception;

    String batchGetDeptDetail(String[] departmentIds,String TenanAccessToken)throws Exception;

    int getTreeLevel()throws Exception;

    String getDeptDetail(String departmentId,String TenanAccessToken) throws Exception;

    boolean deptComparePreperties(DeptCompareInfo deptCompareInfo, DepartmentInfo departmentInfo, String paDeptId) throws Exception;

    DeptCompareInfo doChangeDeptInfo(DepartmentInfo departmentInfo, String paDeptId) throws Exception;

    FeiShuDepartment getFeishuDepartment(DepartmentInfo departmentInfo, String PaDeptId, String TenanAccessToken) throws Exception;

    void addDepartmentByLevelNew(int level,String fdDate,List<FeiShuDepartment> addList,List<DeptCompareInfo> addDeptCompareInfos,List<FeiShuDepartment> updateList,List<DeptCompareInfo> updateDeptCompareInfos,List<FeiShuDepartment> deleteList,List<DeptCompareInfo> deleteDeptCompareInfos,List<DepartmentInfo> departmentInfos)throws Exception;
    }
