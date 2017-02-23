package com.bep.roomidparser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sido
 */
//@Controller
//@RequestMapping("/")
public class IndexController {

//    @RequestMapping("/")
//    @ResponseBody
    public String home() {
        return "Welcome to the Room-id-parser";
    }

}
