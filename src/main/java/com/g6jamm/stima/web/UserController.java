package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mysql.PermissionRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.ResourceTypeRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.UserRepositoryImpl;
import com.g6jamm.stima.domain.exception.LoginException;
import com.g6jamm.stima.domain.exception.ResourceTypeNotFoundException;
import com.g6jamm.stima.domain.exception.SignUpException;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.model.User;
import com.g6jamm.stima.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {

  private final UserService USER_SERVICE =
      new UserService(
          new UserRepositoryImpl(),
          new ResourceTypeRepositoryImpl(),
          new PermissionRepositoryImpl());

  /**
   * @auther Mohamad
   */
  @GetMapping("/")
  public String index(WebRequest webRequest) {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "index";
    }
    return "redirect:/projects";
  }

  /**
   * @auther Mohamad
   */
  @GetMapping("/create-user")
  public String signUp(WebRequest webRequest) {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }
    return "createUser";
  }

  /**
   * @auther Mohamad
   */
  @GetMapping("/logout")
  public String logout(WebRequest webRequest) {
    webRequest.removeAttribute("user", WebRequest.SCOPE_SESSION);
    return "redirect:/";
  }

  /**
   * @auther Mohamad
   */
  @PostMapping("/login")
  public String logIn(WebRequest webRequest, Model model) throws SystemException {
    try {
      String email = webRequest.getParameter("email");
      String password = webRequest.getParameter("password");

      User user = USER_SERVICE.login(email, password);

      webRequest.setAttribute("user", user, WebRequest.SCOPE_SESSION);

      return "redirect:/projects";

    } catch (LoginException e) {
      model.addAttribute("loginFail", e.getMessage());
      return "index";
    }
  }

  /**
   * @auther Mohamad, Mathias
   */
  @PostMapping("/create-user")
  public String createUser(WebRequest webRequest, Model model)
      throws SystemException, ResourceTypeNotFoundException {
    String firstNameParam = webRequest.getParameter("user-firstname");
    String lastNameParam = webRequest.getParameter("user-lastname");
    String emailParam = webRequest.getParameter("user-email");
    String resourceTypeParam = webRequest.getParameter("user-resource-type");
    String permissionIdParam = webRequest.getParameter("user-permission");
    String password1Param = webRequest.getParameter("user-password1");
    String password2Param = webRequest.getParameter("user-password2");

    try {
      if (validatePassword(password1Param, password2Param)) {
        User user =
            USER_SERVICE.createUser(
                firstNameParam,
                lastNameParam,
                emailParam,
                password1Param,
                resourceTypeParam,
                permissionIdParam);

        webRequest.setAttribute("user", user, WebRequest.SCOPE_SESSION);

        return "redirect:/projects"; // TODO: redirect to a success page?
      }
    } catch (SignUpException e) {
      model.addAttribute("signupFail", e.getMessage()); // TODO
    }
    return null;
  }

  /**
   * @auther Mohamad
   */
  private boolean validatePassword(String password1, String password2) {
    return password1.equals(password2);
  }

  @ExceptionHandler(Exception.class)
  public String error(Model model, Exception e) {
    model.addAttribute("message", e.getMessage());
    e.printStackTrace();
    return "error";
  }
}
