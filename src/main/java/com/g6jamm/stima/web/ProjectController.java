package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mysql.*;
import com.g6jamm.stima.domain.exception.SystemException;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.*;
import com.g6jamm.stima.domain.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProjectController {
  private final ProjectService PROJECT_SERVICE = new ProjectService(new ProjectRepositoryImpl());
  private final TaskService TASK_SERVICE =
      new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryImpl());
  private final SubProjectService SUBPROJECT_SERVICE =
      new SubProjectService(new SubProjectRepositoryImpl());
  private final UserService USER_SERVICE =
      new UserService(
          new UserRepositoryImpl(),
          new ResourceTypeRepositoryImpl(),
          new PermissionRepositoryImpl());
  private final ProjectColorService COLOR_SERVICE = new ProjectColorService(new ProjectColorImpl());

  /**
   * View all projects.
   *
   * @param model Model
   * @return String
   * @auther Mathias
   */
  @GetMapping("/projects")
  public String projects(WebRequest webRequest, Model model) throws SystemException {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    List<ProjectComposite> projects = PROJECT_SERVICE.getProjects(user);

    model.addAttribute("projects", projects);
    model.addAttribute("projectColors", COLOR_SERVICE.getProjectColors());

    return "projects";
  }

  /**
   * View a specific project.
   *
   * @param model Model
   * @return String
   * @auther Mathias
   */
  @GetMapping("/projects/{projectId}")
  public String projectId(WebRequest webRequest, Model model, @PathVariable int projectId)
      throws SystemException {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

    List<Project> subProjects = project.getSubProjects();
    model.addAttribute("projects", subProjects);

    List<Task> tasks = project.getTasks();
    model.addAttribute("tasks", tasks);
    model.addAttribute("parentproject", project);
    model.addAttribute("projectColors", COLOR_SERVICE.getProjectColors());
    model.addAttribute("classActiveSettings", "active");
    model.addAttribute("resourceTypes", TASK_SERVICE.getResourceTypes());

    return "project";
  }

  @PostMapping("/projects/{projectId}/create-subproject")
  public String createSubProject(WebRequest webRequest, @PathVariable int projectId)
      throws SystemException {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    String subProjectNameParam = webRequest.getParameter("create-subproject-name");
    String startDateParam = webRequest.getParameter("create-subproject-start-date");
    String endDateParam = webRequest.getParameter("create-subproject-end-date");
    String projectColorParam = webRequest.getParameter("create-subproject-color");

    ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

    ProjectLeaf subProject =
        SUBPROJECT_SERVICE.createSubProject(
            subProjectNameParam,
            LocalDate.parse(startDateParam),
            LocalDate.parse(endDateParam),
            projectColorParam,
            projectId);

    project.getSubProjects().add(subProject);

    return "redirect:/projects/" + projectId;
  }

  @PostMapping("/projects/create-project")
  public String createProject(WebRequest webRequest, Model model) throws SystemException {
    if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) == null) {
      return "redirect:/";
    }

    User user = (User) webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);

    String projectNameParam = webRequest.getParameter("create-project-name");
    String startDateParam = webRequest.getParameter("create-project-start-date");
    String endDateParam = webRequest.getParameter("create-project-end-date");
    String projectColorParam = webRequest.getParameter("create-project-color");

    ProjectComposite project =
        PROJECT_SERVICE.createProject(
            projectNameParam,
            LocalDate.parse(startDateParam),
            LocalDate.parse(endDateParam),
            projectColorParam,
            user);

    return "redirect:/projects";
  }

  @PostMapping("/projects/{projectId}/{subprojectId}/edit-project")
  public String editSubProject(
      WebRequest webRequest, @PathVariable int projectId, @PathVariable int subprojectId)
      throws SystemException {

    String projectNameParam = webRequest.getParameter("edit-project-name");
    String startDateParam = webRequest.getParameter("edit-project-start-date");
    String endDateParam = webRequest.getParameter("edit-project-end-date");
    String projectColorParam = webRequest.getParameter("edit-project-color");

    SUBPROJECT_SERVICE.editProject(
        subprojectId,
        projectNameParam,
        LocalDate.parse(startDateParam),
        LocalDate.parse(endDateParam),
        projectColorParam);

    return "redirect:/projects/" + projectId;
  }

  @PostMapping("/projects/{projectId}/edit-project")
  public String editProject(WebRequest webRequest, @PathVariable int projectId)
      throws SystemException {

    String projectNameParam = webRequest.getParameter("edit-project-name");
    String startDateParam = webRequest.getParameter("edit-project-start-date");
    String endDateParam = webRequest.getParameter("edit-project-end-date");
    String projectColorParam = webRequest.getParameter("edit-project-color");

    PROJECT_SERVICE.editProject(
        projectId,
        projectNameParam,
        LocalDate.parse(startDateParam),
        LocalDate.parse(endDateParam),
        projectColorParam);

    return "redirect:/projects";
  }

  @PostMapping("/projects/{projectId}/delete-project")
  public String deleteProject(@PathVariable int projectId) throws SystemException {
    PROJECT_SERVICE.deleteProject(projectId);

    return "redirect:/projects";
  }

  @PostMapping("/projects/{projectId}/{subprojectId}/delete-project")
  public String deleteSubProject(@PathVariable int projectId, @PathVariable int subprojectId)
      throws SystemException {
    SUBPROJECT_SERVICE.deleteProject(subprojectId);

    return "redirect:/projects/" + projectId;
  }

  @PostMapping("/projects/{projectId}/edit-task")
  public String editProjectTask(WebRequest webRequest, @PathVariable int projectId)
      throws TaskCreationException, SystemException {

    String nameParam = webRequest.getParameter("edit-task-name");
    String hoursParam = webRequest.getParameter("edit-task-hours");
    String resourceTypeParam = webRequest.getParameter("edit-task-resource-type");
    String startDateParam = webRequest.getParameter("edit-task-start-date");
    String endDateParam = webRequest.getParameter("edit-task-end-date");
    String taskIdParam = webRequest.getParameter("task-id");

    TASK_SERVICE.editTask(
        nameParam,
        Double.parseDouble(hoursParam),
        resourceTypeParam,
        startDateParam,
        endDateParam,
        Integer.parseInt(taskIdParam));

    return "redirect:/projects/" + projectId;
  }

  @PostMapping("/projects/{projectId}/{subprojectId}/edit-task")
  public String editSubProjectTask(
      WebRequest webRequest, @PathVariable int projectId, @PathVariable int subprojectId)
      throws TaskCreationException, SystemException {

    String nameParam = webRequest.getParameter("edit-task-name");
    String hoursParam = webRequest.getParameter("edit-task-hours");
    String resourceTypeParam = webRequest.getParameter("edit-task-resource-type");
    String startDateParam = webRequest.getParameter("edit-task-start-date");
    String endDateParam = webRequest.getParameter("edit-task-end-date");
    String taskIdParam = webRequest.getParameter("task-id");

    // TODO check if valid date
    // TODO check if date are inside project start and end

    TASK_SERVICE.editTask(
        nameParam,
        Double.parseDouble(hoursParam),
        resourceTypeParam,
        startDateParam,
        endDateParam,
        Integer.parseInt(taskIdParam));

    return "redirect:/projects/" + projectId + "/" + subprojectId;
  }

  @ExceptionHandler(Exception.class)
  public String error(Model model, Exception e) {
    model.addAttribute("message", e.getMessage());
    e.printStackTrace();
    return "error";
  }
}
