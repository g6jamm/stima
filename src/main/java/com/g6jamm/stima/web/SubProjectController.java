package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mysql.*;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.Headproject;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.model.User;
import com.g6jamm.stima.domain.service.ProjectService;
import com.g6jamm.stima.domain.service.TaskService;
import com.g6jamm.stima.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class SubProjectController {

  private final TaskService TASK_SERVICE =
      new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryImpl());
  private final ProjectService PROJECT_SERVICE = new ProjectService(new ProjectRepositoryImpl());
  private final UserService USER_SERVICE =
      new UserService(
          new UserRepositoryImpl(),
          new ResourceTypeRepositoryImpl(),
          new PermissionRepositoryImpl());

  /**
   * Get method for displaying subproject page, shows all task for a subproject Redirects the user
   * to login if not logged in. Finds the subproject based on projectid and subProjectId givin in
   * the parameter. First by getting the head project, then by looping through the headprojects
   * subprojects. Last it adds it to the model
   *
   * @param model
   * @param projectId
   * @param subProjectId
   * @return
   * @author Jackie
   */
  @GetMapping("/projects/{projectId}/{subProjectId}")
  public String subProjectPage(
      WebRequest webRequest,
      Model model,
      @PathVariable int projectId,
      @PathVariable int subProjectId)
      throws SystemException {

    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    model.addAttribute("classActiveSettings", "active");

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    Headproject project = PROJECT_SERVICE.getProjectById(user, projectId);

    Project subProject = null;
    for (Project sp : project.getSubProjects()) {
      if (subProjectId == sp.getId()) {
        subProject = sp;
      }
    }

    if (subProject != null) {
      List<Task> tasks = subProject.getTasks();

      model.addAttribute("tasks", tasks);
      model.addAttribute("subProject", subProject);
      model.addAttribute("resourceTypes", TASK_SERVICE.getResourceTypes());
      model.addAttribute("permissions", USER_SERVICE.getPermissions());
      model.addAttribute("parentProject", project);

      return "subProject";
    }

    return "redirect:/projects/" + projectId;
  }

  /**
   * Post method for adding tasks to Headprojects. Redirects the user to login if not logged in.
   * Finds the headproject based on projectid givin in the parameter. calls createTask() with the
   * webrequest and project.
   *
   * @author Andreas
   * @param webRequest
   * @param projectId
   * @return
   * @throws SystemException thrown on error when creating tasks.
   * @throws TaskCreationException thrown on error when deciding on which resourcetype to use.
   */
  @PostMapping("/projects/{projectId}/create-task")
  public String createProjectTask(WebRequest webRequest, Model model, @PathVariable int projectId)
      throws SystemException {

    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    Headproject project = PROJECT_SERVICE.getProjectById(user, projectId);

    try {
      createTask(webRequest, project);
    } catch (TaskCreationException e) {
      model.addAttribute("error", e.getMessage());
    }

    return "redirect:/projects/" + projectId;
  }

  /**
   * Method for creating new tasks. Takes all input from the form and passes them to taskService
   * which create a Task object. The newly created task is then added to the projects list of tasks.
   *
   * @param webRequest
   * @author Andreas
   */
  private void createTask(WebRequest webRequest, Project project)
      throws TaskCreationException, SystemException {

    String taskNameParam = webRequest.getParameter("task-name");
    String taskHoursParam = webRequest.getParameter("task-hours");
    String resourceTypeParam = webRequest.getParameter("task-resource-type");
    String taskStartDateParam = webRequest.getParameter("task-start-date");
    String taskEndDateParam = webRequest.getParameter("task-end-date");

    boolean isNumberTaskHours = taskHoursParam.matches("[0-9]+");

    double hours = isNumberTaskHours ? Double.parseDouble(taskHoursParam) : 0.0;

    String taskStartDate =
        !taskStartDateParam.isEmpty()
            ? taskStartDateParam
            : project.getStartDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

    String taskEndDate =
        !taskEndDateParam.isEmpty()
            ? taskEndDateParam
            : project
                .getStartDate()
                .format(DateTimeFormatter.ofPattern("MM-dd-yyyy")); // TODO: More validation

    Task task =
        TASK_SERVICE.createTask(
            taskNameParam, hours, resourceTypeParam, taskStartDate, taskEndDate, project.getId());

    project.addTask(task);
  }

  /**
   * Post method for adding tasks to subprojects. Redirects the user to login if not logged in
   *
   * <p>Finds the subproject based on project id and subProjectId given in the parameter. First by
   * getting the head project, then by looping through the headprojects subprojects.
   *
   * <p>calls createTask() with the webrequest and subproject.
   * @author Andreas, Jackie
   *
   * @param webRequest
   * @param projectId
   * @param subProjectId
   * @return
   * @throws SystemException thrown on error when creating tasks.
   * @throws TaskCreationException thrown on error when deciding on which resourcetype to use.
   */
  @PostMapping("/projects/{projectId}/{subProjectId}/create-task")
  public String createSubProjectTask(
      WebRequest webRequest, @PathVariable int projectId, @PathVariable int subProjectId)
      throws SystemException, TaskCreationException {

    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    Headproject project = PROJECT_SERVICE.getProjectById(user, projectId);

    Project subProject = null;
    for (Project projectComponent : project.getSubProjects()) {
      if (projectComponent.getId() == subProjectId) {
        subProject = projectComponent;
      }
    }

    if (subProject != null) {
      createTask(webRequest, subProject);
    }

    return "redirect:/projects/" + projectId + "/" + subProjectId;
  }

  @ExceptionHandler(Exception.class)
  public String error(Model model, Exception e) {
    model.addAttribute("message", e.getMessage());
    e.printStackTrace();
    return "error";
  }
}
