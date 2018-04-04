package com.zts.jpa.dao;

import com.zts.jpa.common.repository.BaseRepository;
import com.zts.jpa.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends BaseRepository<User,Integer> {

    @Query("select u from User u where userName=?1 and password=?2 order by id ")
    User login(String userName, String password);


}
