package com.uestc.appinterface.Dao;

import com.uestc.appinterface.Model.UserPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPersonalInfoRepository extends JpaRepository<UserPersonalInfo,Long> {

}
