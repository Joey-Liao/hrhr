package com.wiwj.appinterface.Dao;

import com.wiwj.appinterface.Model.DepartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentInfoRepository extends JpaRepository<DepartmentInfo,Long> {

    @Query("from DepartmentInfo where DEPT_ID =?1 and SET_ID=?2 and EFF_STATUS= 'A'")
    List<DepartmentInfo> findByDeptIdAndSetId(String DEPT_ID,String SET_ID);

    @Query("from DepartmentInfo where DEPT_ID =?1")
    List<DepartmentInfo> findByDeptId(String DEPT_ID);

    @Query("from DepartmentInfo where DEPT_ID =?1 and SET_ID=?2 ")
    List<DepartmentInfo> findByDeptIdAndSetIdWithoutStatus(String DEPT_ID,String SET_ID);
}
