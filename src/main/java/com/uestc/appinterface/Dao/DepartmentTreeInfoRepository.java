package com.uestc.appinterface.Dao;

import com.uestc.appinterface.Model.DepartmentTreeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentTreeInfoRepository extends JpaRepository<DepartmentTreeInfo,Long> {


    @Query(value="select count(*) ,TREE_LEVEL_NUM from hr_dept_tree group by TREE_LEVEL_NUM",nativeQuery = true)
    int[] GetMaxLevel();


    @Query(value = "select * from hr_dept_tree where TREE_LEVEL_NUM =?1 and FD_HR_DATE > ?2 group by SET_ID,TREE_NODE and ",nativeQuery = true)
    List<DepartmentTreeInfo> findByTreeLevel(String level,String fdDate);
}