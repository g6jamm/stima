package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mock.ProjectRepositoryStub;
import com.g6jamm.stima.data.repository.mock.SubProjectRepositoryStub;
import com.g6jamm.stima.data.repository.mock.TaskRepositoryStub;
import com.g6jamm.stima.domain.model.HeadProject;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.ProjectService;
import com.g6jamm.stima.domain.service.SubProjectService;
import com.g6jamm.stima.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
  @GetMapping("/head-projects")
  public String projects(WebRequest webRequest, Model model, ModelAndView model2) {
    ProjectService projectService = new ProjectService(new ProjectRepositoryStub());

    List<HeadProject> headProjects = projectService.getProjects();

    model.addAttribute("headProjects", headProjects);

    return "headProjects";
  }

  /**
   * View a specific project.
   *
   * @param model Model
   * @return String
   * @auther Mathias
   */
  @GetMapping("/head-projects/{headProjectId}")
  public String projectId(Model model, @PathVariable int headProjectId) {

    SubProjectService subProjectService = new SubProjectService(new SubProjectRepositoryStub());
    List<SubProject> subProjects = subProjectService.getSubprojects();
    model.addAttribute("subprojects", subProjects);

    TaskService taskService = new TaskService(new TaskRepositoryStub());
    List<Task> tasks = taskService.getTasks();
    model.addAttribute("tasks", tasks);

    ProjectService projectService = new ProjectService(new ProjectRepositoryStub());
    HeadProject headProject = projectService.getProjects().get(0); // TODO: ID of headProject

    model.addAttribute("headProject", headProject);

    return "headProject";
  }

  @GetMapping("/head-projects/{headProjectId}/{subProjectId}")
  public String subProject(
      Model model, @PathVariable int headProjectId, @PathVariable int subProjectId) {
    SubProjectService SUBPROJECT_SERVICE = new SubProjectService(new SubProjectRepositoryStub());
    SubProject subP =
        SUBPROJECT_SERVICE.createSubProject(
            "TEST", LocalDate.of(2021, 5, 6), LocalDate.of(2021, 6, 5));

    model.addAttribute("subProject", subP);

    return "subProject";
  }

  @PostMapping("/head-projects/{headProjectId}/create-new") // TODO /{subProjectId}
  public String createSubProject(
      WebRequest webRequest, Model model, @PathVariable int headProjectId) {
    String subProjectName = webRequest.getParameter("name");
    String startDate = webRequest.getParameter("start-date");
    String endDate = webRequest.getParameter("end-date");
    // TODO check if valid date
    // TODO check if date are inside project start and end
    SubProjectService subProjectService = new SubProjectService(new SubProjectRepositoryStub());
    SubProject subP =
        subProjectService.createSubProject(
            subProjectName, LocalDate.parse(startDate), LocalDate.parse(endDate));

    model.addAttribute("subProject", subP);

    return "headProject";
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
   * @param headProjectId int
   * @return String
   * @auther Mathias
   */
  @PostMapping("/edit-project{headProjectId}")
  public String editProject(WebRequest webRequest, @PathVariable int headProjectId) {
    return "redirect:/project/edit-project"; // TODO: redirect?
  }

  /**
   * Deletes the project by id and navigate the user to the project page.
   *
   * @param webRequest WebRequest
   * @param headProjectId int
   * @return String
   * @auther Mathias
   */
  @PostMapping("/delete-project/{headProjectId}")
  public String deleteProject(WebRequest webRequest, @PathVariable int headProjectId) {
    return "redirect:/project"; // TODO: redirect?
  }
}
