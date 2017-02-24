package com.bep.roomidparser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sido
 */
@Controller
public class IndexController {

      @RequestMapping("/")
      public String index() {
  return "index";
}


      @RequestMapping("/status")
      public String status() {
    return "status";
  }

}
