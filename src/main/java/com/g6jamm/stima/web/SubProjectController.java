package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.stub.ResourceTypeRepositoryStub;
import com.g6jamm.stima.data.repository.stub.SubProjectRepositoryStub;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.service.SubProjectService;
import com.g6jamm.stima.data.repository.stub.TaskRepositoryStub;
import com.g6jamm.stima.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class SubProjectController {
  TaskService taskService = new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());

  private final SubProjectService SUBPROJECT_SERVICE =
      new SubProjectService(new SubProjectRepositoryStub());
  private SubProject subP =
      SUBPROJECT_SERVICE.createSubProject(
          "TEST", LocalDate.of(2021, 5, 6), LocalDate.of(2021, 6, 5));
  // TODO skal fjernes

  @GetMapping("/subproject") // TODO /{subProjectId}
  public String subProjectPage(Model model) {
    model.addAttribute("subProject", subP);
    return "subProject";
  }

  @GetMapping("/subproject/createnew/") // TODO /{subProjectId}
  public String createSubProject(Model model) {
    model.addAttribute("true", "isCreate");
    return "subProject";
  }

  @PostMapping("/subproject") // TODO /{subProjectId}
  public String createSubProject(WebRequest webRequest, Model model) {
    String subProjectName = webRequest.getParameter("name");
    String startDate = webRequest.getParameter("startDate");
    String endDate = webRequest.getParameter("endDate");
    // TODO check if valid date
    // TODO check if date are inside project start and end
    subP =
        SUBPROJECT_SERVICE.createSubProject(
            subProjectName, LocalDate.parse(startDate), LocalDate.parse(endDate));

    model.addAttribute("subProject", subP);

    return "subProject";
  }

  /**
   * Post method for creating new tasks.
   *
   * <p>Takes all input from the form and passes them to taskService which create a Task object.
   *
   * <p>This object is then added to the parameter "model". @Author Andreas
   *
   * @param webRequest
   * @param model
   * @return redirects user to Task page.
   */
  @PostMapping("/create-task") // TODO Change to /projects/{project_id}/create-task
  public String createTask(WebRequest webRequest, Model model) {
    // TODO get project from project_id

    String name = webRequest.getParameter("task_name");
    double hours =
        webRequest.getParameter("task_hours").matches("[0-9]+")
            ? Double.valueOf(webRequest.getParameter("task_hours"))
            : 0.0;

    String resourceType = webRequest.getParameter("task_resourcetype");
    String startDate =
        !webRequest.getParameter("task_startdate").isEmpty()
            ? webRequest.getParameter("task_startdate")
            : "1990-01-01"; // change to project start date
    String endDate =
        !webRequest.getParameter("task_enddate").isEmpty()
            ? webRequest.getParameter("task_enddate")
            : "1990-01-01"; // change to project end date

    // TODO Add to Task to project

    model.addAttribute(
        "Task", taskService.createtask(name, hours, resourceType, startDate, endDate));
    model.addAttribute("ResourceTypeList", taskService.getResourceTypes());

    return "Task"; // TODO redirect to /projects/{project_id}
  }

  /**
   * Initial Get method for displaying a task. @Author Andreas
   *
   * @param webRequest
   * @param model
   * @return
   */
  @GetMapping("/task")
  public String task(WebRequest webRequest, Model model) {
    if (model.getAttribute("Task") == null) {
      model.addAttribute(
          "Task", taskService.createtask("Placeholder", 1.0, "test", "1990-01-01", "1991-01-01"));
      model.addAttribute("ResourceTypeList", taskService.getResourceTypes());
    }
    return "Task";
  }
}
