package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mysql.TaskRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.*;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.Project;
import com.g6jamm.stima.domain.model.ProjectInterface;
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

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class SubProjectController {
  private final SubProjectService SUBPROJECT_SERVICE =
      new SubProjectService(new SubProjectRepositoryStub());
  TaskService taskService =
      new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryStub());

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
    List<Task> tasks = subProject.getTasks();
    // TODO need change remove hardcoded tasks when possible

    //    for (Task t : tasks) {
    //      SUBPROJECT_SERVICE.addTaskToSubProject(subProject.getId(), t);
    //    } //TODO FIX!!

    model.addAttribute("tasks", tasks);
    model.addAttribute("subProject", subProject);
    model.addAttribute("resourceTypes", taskService.getResourceTypes());

    ProjectService projectService = new ProjectService(new ProjectRepositoryStub());

    model.addAttribute("parentProject", projectService.getProjectById(projectId));

    return "subProject";
  }

  @PostMapping("/projects/{projectId}/create-task")
  public String createProjectTask(WebRequest webRequest, Model model, @PathVariable int projectId) {

    ProjectService projectService = new ProjectService(new ProjectRepositoryStub());
    Project project = projectService.getProjectById(projectId);

    try {
      createTask(webRequest, project);
    } catch (TaskCreationException e) {
      model.addAttribute("error", e.getMessage());
    }

    return "redirect:/projects/" + projectId;
  }

  /**
   * Post method for creating new tasks. Takes all input from the form and passes them to
   * taskService which create a Task object. The newly created task is then added to the projects
   * list of tasks.
   *
   * @param webRequest
   * @author Andreas
   */
  private void createTask(WebRequest webRequest, ProjectInterface project)
      throws TaskCreationException {

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
            : project.getStartDate().format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));

    String taskEndDate =
        !taskEndDateParam.isEmpty()
            ? taskEndDateParam
            : project
                .getStartDate()
                .format(DateTimeFormatter.ofPattern("YYYY-MM-DD")); // TODO More validation

    Task newTask =
        taskService.createtask(
            taskNameParam, hours, resourceTypeParam, taskStartDate, taskEndDate, project.getId());

    project.getTasks().add(newTask);
  }

  @PostMapping("/projects/{projectId}/{subProjectId}/create-task")
  public String createSubProjectTask(
      WebRequest webRequest,
      Model model,
      @PathVariable int projectId,
      @PathVariable int subProjectId) {

    SubProject subProject = SUBPROJECT_SERVICE.getSubProject(projectId);
    try {
      createTask(webRequest, subProject);
    } catch (TaskCreationException e) {
      model.addAttribute(
          "error",
          e.getMessage()); // TODO Handle exceptions?????? if we redirect we dont see the error.
    }

    return "redirect:/projects/" + projectId + "/" + subProjectId;
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
                "Placeholder", 1.0, "Senior Developer", "1990-01-01", "1991-01-01", 1));
        model.addAttribute("ResourceTypes", taskService.getResourceTypes());
      } catch (TaskCreationException e) {
        model.addAttribute("error", e.getMessage());
      }
    }
    return "Task";
  }
}
