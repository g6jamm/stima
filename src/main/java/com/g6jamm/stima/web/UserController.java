package com.g6jamm.stima.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
  @GetMapping("/")
  public String goToHomepage(Model model) {
    model.addAttribute("test", "something");
    return "index";
  }
}
