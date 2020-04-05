package com.wiwj.appinterface.ServiceImpl;


import com.alibaba.fastjson.JSON;
import com.wiwj.appinterface.Dao.UserCompareInfoRepository;
import com.wiwj.appinterface.Dao.UserInfoRepository;
import com.wiwj.appinterface.Result.SimpleFeishuUser;
import com.wiwj.appinterface.Result.UserCompareInfo;
import com.wiwj.appinterface.Service.UserService;
import com.wiwj.appinterface.ToolUtil.LzyApp;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserCompareInfoRepository userCompareInfoRepository;
    @Autowired
    LzyApp lzyApp;
    @Autowired
    FeiShuApi feiShuApi;
    Logger log = LoggerFactory.getLogger(this.getClass());

    public String getTenanAccessToken()throws Exception{
        String appId=lzyApp.getAppId();
        String appSecret=lzyApp.getAppSecret();
        String TenanAccessToken=feiShuApi.GetTenantAccessToken(appId,appSecret);
        return TenanAccessToken;
    }


    /**
     * 获取所有人员信息表
     * @param FD_HR_DATE
     * @param addList
     * @param updateList
     * @param deleteList
     * @return
     * @throws Exception
     */
    @Override
    public String batchAddUser(String FD_HR_DATE,List<SimpleFeishuUser> addList,List<SimpleFeishuUser> updateList,List<SimpleFeishuUser> deleteList) throws Exception {
        String TenanAccessToken=getTenanAccessToken();
        if(StringUtils.isEmpty(FD_HR_DATE)){//若该参数为空，则查询所有时间
            FD_HR_DATE="1000-11-15";
        }
        //获取deleteList
        log.info("开始获取人员删除列表");
        List<UserCompareInfo> userDeleteListInMyText=userCompareInfoRepository.findAll();
        for(UserCompareInfo userCompareInfo:userDeleteListInMyText){
            if((!StringUtils.isEmpty(userCompareInfo.getSET_ID()))&&(!StringUtils.isEmpty(userCompareInfo.getEMPL_ID()))) {
                List<SimpleFeishuUser> simpleFeishuUsers = getUserById(FD_HR_DATE, userCompareInfo.getSET_ID(), userCompareInfo.getEMPL_ID());
                //OA表中不存在或者失效则加入删除表
                if ((simpleFeishuUsers.size() == 0) || (!(StringUtils.equals(simpleFeishuUsers.get(0).getHR_STATUS(), "A")))) {
                    SimpleFeishuUser deleteOne = new SimpleFeishuUser();
                    String myUserId = userCompareInfo.getUser_id();
                    if (!StringUtils.isEmpty(myUserId)) {
                        deleteOne.setMyUserId(myUserId);
                        deleteOne.setEMPL_ID(userCompareInfo.getEMPL_ID());
                        log.info("deleteList中添加人员：" + JSON.toJSONString(deleteOne));
                        deleteList.add(deleteOne);
                    }
                }
            }
        }
        log.info("结束获取人员删除列表");
        List<SimpleFeishuUser> feiShuUserList = getAllUser(FD_HR_DATE);//获得OA表中所有用户信息
        log.info("开始获取人员新增，更新列表");
        for(SimpleFeishuUser simpleFeishuUser:feiShuUserList){
            //若电话不存在则不做任何操作
            if(StringUtils.isEmpty(simpleFeishuUser.getMobile())||(StringUtils.equals("null",simpleFeishuUser.getMobile()))){
                log.info("没有手机号");
                continue;
            }
            SimpleFeishuUser realUser=getRealUser(simpleFeishuUser);
            //根据id查自建记录表
            List<UserCompareInfo> userCompareInfos=userCompareInfoRepository.findByEMPL_ID(simpleFeishuUser.getEMPL_ID());
            if(userCompareInfos.size()==0){//自建记录表没有该人员信息
                log.info("addList中添加人员："+JSON.toJSONString(realUser));
                addList.add(realUser);
            }else{//自建记录表有该人员信息
                UserCompareInfo userCompareInfo=userCompareInfos.get(0);
                boolean flag=userComparePreperties(realUser, userCompareInfo);
                if(!flag){//若相同则结束，若不同
                        //添加到部门更新表中
                        realUser.setMyUserId(userCompareInfo.getUser_id());
                    log.info("updateList中添加人员："+JSON.toJSONString(realUser));
                        updateList.add(realUser);
                }
            }
        }
        log.info("结束获取人员新增，更新列表");
        return "";
    }
    /**
     * 反写结果到自建表
     * @param realUser
     */
    public void doChangeUserInfoAdd(SimpleFeishuUser realUser,String feishuId) {
        if(realUser==null) return;
        UserCompareInfo userCompareInfo=new UserCompareInfo();
        userCompareInfo.setEMPL_ID(realUser.getEMPL_ID());
        userCompareInfo.setNAME_FORMAT(realUser.getName());
        String IntSex=realUser.getGender();
        String sex="";
        if(StringUtils.equals("1",IntSex)){
            sex="M";
        }else{
            sex="F";
        }
        userCompareInfo.setSEX(sex);
        userCompareInfo.setC_MOBILE(realUser.getMobile());
        userCompareInfo.setC_EMAIL(realUser.getEmail());
        userCompareInfo.setSET_ID(realUser.getSetId());
        userCompareInfo.setDEPT_ID(realUser.getDepartment_ids()[0]);
        userCompareInfo.setSUPERVISOR_ID(realUser.getLeader_employee_id());
        userCompareInfo.setHR_STATUS(realUser.getHR_STATUS());
        userCompareInfo.setC_QUARTERS_ID(realUser.getC_QUARTERS_ID());
        userCompareInfo.setUser_id(feishuId);
        userCompareInfoRepository.save(userCompareInfo);
    }
    /**
     * 反写结果到自建表
     * @param realUser
     */
    public void doChangeUserInfo(SimpleFeishuUser realUser) {
        if(realUser==null) return;
        UserCompareInfo userCompareInfo=new UserCompareInfo();
        userCompareInfo.setEMPL_ID(realUser.getEMPL_ID());
        userCompareInfo.setNAME_FORMAT(realUser.getName());
        String IntSex=realUser.getGender();
        String sex="";
        if(StringUtils.equals("1",IntSex)){
            sex="M";
        }else{
            sex="F";
        }
        userCompareInfo.setSEX(sex);
        userCompareInfo.setC_MOBILE(realUser.getMobile());
        userCompareInfo.setC_EMAIL(realUser.getEmail());
        userCompareInfo.setSET_ID(realUser.getSetId());
        userCompareInfo.setDEPT_ID(realUser.getDepartment_ids()[0]);
        userCompareInfo.setSUPERVISOR_ID(realUser.getLeader_employee_id());
        userCompareInfo.setHR_STATUS(realUser.getHR_STATUS());
        userCompareInfo.setC_QUARTERS_ID(realUser.getC_QUARTERS_ID());
        userCompareInfo.setUser_id(realUser.getMyUserId());
        userCompareInfoRepository.save(userCompareInfo);
    }

    /**
     * 比较OA中人员信息和自建记录表中人员信息
     * @param realUser
     * @param userCompareInfo
     * @return
     */
    private boolean userComparePreperties(SimpleFeishuUser realUser, UserCompareInfo userCompareInfo) {
        boolean flag=true;
        if(!StringUtils.equals(userCompareInfo.getEMPL_ID(),realUser.getEMPL_ID())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getNAME_FORMAT(),realUser.getName())){
            flag=false;
        }
        String sex=userCompareInfo.getSEX();
        int intSex=0;
        if(StringUtils.equals("M",sex)){
            intSex=1;
        }else{
            intSex=2;
        }
        if(!StringUtils.equals(""+intSex,realUser.getGender())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getC_MOBILE(),realUser.getMobile())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getC_EMAIL(),realUser.getEmail())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getSET_ID(),realUser.getSetId())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getDEPT_ID(),realUser.getDepartment_ids()[0])){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getSUPERVISOR_ID(),realUser.getLeader_employee_id())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getHR_STATUS(),realUser.getHR_STATUS())){
            flag=false;
        }
        if(!StringUtils.equals(userCompareInfo.getC_QUARTERS_ID(),realUser.getC_QUARTERS_ID())){
            flag=false;
        }
        return flag;
    }

    @Override
    public List<SimpleFeishuUser> getAllUser(String FD_HR_DATE) throws Exception{
        List<Object[]> select=userInfoRepository.findAllUser(FD_HR_DATE);
        List<SimpleFeishuUser> feiShuUserList=castEntity(select,SimpleFeishuUser.class);
        //log.info("用户获取开始");
        for(SimpleFeishuUser simpleFeishuUser:feiShuUserList){
            //log.info("user:"+JSON.toJSONString(simpleFeishuUser));
        }
        return feiShuUserList;
    }
    @Override
    public List<SimpleFeishuUser> getLeader(String FD_HR_DATE,String setId,String deptId,String C_QUARTERS_ID) throws Exception{
        List<Object[]> select=userInfoRepository.findLeader(FD_HR_DATE,setId,deptId,C_QUARTERS_ID);
        List<SimpleFeishuUser> feiShuUserList=castEntity(select,SimpleFeishuUser.class);
        log.info("feishuUserList："+JSON.toJSONString(feiShuUserList));
        return feiShuUserList;
    }

    public List<SimpleFeishuUser> getUserById(String FD_HR_DATE,String setId,String id) throws Exception{
        List<Object[]> select=userInfoRepository.findBysetIdAndId(FD_HR_DATE,setId,id);
        List<SimpleFeishuUser> feiShuUserList=castEntity(select,SimpleFeishuUser.class);
       // log.info("用中间用户表查询oa表的结果长度："+JSON.toJSONString(feiShuUserList.size()));
        //log.info("用中间用户表查询oa表："+JSON.toJSONString(feiShuUserList.get(0)));
        return feiShuUserList;
    }

    @Override
    public SimpleFeishuUser getRealUser(SimpleFeishuUser feishuUser) {
        SimpleFeishuUser realuser=new SimpleFeishuUser();
        if(!StringUtils.isEmpty(feishuUser.getLeader_employee_id())&& (!StringUtils.equals("null",feishuUser.getLeader_employee_id()))){
            realuser.setLeader_employee_id(feishuUser.getLeader_employee_id());
        }
        if(!StringUtils.isEmpty(feishuUser.getMobile())&& (!StringUtils.equals("null",feishuUser.getMobile()))){
            realuser.setMobile(feishuUser.getMobile());
        }
        if(!StringUtils.isEmpty(feishuUser.getEmail())&& (!StringUtils.equals("null",feishuUser.getEmail()))){
            realuser.setEmail(feishuUser.getEmail());
        }
        if(!StringUtils.isEmpty(feishuUser.getName())&& (!StringUtils.equals("null",feishuUser.getName()))){
            realuser.setName(feishuUser.getName());
        }else{
            realuser.setName("无名");
        }
        String[] de=feishuUser.getDepartment_ids();
        String realDeptId=feishuUser.getSetId()+de[0];
        de[0]=realDeptId;//真的departmentId
        realuser.setDepartment_ids(de);
        realuser.setSetId(feishuUser.getSetId());
        realuser.setEMPL_ID(feishuUser.getEMPL_ID());
        realuser.setGender(feishuUser.getGender());
        realuser.setC_QUARTERS_ID(feishuUser.getC_QUARTERS_ID());
        realuser.setHR_STATUS(feishuUser.getHR_STATUS());
        realuser.setPost(feishuUser.getPost());
        return realuser;
    }

    /**
     * 转换实体类
     * @param list
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        if(CollectionUtils.isEmpty(list)){
            return returnList;
        }
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];
        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            if(co[i]!=null){
                c2[i] = co[i].getClass();
            }else {
                c2[i]=String.class;
            }
        }
        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }


}
