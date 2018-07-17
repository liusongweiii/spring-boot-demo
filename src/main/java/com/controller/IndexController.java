package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/7/10 0010.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public Object index(){
        return "index";
    }

}
