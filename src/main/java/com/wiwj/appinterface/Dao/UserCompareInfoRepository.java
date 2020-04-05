package com.wiwj.appinterface.Dao;

import com.wiwj.appinterface.Result.UserCompareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserCompareInfoRepository extends JpaRepository<UserCompareInfo,Long> {

    @Query("from UserCompareInfo where EMPL_ID=?1")
    List<UserCompareInfo> findByEMPL_ID(String EMPL_ID);

    @Query(value = "delete from fei_user where EMPL_ID =?1",nativeQuery = true)
    void deleteByEMPL_ID(String EMPL_ID);
}
