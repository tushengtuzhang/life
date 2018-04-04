package com.zts.jpa.controller;

import com.zts.jpa.common.controller.BaseController;
import com.zts.jpa.common.entity.Status;
import com.zts.jpa.common.vo.R;
import com.zts.jpa.dao.UserDao;
import com.zts.jpa.entity.Sex;
import com.zts.jpa.entity.User;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController<UserDao,User>{

    @RequestMapping(value = "/login")
    @ResponseBody
    public R login(String userName,String password){

        User user = t.login(userName, password);
        if(user!=null){
            return R.ok();
        }

        return R.error("用户名或者密码不对");
    }

    @Override
    public R save(User user){
        log.info("重写BaseController中的方法测试");
        return super.save(user);
    }

    @RequestMapping(value = "/pager")
    @ResponseBody
    public R pager(@RequestBody(required = false) User user,@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "ASC")Sort.Direction direction, @RequestParam(defaultValue = "id") String orderBy){

        log.info("带条件的分页查询，后面把实现抽取到BaseController");

        PageRequest pageRequest=PageRequest.of(pageNumber-1,size,new Sort(direction,orderBy));
        Specification<User> specification= (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            Path<String> userName = root.get("userName");
            Path<Sex> sex=root.get("sex");
            Predicate predicate = criteriaBuilder.like(userName, "%" + user.getUserName() + "%");
            Predicate predicate1 = criteriaBuilder.equal(sex, user.getSex());
            Predicate predicate2 = criteriaBuilder.equal(root.get("status"), Status.ACTIVE);

            return criteriaBuilder.and(predicate,predicate1,predicate2);
        };

        Map<String,Object> map=new HashMap<>();

        map.put("list",t.findAll(specification,pageRequest));

        return R.ok(map);

    }



}
