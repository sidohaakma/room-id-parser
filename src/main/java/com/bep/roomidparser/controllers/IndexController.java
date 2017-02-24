package com.bep.roomidparser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * <p>Used to generate the </p>
 *
 * @author sido
 */
@Controller
public class IndexController {

      @RequestMapping("/")
      public String index() {
  return "index";
}

}
