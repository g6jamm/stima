package com.g6jamm.stima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubProjectController {

  @GetMapping("/subproject") // TODO rename, skal nok hedde noget andet
  public String subProjectPage() {
    return "subProject";
  }
}
