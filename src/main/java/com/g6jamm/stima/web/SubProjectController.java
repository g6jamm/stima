package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mysql.PermissionRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.ProjectRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.ResourceTypeRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.TaskRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.UserRepositoryImpl;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectComposite;
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
   * Get method for sub project page, shows all task for the sup project
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

    ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

    Project subProject = null; // TODO: move
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
      model.addAttribute("parentProject", project);

      return "subProject";
    }

    return "redirect:/projects/" + projectId;
  }

  @PostMapping("/projects/{projectId}/create-task")
  public String createProjectTask(WebRequest webRequest, Model model, @PathVariable int projectId)
      throws SystemException {

    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

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
        TASK_SERVICE.createtask(
            taskNameParam, hours, resourceTypeParam, taskStartDate, taskEndDate, project.getId());

    project.addTask(task);
  }

  @PostMapping("/projects/{projectId}/{subProjectId}/create-task")
  public String createSubProjectTask(
      WebRequest webRequest,
      Model model,
      @PathVariable int projectId,
      @PathVariable int subProjectId)
      throws SystemException, TaskCreationException {

    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

    Project subProject = null; // TODO: move
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
