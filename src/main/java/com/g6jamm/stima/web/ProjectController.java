package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mysql.ProjectRepositoryMySQLImpl;
import com.g6jamm.stima.data.repository.mysql.SubProjectRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.TaskRepositoryImpl;
import com.g6jamm.stima.data.repository.mysql.UserRepositoryImpl;
import com.g6jamm.stima.data.repository.stub.*;
import com.g6jamm.stima.domain.model.*;
import com.g6jamm.stima.domain.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProjectController {
    private final ProjectService PROJECT_SERVICE =
            new ProjectService(new ProjectRepositoryMySQLImpl());
    private final TaskService TASK_SERVICE =
            new TaskService(new TaskRepositoryImpl(), new ResourceTypeRepositoryStub());
    private final SubProjectService SUBPROJECT_SERVICE =
            new SubProjectService(new SubProjectRepositoryImpl());
    private final UserService USER_SERVICE = new UserService(new UserRepositoryImpl());

    /**
     * View all projects.
     *
     * @param model Model
     * @return String
     * @auther Mathias
     */
    @GetMapping("/projects")
    public String projects(WebRequest webRequest, Model model) {
        if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) != null) {
            User user = USER_SERVICE.getUser((Integer) (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION)));
            List<ProjectComposite> projects = PROJECT_SERVICE.getProjects(user);

            model.addAttribute("projects", projects);

            ProjectColorService projectColorService = new ProjectColorService(new ProjectColorStub());
            model.addAttribute("projectColors", projectColorService.getProjectColors());

            return "projects";
        }
        return "redirect:/";
    }

    /**
     * View a specific project.
     *
     * @param model Model
     * @return String
     * @auther Mathias
     */
    @GetMapping("/projects/{projectId}")
    public String projectId(WebRequest webRequest, Model model, @PathVariable int projectId) {
        if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) != null) {
            User user = USER_SERVICE.getUser((Integer) (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION)));
            ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

            List<Project> subProjects = project.getSubProjects();
            model.addAttribute("projects", subProjects);

            List<Task> tasks = project.getTasks();
            model.addAttribute("tasks", tasks);

            model.addAttribute("parentproject", project);

            ProjectColorService projectColorService = new ProjectColorService(new ProjectColorStub());
            model.addAttribute("projectColors", projectColorService.getProjectColors());

            model.addAttribute("classActiveSettings", "active");

            model.addAttribute("resourceTypes", TASK_SERVICE.getResourceTypes());

            return "project";
        }
        return "redirect:/";
    }

    @PostMapping("/projects/{projectId}/create-subproject")
    public String createSubProject(WebRequest webRequest, Model model, @PathVariable int projectId) {
        if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) != null) {
            User user = USER_SERVICE.getUser((Integer) (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION)));

            String subProjectNameParam = webRequest.getParameter("subproject-name");
            String startDateParam = webRequest.getParameter("subproject-start-date");
            String endDateParam = webRequest.getParameter("subproject-end-date");
            String projectColorParam = webRequest.getParameter("subproject-color");

            // TODO check if valid date
            // TODO check if date are inside project start and end
            ProjectComposite project = PROJECT_SERVICE.getProjectById(user, projectId);

            ProjectLeaf subProject =
                    SUBPROJECT_SERVICE.createSubProject(
                            subProjectNameParam,
                            LocalDate.parse(startDateParam),
                            LocalDate.parse(endDateParam),
                            projectColorParam,
                            projectId);

            project
                    .getSubProjects()
                    .add(subProject); // TODO skal fjernes - Mere object orienteret at gøre det?

            model.addAttribute("subProject", subProject); // TODO doesnt matter? we redirect?

            return "redirect:/projects/" + projectId;
        }
        return "redirect:/";
    }

    @PostMapping("/projects/create-project")
    public String createProject(WebRequest webRequest, Model model) {
        if (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION) != null) {
            User user = USER_SERVICE.getUser((Integer) (webRequest.getAttribute("user", WebRequest.SCOPE_SESSION)));

        String projectNameParam = webRequest.getParameter("project-name");
        String startDateParam = webRequest.getParameter("project-start-date");
        String endDateParam = webRequest.getParameter("project-end-date");
        String projectColorParam = webRequest.getParameter("project-color");

        // TODO check if valid date
        // TODO check if date are inside project start and end

        ProjectComposite project =
                PROJECT_SERVICE.createProject(
                        projectNameParam,
                        LocalDate.parse(startDateParam),
                        LocalDate.parse(endDateParam),
                        projectColorParam,user);

        model.addAttribute("project", project);

        return "redirect:/projects";
    }
        return "redirect:/";
    }

    /**
     * Navigates the user to edit project page.
     *
     * @param webRequest WebRequest
     * @param projectId  int
     * @return String
     * @auther Mathias
     */
    @PostMapping("/edit-project/{projectId}")
    public String editProject(WebRequest webRequest, @PathVariable int projectId) {
        return "redirect:/project/edit-project"; // TODO: redirect?
    }

    /**
     * Deletes the project by id and navigate the user to the project page.
     *
     * @param webRequest WebRequest
     * @param projectId  int
     * @return String
     * @auther Mathias
     */
    @PostMapping("/delete-project/{projectId}")
    public String deleteProject(WebRequest webRequest, @PathVariable int projectId) {
        return "redirect:/project"; // TODO: redirect?
    }
}
