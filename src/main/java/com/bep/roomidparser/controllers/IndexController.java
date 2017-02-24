package com.bep.roomidparser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * <p>Used to generate the starting point of the Room-ID-parser.</p>
 *
 * @author sido
 */
@Controller
public class IndexController {

  /**
   * <p>Starting point of the Room-ID-parser.</p>
   *
   * @return index.html
   */
  @RequestMapping("/")
  public String index() {
  return "index";
}

}
