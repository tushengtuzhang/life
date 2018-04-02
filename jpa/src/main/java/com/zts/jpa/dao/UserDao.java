package com.zts.jpa.dao;

import com.zts.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    @Query("select u from User u where userName=?1 and password=?2 order by id ")
    User login(String userName, String password);

}
