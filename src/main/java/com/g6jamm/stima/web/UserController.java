package com.g6jamm.stima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @GetMapping("/")
  public String goToHomepage() {
    return "index";
  }
}