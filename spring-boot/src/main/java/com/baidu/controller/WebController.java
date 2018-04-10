package com.baidu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mypc on 2018/4/3 0003.
 */
@Controller
@RequestMapping("web")
public class WebController {

    @RequestMapping(value = "jspIndex",method = RequestMethod.GET)
    public String jspIndex(){
        System.out.println("=========================");
        return "Index2";
    }
}
