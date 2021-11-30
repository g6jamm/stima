package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mock.ProjectRepositoryStub;
import com.g6jamm.stima.data.repository.mock.SubProjectRepositoryStub;
import com.g6jamm.stima.data.repository.mock.TaskRepositoryStub;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.ProjectService;
import com.g6jamm.stima.domain.service.SubProjectService;
import com.g6jamm.stima.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProjectController {

  /**
   * View all projects.
   *
   * @param webRequest WebRequest
   * @param model Model
   * @return String
   * @auther Mathias
   */
  @GetMapping("/projects")
  public String projects(WebRequest webRequest, Model model, ModelAndView model2) {
    ProjectService projectService = new ProjectService(new ProjectRepositoryStub());

    List<Project> projects = projectService.getProjects();

    model.addAttribute("projects", projects);

    return "projects";
  }

  /**
   * View a specific project.
   *
   * @param model Model
   * @return String
   * @auther Mathias
   */
  @GetMapping("/project")
  public String projectId(Model model) {

    SubProjectService subProjectService = new SubProjectService(new SubProjectRepositoryStub());
    List<SubProject> subProjects = subProjectService.getSubprojects();
    model.addAttribute("subprojects", subProjects);

    TaskService taskService = new TaskService(new TaskRepositoryStub());
    List<Task> tasks = taskService.getTasks();
    model.addAttribute("tasks", tasks);

    ProjectService projectService = new ProjectService(new ProjectRepositoryStub());
    Project project = projectService.getProjects().get(0); // TODO: ID of project

    model.addAttribute("project", project);

    return "project";
  }

  /**
   * Creates a project and navigates the user to the project page.
   *
   * @param webRequest WebRequest
   * @param model Model
   * @return String
   * @auther Mathias
   */
  @PostMapping("/create-project")
  public String createProject(WebRequest webRequest, Model model) {
    int projectId = 1; // TODO: make dynamic
    return "redirect:/project/" + projectId;
  }

  /**
   * Navigates the user to edit project page.
   *
   * @param webRequest WebRequest
   * @param id int
   * @return String
   * @auther Mathias
   */
  @PostMapping("/edit-project{id}")
  public String editProject(WebRequest webRequest, @PathVariable int id) {
    return "redirect:/project/edit-project"; // TODO: redirect?
  }

  /**
   * Deletes the project by id and navigate the user to the project page.
   *
   * @param webRequest WebRequest
   * @param id int
   * @return String
   * @auther Mathias
   */
  @PostMapping("/delete-project/{id}")
  public String deleteProject(WebRequest webRequest, @PathVariable int id) {
    return "redirect:/project"; // TODO: redirect?
  }
}
