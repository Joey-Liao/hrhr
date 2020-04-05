package com.wiwj.appinterface.Dao;

import com.wiwj.appinterface.Result.DeptCompareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DeptCompareInfoRepository extends JpaRepository<DeptCompareInfo,Long> {
    @Query("from DeptCompareInfo where DEPT_ID =?1 and SET_ID=?2 and EFF_STATUS= 'A'")
    List<DeptCompareInfo> findByDeptIdAndSetId(String DEPT_ID, String SET_ID);

    @Query(value = "delete from fei_dept where DEPT_ID =?1 and SET_ID=?2",nativeQuery = true)
    void deleteByDEPT_IDAndSET_ID(String DEPT_ID, String SET_ID);

}
