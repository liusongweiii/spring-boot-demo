package com.controller;

import com.aop.MyLog;
import com.bean.User;
import com.common.Service;
import com.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/12 0012.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/add")
    @MyLog(remark = "添加用户",serviceClazz = UserService.class, idKey = "id",
    attrKey = {"name","age","sex"}, attrName = {"name","age","sex"})
    public Object add(Integer id,String name,String age,String sex){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        Service.getInstance().userService.add(user);
        return user;
    }


//    @RequestMapping("/update")
//    @MyLog(remark = "修改用户",beanClazz = User.class,serviceClazz = UserService.class)
//    public Object update(Integer id,String name,String age,String sex){
//        User user = Service.getInstance().userService.queryById(id);
//        if(user!=null){
//            user.setName(name);
//            user.setAge(age);
//            user.setSex(sex);
//            Service.getInstance().userService.add(user);
//        }
//        return user;
//    }


}



























