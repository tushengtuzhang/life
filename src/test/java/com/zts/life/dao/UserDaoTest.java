package com.zts.life.dao;

import com.zts.life.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    private Log log=LogFactory.getLog(UserDaoTest.class);
    private Logger logger=LoggerFactory.getLogger(UserDaoTest.class);

    @Resource
    UserDao userDao;

    @Test
    public void saveTest(){
        User user=new User(1,"zts","password");

        User user1=new User();
        user1.setUserName("zhangts1");
        user1.setPassword("password1");
        userDao.save(user1);
    }

    @Test
    public void listTest(){
        int pageNumber=1,size=15;
        PageRequest pageRequest=PageRequest.of(pageNumber-1,size);
        //Sort sort=new Sort(Sort.Direction.DESC,"userName","password");
        //PageRequest pageRequest1=PageRequest.of(pageNumber-1,size, sort);
        //PageRequest pageRequest2=PageRequest.of(pageNumber-1,size, Sort.Direction.DESC,"userName");
        Page<User> page = userDao.findAll(pageRequest);
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        System.out.println(page.getSize());
        System.out.println(page.getContent());

        log.info(page.getTotalPages());
        logger.info("totalElements",page.getTotalElements());
        logger.info(String.valueOf(page.getTotalElements()));


        User loginUser=userDao.login("zhangts", "password");
        System.out.println(loginUser);
    }
}
