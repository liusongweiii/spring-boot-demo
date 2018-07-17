package com.common;


import com.service.FilePathService;
import com.service.SysLogService;
import com.service.UrlService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Service {


    @Autowired
    public UrlService urlService;
    @Autowired
    public FilePathService filePathService;
    @Autowired
    public UserService userService;
    @Autowired
    public SysLogService sysLogService;


    public static Object queryById(String id,Class clazz){
        if(UserService.class.equals(clazz)){
            return Service.getInstance().userService.queryById(Integer.valueOf(id));
        }
        return null;
    }

    private static Service service;

    @PostConstruct
    public void init(){
        service = this;
    }

    private static class LazyHolder{
        private static final Service INSTANCE = service;
    }

    private Service(){}

    public static final Service getInstance(){
        return LazyHolder.INSTANCE;
    }


}
