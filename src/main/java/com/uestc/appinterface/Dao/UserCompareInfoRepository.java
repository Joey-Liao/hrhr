package com.uestc.appinterface.Dao;

import com.uestc.appinterface.Result.UserCompareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserCompareInfoRepository extends JpaRepository<UserCompareInfo,Long> {

    @Query("from UserCompareInfo where EMPL_ID=?1")
    UserCompareInfo findByEMPL_ID(String EMPL_ID);

    @Query(value = "delete from fei_user where EMPL_ID =?1",nativeQuery = true)
    void deleteByEMPL_ID(String EMPL_ID);
}
