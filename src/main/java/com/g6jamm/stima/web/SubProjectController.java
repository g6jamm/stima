package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.stub.ResourceTypeRepositoryStub;
import com.g6jamm.stima.data.repository.stub.SubProjectRepositoryStub;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.SubProject;
import com.g6jamm.stima.domain.model.Task;
import com.g6jamm.stima.domain.service.SubProjectService;
import com.g6jamm.stima.data.repository.stub.TaskRepositoryStub;
import com.g6jamm.stima.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class SubProjectController {
  TaskService taskService =
      new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());

  private final SubProjectService SUBPROJECT_SERVICE =
      new SubProjectService(new SubProjectRepositoryStub());

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
      Model model, @PathVariable int projectId, @PathVariable int subProjectId) {
    SubProject subProject = SUBPROJECT_SERVICE.getSubProject(subProjectId);
    TaskService taskService =
        new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());
    List<Task> tasks = taskService.getTasks();
    // TODO need change remove hardcoded tasks when possible

    //    for (Task t : tasks) {
    //      SUBPROJECT_SERVICE.addTaskToSubProject(subProject.getId(), t);
    //    } //TODO FIX!!

    model.addAttribute("tasks", tasks);
    model.addAttribute("project", subProject);
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
  @PostMapping(
      "/create-task") // TODO Change to /projects/{project_id}/create-task (Maybe make modal box
  // (see projects, create subproject))
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
    try {
      model.addAttribute(
          "Task", taskService.createtask(name, hours, resourceType, startDate, endDate));
      model.addAttribute("ResourceTypeList", taskService.getResourceTypes());
    } catch (TaskCreationException e) {
      model.addAttribute("error", e.getMessage());
    }
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
      try {
        model.addAttribute(
            "Task",
            taskService.createtask(
                "Placeholder", 1.0, "Senior Developer", "1990-01-01", "1991-01-01"));
        model.addAttribute("ResourceTypeList", taskService.getResourceTypes());
      } catch (TaskCreationException e) {
        model.addAttribute("error", e.getMessage());
      }
    }
    return "Task";
  }
}
