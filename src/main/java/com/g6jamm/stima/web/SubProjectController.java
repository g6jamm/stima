package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.stub.*;
import com.g6jamm.stima.domain.exception.TaskCreationException;
import com.g6jamm.stima.domain.model.ProjectComposite;
import com.g6jamm.stima.domain.model.Project;
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
      new TaskService(new TaskRepositoryStub(), new ResourceTypeRepositoryStub());
  private final ProjectService PROJECT_SERVICE = new ProjectService(new ProjectRepositoryStub());

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
    ProjectComposite project = PROJECT_SERVICE.getProjectById(projectId);

    Project subProject = null; // todo move??
    for (Project sp : project.getSubProjects()) {
      if (subProjectId == sp.getId()) {
        subProject = sp;
      }
    }
    if (subProject != null) {
      List<Task> tasks = subProject.getTasks();
      // TODO need change remove hardcoded tasks when possible

      model.addAttribute("tasks", tasks);
      model.addAttribute("subProject", subProject);
      model.addAttribute("resourceTypes", taskService.getResourceTypes());

      model.addAttribute("parentProject", PROJECT_SERVICE.getProjectById(projectId));

      return "subProject";
    }
    return "redirect:/projects/" + projectId;
  }

  @PostMapping("/projects/{projectId}/create-task")
  public String createProjectTask(WebRequest webRequest, Model model, @PathVariable int projectId) {
    ProjectComposite project = PROJECT_SERVICE.getProjectById(projectId);

    try {
      createTask(webRequest, project);
    } catch (TaskCreationException e) {
      model.addAttribute("error", e.getMessage());
    }

    return "redirect:/projects/" + projectId;
  }

  /**
   * Method for creating new tasks. Takes all input from the form and passes them to
   * taskService which create a Task object. The newly created task is then added to the projects
   * list of tasks.
   *
   * @param webRequest
   * @author Andreas
   */
  private void createTask(WebRequest webRequest, Project project)
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

    project.addTask(newTask);
  }

  @PostMapping("/projects/{projectId}/{subProjectId}/create-task")
  public String createSubProjectTask(
      WebRequest webRequest,
      Model model,
      @PathVariable int projectId,
      @PathVariable int subProjectId) {

    ProjectComposite project = PROJECT_SERVICE.getProjectById(projectId);

    Project subProject = null;
    for(Project projectComponent : project.getSubProjects()){
      if(projectComponent.getId() ==  subProjectId){
        subProject = projectComponent;
      }
    }

    if(subProject != null) {
      try {
        createTask(webRequest, subProject);
      } catch (TaskCreationException e) {
        model.addAttribute(
                "error",
                e.getMessage()); // TODO Handle exceptions?????? if we redirect we dont see the error.
      }
    }
    return "redirect:/projects/" + projectId + "/" + subProjectId;
  }
}
