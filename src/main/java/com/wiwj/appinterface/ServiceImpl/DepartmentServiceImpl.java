package com.wiwj.appinterface.ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.wiwj.appinterface.Dao.DepartmentInfoRepository;
import com.wiwj.appinterface.Dao.DepartmentTreeInfoRepository;
import com.wiwj.appinterface.Dao.DeptCompareInfoRepository;
import com.wiwj.appinterface.Model.DepartmentInfo;
import com.wiwj.appinterface.Model.DepartmentTreeInfo;
import com.wiwj.appinterface.Result.DeptCompareInfo;
import com.wiwj.appinterface.Result.FeiShuDepartment;
import com.wiwj.appinterface.Service.DepartmentService;
import com.wiwj.appinterface.ToolUtil.LzyApp;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentTreeInfoRepository departmentTreeInfoRepository;

    @Autowired
    DepartmentInfoRepository departmentInfoRepository;

    @Autowired
    DeptCompareInfoRepository deptCompareInfoRepository;

    @Autowired
    FeiShuApi feiShuApi;

    @Autowired
    LzyApp lzyApp;

    Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 获得TenanAccessToken
     * @return
     * @throws Exception
     */
    public String getTenanAccessToken()throws Exception{
        String appId=lzyApp.getAppId();
        String appSecret=lzyApp.getAppSecret();
        String TenanAccessToken=feiShuApi.GetTenantAccessToken(appId,appSecret);
        return TenanAccessToken;
    }

    /**
     * 大改后的新增部门
     * @param level
     * @param fdDate
     * @param addList
     * @param updateList
     * @param deleteList
     * @return
     * @throws Exception
     */
    public void addDepartmentByLevelNew(int level,String fdDate,List<FeiShuDepartment> addList,List<DeptCompareInfo> addDeptCompareInfos,List<FeiShuDepartment> updateList,List<DeptCompareInfo> updateDeptCompareInfos,List<FeiShuDepartment> deleteList,List<DeptCompareInfo> deleteDeptCompareInfos,List<DepartmentInfo> departmentInfos)throws Exception{
        String TenanAccessToken =getTenanAccessToken();
        //该树层级部门树信息
        if(StringUtils.isEmpty(fdDate)){//若该参数为空，则查询所有时间
            fdDate="1000-11-15";
        }
        log.info("部门新增，修改信息获取开始");
        List<DepartmentTreeInfo> deptTreeList=departmentTreeInfoRepository.findByTreeLevel(level+"");
        log.info("部门树数量ListSize:"+deptTreeList.size());
        for(DepartmentTreeInfo deptTree:deptTreeList) {
            String setId = deptTree.getSET_ID();
            String deptId = deptTree.getTREE_NODE();
            //该部门树对应的有效的部门信息
            List<DepartmentInfo> departmentInfoList = departmentInfoRepository.findByDeptIdAndSetId(deptId, setId);
            if (departmentInfoList.size() != 0) {//当OA部门表中存在该部门时
                //获取第一个对象
                DepartmentInfo departmentInfo=departmentInfoList.get(0);
                String PaDeptId = deptTree.getPARENT_NODE_NAME();
                //查自建记录表
                List<DeptCompareInfo> deptCompareInfos = deptCompareInfoRepository.findByDeptIdAndSetId(deptId, setId);
                //创建飞书部门类
                FeiShuDepartment feiShuDepartment = getFeishuDepartment(departmentInfo, PaDeptId, TenanAccessToken);
                if (deptCompareInfos.size() == 0) {//记录表中没有记录
                    //添加到新增部门列表
                    log.info("添加到deptaddlist："+JSON.toJSONString(feiShuDepartment));
                    addList.add(feiShuDepartment);
                    //反写结果，向自建记录表添加信息
                    addDeptCompareInfos.add(doChangeDeptInfo(departmentInfo, PaDeptId));
//                    //根据id查飞书系统中部门信息
//                    String re = getDeptDetail(feiShuDepartment.getId(), TenanAccessToken);
//                    if ((!StringUtils.isEmpty(re)) && (!StringUtils.equals("null", re))) {//飞书中部门存在
//                        //添加到部门更新表中
//                        updateList.add(feiShuDepartment);
//                        departmentInfos.add(departmentInfo);
//                        //反写结果，更新自建记录表信息
//                        updateDeptCompareInfos.add(doChangeDeptInfo(departmentInfo, PaDeptId));
//                    } else {//飞书中部门不存在
//                        //默认不创建部门群
//                        //添加到新增部门列表
//                        log.info("添加到deptaddlist："+JSON.toJSONString(feiShuDepartment));
//                        addList.add(feiShuDepartment);
//                        //反写结果，向自建记录表添加信息
//                        addDeptCompareInfos.add(doChangeDeptInfo(departmentInfo, PaDeptId));
//                    }
                } else {//记录表中有记录
                    //比较部门信息是否一致
                    DeptCompareInfo deptCompareInfo=deptCompareInfos.get(0);
                    boolean flag = deptComparePreperties(deptCompareInfo, departmentInfo, PaDeptId);
                    //若相同则结束，若不同
                    if (!flag) {
                            updateList.add(feiShuDepartment);
                            departmentInfos.add(departmentInfo);
                            //反写结果，更新自建记录表信息
                            updateDeptCompareInfos.add(doChangeDeptInfo(departmentInfo, PaDeptId));
                    }
                }
            }
        }
        log.info("部门新增，修改信息获取结束");
        //新增部门
        //list切片
        log.info("开始新增部门");
        List<List<FeiShuDepartment>> deptAddLists= Lists.partition(addList,999);
        for(List<FeiShuDepartment> smallAddList:deptAddLists) {
            JSONArray addArray = JSONArray.parseArray(JSON.toJSONString(smallAddList));
            if ((!addArray.isEmpty()) && (!(addArray.size() < 1))) {
                String re=feiShuApi.batchAddDepartment(TenanAccessToken, addArray);
                log.info("批量新增部门返回结果："+re);
            }
        }
        //反写新增部门结果
        for(DeptCompareInfo deptCompareInfo:addDeptCompareInfos){
            deptCompareInfoRepository.save(deptCompareInfo);
        }
        log.info("结束新增部门");
    }
    /**
     * 通过现有信息创建飞书部门类
     * @param departmentInfo
     * @param PaDeptId
     *
     */
    public FeiShuDepartment getFeishuDepartment(DepartmentInfo departmentInfo, String PaDeptId,String TenanAccessToken) throws Exception{

        FeiShuDepartment feiShuDepartment = new FeiShuDepartment();
        if (departmentInfo != null) {
            feiShuDepartment.setId(departmentInfo.getSET_ID() + departmentInfo.getDEPT_ID());//存id
            feiShuDepartment.setName( departmentInfo.getDESCR());//存name
            if ((!StringUtils.isEmpty(PaDeptId)) && (!StringUtils.equals("null", PaDeptId))) {
                feiShuDepartment.setParent_id(departmentInfo.getSET_ID() + PaDeptId);
            } else {
                feiShuDepartment.setParent_id(0 + "");
            }
            //String id = feiShuDepartment.getId();//部门id
            String managerId = departmentInfo.getMANAGER_ID();//存magagerid
            if ((!StringUtils.isEmpty(managerId)) && (!StringUtils.equals("null", managerId))) {//若managerId不为空
                String UserRe = feiShuApi.getFeiShuUserDetail(TenanAccessToken, managerId);//在飞书中查managerid是否存在
                if ((!StringUtils.isEmpty(UserRe)) && (!StringUtils.equals("null", UserRe))) {//若部门负责人存在
                    feiShuDepartment.setLeader_user_id((departmentInfo.getMANAGER_ID()));//添加部门负责人
                }
            }
        }
        return feiShuDepartment;
    }

    /**
     * 比较自建记录表信息和原部门表信息
     * @param deptCompareInfo
     * @param departmentInfo
     * @param paDeptId
     * @throws Exception
     */
    public boolean deptComparePreperties(DeptCompareInfo deptCompareInfo, DepartmentInfo departmentInfo, String paDeptId) throws Exception{
        boolean flag=true;
        if(!StringUtils.equals(deptCompareInfo.getSET_ID(),departmentInfo.getSET_ID())){
            flag=false;
        }
        if(!StringUtils.equals(deptCompareInfo.getDEPT_ID(),departmentInfo.getDEPT_ID())){
            flag=false;
        }
        if(!StringUtils.equals(deptCompareInfo.getEFF_STATUS(),departmentInfo.getEFF_STATUS())){
            flag=false;
        }
        if(!StringUtils.equals(deptCompareInfo.getDESCR(),departmentInfo.getDESCR())){
            flag=false;
        }
        if(!StringUtils.equals(deptCompareInfo.getMANAGER_ID(),departmentInfo.getMANAGER_ID())){
            flag=false;
        }
        if(!StringUtils.equals(deptCompareInfo.getPARENT_NODE_NAME(),paDeptId)){
            flag=false;
        }
        return flag;
    }

    /**
     * 添加或更新自建记录表信息
     * @param departmentInfo
     * @param paDeptId
     */
    public DeptCompareInfo doChangeDeptInfo(DepartmentInfo departmentInfo, String paDeptId) {
        if(departmentInfo==null) return null;
        DeptCompareInfo deptCompareInfo=new DeptCompareInfo();
        deptCompareInfo.setFD_ID(departmentInfo.getFD_ID());
        deptCompareInfo.setSET_ID(departmentInfo.getSET_ID());
        deptCompareInfo.setDEPT_ID(departmentInfo.getDEPT_ID());
        deptCompareInfo.setEFF_STATUS(departmentInfo.getEFF_STATUS());
        deptCompareInfo.setDESCR(departmentInfo.getDESCR());
        deptCompareInfo.setMANAGER_ID(departmentInfo.getMANAGER_ID());
        deptCompareInfo.setPARENT_NODE_NAME(paDeptId);
        //deptCompareInfoRepository.save(deptCompareInfo);
        return deptCompareInfo;
    }




    /**
     * 按id删除部门
     * @param id
     * @param TenanAccessToken
     * @throws Exception
     */
    public void deleDepartmentById(String id,String TenanAccessToken) throws Exception{
        feiShuApi.deleteAllChildDepartment(TenanAccessToken,id);
    }

    /**
     * 更新部门
     * @param feiShuDepartment
     * @param TenanAccessToken
     * @return
     * @throws Exception
     */
    @Override
    public String updateFeishuDepartment(FeiShuDepartment feiShuDepartment,String TenanAccessToken) throws Exception {
        String re=feiShuApi.updateDepartment(TenanAccessToken,feiShuDepartment);
        return re;
    }

    /**
     * 获得子部门id列表
     * @param department_id
     * @param offset
     * @param page_size
     * @param fetch_child
     * @param TenanAccessToken
     * @return
     * @throws Exception
     */
    @Override
    public String getChildDeptList(String department_id, int offset, int page_size, boolean fetch_child,String TenanAccessToken) throws Exception {
        String re=feiShuApi.getDepartmentList(TenanAccessToken,department_id,offset,page_size,fetch_child);
        return re;
    }

    /**
     * 批量获得部门详情
     * @param departmentIds
     * @param TenanAccessToken
     * @return
     * @throws Exception
     */
    @Override
    public String batchGetDeptDetail(String[] departmentIds,String TenanAccessToken) throws Exception {
        String re=feiShuApi.batchGetDepartmentDetail(TenanAccessToken,departmentIds);
        return re;
    }

    /**
     * 获得总数层级
     * @return
     * @throws Exception
     */
    @Override
    public int getTreeLevel() throws Exception {
        int re=departmentTreeInfoRepository.GetMaxLevel();
        return re;
    }

    /**
     * 获得单个部门详情
     * @param departmentId
     * @param TenanAccessToken
     * @return
     * @throws Exception
     */
    @Override
    public String getDeptDetail(String departmentId,String TenanAccessToken) throws Exception {
        String re=feiShuApi.getDepartmentDetail(TenanAccessToken,departmentId);
        return re;
    }


}

















