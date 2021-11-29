package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mock.UserRepositoryStub;
import com.g6jamm.stima.domain.model.User;
import com.g6jamm.stima.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {

  UserService userService = new UserService(new UserRepositoryStub());

  @GetMapping("/")
  public String goToHomepage() {
    return "index";
  }

  @GetMapping("/sigup")
  public String signUp(){
    return "signup";
  }

  @PostMapping("/login")
  public String logIn(WebRequest webRequest){

    String email = webRequest.getParameter("email");
    String password = webRequest.getParameter("password");

    User user = userService.login(email, password);
    webRequest.setAttribute("user", user.getId(), WebRequest.SCOPE_SESSION);

    return "redirect:/index";
  }

  @PostMapping("/signup")
  public String createUser(WebRequest webRequest){
    String firstName = webRequest.getParameter("firstname");
    String lastName = webRequest.getParameter("lastname");
    String email = webRequest.getParameter("email");
    String password1 = webRequest.getParameter("password1");
    String password2 = webRequest.getParameter("password2");

    if (validatePassword(password1, password2)){
      User user = userService.createUser(firstName, lastName, email, password1);
      webRequest.setAttribute("user", user.getId(), WebRequest.SCOPE_SESSION);
      return "redirect:/index";
    }
    return "signup";
  }

  @GetMapping("logout")
  public String logout(WebRequest webRequest){
    webRequest.removeAttribute("user", WebRequest.SCOPE_SESSION);
    return "redirect:/index";
  }

  private boolean validatePassword(String password1, String password2){
    if (password1.equals(password2)){
      return true;
    }
    return false;
  }



}
