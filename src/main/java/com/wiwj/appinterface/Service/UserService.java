package com.wiwj.appinterface.Service;

import com.wiwj.appinterface.Result.SimpleFeishuUser;
import java.util.List;

public interface UserService {
    String batchAddUser(String FD_HR_DATE,List<SimpleFeishuUser> addList,List<SimpleFeishuUser> updateList,List<SimpleFeishuUser> deleteList,String TenanAccessToken)throws Exception;
    List<SimpleFeishuUser> getAllUser(String FD_HR_DATE)throws Exception;
    SimpleFeishuUser getRealUser(SimpleFeishuUser feishuUser);
    List<SimpleFeishuUser> getLeader(String FD_HR_DATE,String setId,String deptId,String C_QUARTERS_ID) throws Exception;
    List<SimpleFeishuUser> getUserById(String FD_HR_DATE,String setId,String id)throws Exception;
}
