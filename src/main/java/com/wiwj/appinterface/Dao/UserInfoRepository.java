package com.wiwj.appinterface.Dao;

import com.wiwj.appinterface.Model.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    @Query(value="  select p.NAME_FORMAT as name ,p.C_EMAIL as email,p.C_MOBILE as mobile,e.DEPT_ID as department_id,p.SEX as gender,e.SUPERVISOR_ID as leader_employee_id,e.EMPL_ID as employee_id ,e.SETID_DEPT as setId ,e.HR_STATUS as HR_STATUS,e.C_QUARTERS_ID as C_QUARTERS_ID ,po.DESCR as DESCR from hr_emp as e left join hr_personal as p on e.EMPL_ID=p.EMPL_ID left join hr_post as po on e.SETID_DEPT=po.SET_ID and e.C_QUARTERS_ID=po.C_QUARTERS_ID  where e.EMPL_RCD='0' and e.MODIFY_TIME>?1 and p.FD_HR_DATE>?1 and e.HR_STATUS='A'" ,nativeQuery = true)
    List<Object[]> findAllUser(String FD_HR_DATE);

    @Query(value=" select p.NAME_FORMAT as name ,p.C_EMAIL as email,p.C_MOBILE as mobile,e.DEPT_ID as department_id,p.SEX as gender,e.SUPERVISOR_ID as leader_employee_id,e.EMPL_ID as employee_id ,e.SETID_DEPT as setId ,e.HR_STATUS as HR_STATUS,e.C_QUARTERS_ID as C_QUARTERS_ID ,po.DESCR as DESCR from hr_emp as e left join hr_personal as p on e.EMPL_ID=p.EMPL_ID left join hr_post as po on e.SETID_DEPT=po.SET_ID and e.C_QUARTERS_ID=po.C_QUARTERS_ID where e.EMPL_RCD='0' and e.MODIFY_TIME>?1 and p.FD_HR_DATE>?1 and e.LAST_HIRE_DT in(select max(LAST_HIRE_DT) from hr_emp where C_QUARTERS_ID=?4 and SETID_DEPT=?2 and DEPT_ID=?3) and C_QUARTERS_ID=?4 and SETID_DEPT=?2 and DEPT_ID=?3" ,nativeQuery = true)
    List<Object[]> findLeader(String FD_HR_DATE,String setId,String deptId,String C_QUARTERS_ID);

    @Query(value="  select p.NAME_FORMAT as name ,p.C_EMAIL as email,p.C_MOBILE as mobile,e.DEPT_ID as department_id,p.SEX as gender,e.SUPERVISOR_ID as leader_employee_id,e.EMPL_ID as employee_id ,e.SETID_DEPT as setId ,e.HR_STATUS as HR_STATUS,e.C_QUARTERS_ID as C_QUARTERS_ID ,po.DESCR as DESCR from hr_emp as e left join hr_personal as p on e.EMPL_ID=p.EMPL_ID left join hr_post as po on e.SETID_DEPT=po.SET_ID and e.C_QUARTERS_ID=po.C_QUARTERS_ID  where e.EMPL_RCD='0' and e.MODIFY_TIME>?1 and e.SETID_DEPT=?2 and e.EMPL_ID=?3" ,nativeQuery = true)
    List<Object[]> findBysetIdAndId(String FD_HR_DATE,String setId,String Id);
}
