package com.wiwj.appinterface.Dao;

import com.wiwj.appinterface.Model.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    @Query(value="select p.NAME_FORMAT ,p.C_EMAIL,p.C_MOBILE,e.DEPT_ID,p.SEX ,e.SUPERVISOR_ID,e.EMPL_ID,e.SETID_DEPT,e.HR_STATUS,e.C_QUARTERS_ID,po.DESCR from hr_emp e left join hr_personal p on e.EMPL_ID=p.EMPL_ID left join hr_post po on e.SETID_DEPT=po.SET_ID and e.C_QUARTERS_ID=po.C_QUARTERS_ID  where e.EMPL_RCD='0' and e.MODIFY_TIME between to_date(?1,'yyyy-mm-dd') and to_date('9999-12-31','yyyy-mm-dd') and p.FD_HR_DATE between to_date(?1,'yyyy-mm-dd') and to_date('9999-12-31','yyyy-mm-dd') and e.HR_STATUS='A'" ,nativeQuery = true)
    List<Object[]> findAllUser(String FD_HR_DATE);

    @Query(value="select p.NAME_FORMAT ,p.C_EMAIL,p.C_MOBILE,e.DEPT_ID,p.SEX ,e.SUPERVISOR_ID,e.EMPL_ID,e.SETID_DEPT,e.HR_STATUS,e.C_QUARTERS_ID,po.DESCR from hr_emp e left join hr_personal p on e.EMPL_ID=p.EMPL_ID left join hr_post po on e.SETID_DEPT=po.SET_ID and e.C_QUARTERS_ID=po.C_QUARTERS_ID  where e.EMPL_RCD='0' and e.MODIFY_TIME between to_date(?1,'yyyy-mm-dd') and to_date('9999-12-31','yyyy-mm-dd') and p.FD_HR_DATE between to_date(?1,'yyyy-mm-dd') and to_date('9999-12-31','yyyy-mm-dd') and e.LAST_HIRE_DT in(select max(LAST_HIRE_DT) from hr_emp where C_QUARTERS_ID=?4 and SETID_DEPT=?2 and DEPT_ID=?3) and e.C_QUARTERS_ID=?4 and e.SETID_DEPT=?2 and e.DEPT_ID=?3" ,nativeQuery = true)
    List<Object[]> findLeader(String FD_HR_DATE,String setId,String deptId,String C_QUARTERS_ID);

    @Query(value="select p.NAME_FORMAT ,p.C_EMAIL,p.C_MOBILE,e.DEPT_ID,p.SEX ,e.SUPERVISOR_ID,e.EMPL_ID,e.SETID_DEPT,e.HR_STATUS,e.C_QUARTERS_ID,po.DESCR from hr_emp e left join hr_personal p on e.EMPL_ID=p.EMPL_ID left join hr_post po on e.SETID_DEPT=po.SET_ID and e.C_QUARTERS_ID=po.C_QUARTERS_ID  where e.EMPL_RCD='0' and e.MODIFY_TIME between to_date(?1,'yyyy-mm-dd') and to_date('9999-12-31','yyyy-mm-dd') and p.FD_HR_DATE between to_date(?1,'yyyy-mm-dd') and to_date('9999-12-31','yyyy-mm-dd') and e.SETID_DEPT=?2 and e.EMPL_ID=?3" ,nativeQuery = true)
    List<Object[]> findBysetIdAndId(String FD_HR_DATE,String setId,String Id);
}
