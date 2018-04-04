package com.zts.jpa.common.controller;

import com.zts.jpa.common.entity.BaseEntity;
import com.zts.jpa.common.entity.Status;
import com.zts.jpa.common.vo.R;
import com.zts.jpa.common.repository.BaseRepository;
import com.zts.jpa.entity.User;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log
public class BaseController<T extends BaseRepository,E extends BaseEntity> {

    @Resource
    protected T t;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public R list(){
        Map<String,Object> map=new HashMap<>();

        map.put("list",t.findAll());
        log.info(map.get("list").toString());

        log.info(getEntityName());

        return R.ok(map);
    }

    @RequestMapping(value = "/page")
    @ResponseBody
    public R page(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "15") int size, @RequestParam(defaultValue = "ASC")Sort.Direction direction, @RequestParam(defaultValue = "id") String orderBy){

        PageRequest pageRequest=PageRequest.of(pageNumber-1,size,new Sort(direction,orderBy));

        Map<String,Object> map=new HashMap<>();
        map.put("list",t.findAll(pageRequest));

        return R.ok(map);

    }





    @RequestMapping(value = "/save")
    @ResponseBody
    @Transactional
    public R save(User user){

        user.setCreateTime(new Date());

        Map<String,Object> map=new HashMap<>();
        map.put("entity",t.saveAndFlush(user));
        return R.ok(map);
    }

    @RequestMapping(value = "update")
    @ResponseBody
    @Transactional
    public R update(User user){

        user.setUpdateTime(new Date());

        Map<String,Object> map=new HashMap<>();
        map.put("entity",t.saveAndFlush(user));

        return R.ok(map);
    }

    @RequestMapping(value="delete")
    @ResponseBody
    @Transactional
    @SuppressWarnings("unchecked")
    public R delete(Integer id){

        Optional<E> optional=t.findById(id);
        if(optional.isPresent()){
            E e=optional.get();
            e.setDeleteTime(new Date());
            e.setStatus(Status.DELETE);
            t.saveAndFlush(e);

            return R.ok();
        }

        //t.deleteById(id);
        return R.error();
    }

    @SuppressWarnings("unchecked")
    private String getEntityName(){
        //获取第二个参数
        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return entityClass.getSimpleName();
    }

}
