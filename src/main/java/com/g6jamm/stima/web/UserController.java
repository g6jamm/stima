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
   * Get Mapping for returning the index page. If there is a user in the session we redirect to
   * /projects.
   *
   * @author Mohamad
   */
  @GetMapping("/")
  public String index(WebRequest webRequest) {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "index";
    }
    return "redirect:/projects";
  }

  /**
   * get mapping for showing the create user page. if there is no user in the session we redirect to
   * indexpage.
   *
   * @author Mohamad
   */
  @GetMapping("/create-user")
  public String signUp(WebRequest webRequest) {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }
    return "createUser";
  }

  /**
   * Mapping for logout. This method removes the user from the session amd redirected to the index
   * page.
   *
   * @author Mohamad
   */
  @GetMapping("/logout")
  public String logout(WebRequest webRequest) {
    webRequest.removeAttribute("user", WebRequest.SCOPE_SESSION);
    return "redirect:/";
  }

  /**
   * Mapping for sign in, this checks if the user exists and email/password matches what is in the
   * datalayer. Returns the user to /projects if successful else an error is displayed on the index
   * page.
   *
   * @author Mohamad
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
   * Mapping for creating new users. This is only available when logged in. This method takes the
   * input from the form and creates a user if the passwords are the same. Returns an error if not
   * successful.
   *
   * @author Mohamad, Mathias
   */
  @PostMapping("/create-user")
  public String createUser(WebRequest webRequest, Model model)
      throws SystemException, ResourceTypeNotFoundException {

    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    String firstNameParam = webRequest.getParameter("user-firstname");
    String lastNameParam = webRequest.getParameter("user-lastname");
    String emailParam = webRequest.getParameter("user-email");
    String resourceTypeParam = webRequest.getParameter("user-resource-type");
    String permissionIdParam = webRequest.getParameter("user-permission");
    String password1Param = webRequest.getParameter("user-password1");
    String password2Param = webRequest.getParameter("user-password2");

    try {
      if (validatePassword(password1Param, password2Param)) {
        USER_SERVICE.createUser(
            firstNameParam,
            lastNameParam,
            emailParam,
            password1Param,
            resourceTypeParam,
            permissionIdParam);

        return "redirect:/projects";
      }
    } catch (SignUpException e) {
      model.addAttribute("signupFail", e.getMessage());
    }
    return null;
  }

  /**
   * Method for checking if passwords are eqaul. Returns true or false
   *
   * @author Mohamad
   */
  private boolean validatePassword(String password1, String password2) {
    return password1.equals(password2);
  }

  /**
   * Method for handling exceptions. This displays an error page with the message received from the
   * exception
   * @author Mohamad
   */
  @ExceptionHandler(Exception.class)
  public String error(Model model, Exception e) {
    model.addAttribute("message", e.getMessage());
    e.printStackTrace();
    return "error";
  }
}
