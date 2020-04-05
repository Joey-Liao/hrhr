package com.wiwj.appinterface.Dao;

import com.wiwj.appinterface.Model.DepartmentTreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentTreeInfoRepository extends JpaRepository<DepartmentTreeInfo,Long> {

    @Query(value="select count(DISTINCT TREE_LEVEL_NUM) from hr_dept_tree",nativeQuery = true)
    int GetMaxLevel();

    @Query(value = "select * from hr_dept_tree where TREE_LEVEL_NUM =?1 and FD_HR_DATE in(select max(FD_HR_DATE) from HR_DEPT_TREE group by SET_ID,TREE_NODE )ORDER BY SET_ID",nativeQuery = true)
    List<DepartmentTreeInfo> findByTreeLevel(String level);
}