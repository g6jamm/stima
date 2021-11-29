package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mock.TaskRepositoryStub;
import com.g6jamm.stima.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SubProjectController {

    TaskService taskService = new TaskService(new TaskRepositoryStub());


    @GetMapping("/subproject") // TODO rename, skal nok hedde noget andet
    public String subProjectPage() {
        return "subProject";
    }


    /**
     * Post method for creating new tasks.
     *
     * Takes all input from the form and passes them to taskService which create a Task object.
     *
     * This object is then added to the parameter "model".
     *
     * @Author Andreas
     * @param webRequest
     * @param model
     * @return redirects user to Task page.
     */

    @PostMapping("/create-task") //TODO Change to /projects/{project_id}/create-task
    public String createTask(WebRequest webRequest, Model model) {
        //TODO get project from project_id

        String name = webRequest.getParameter("task_name");
        double hours = webRequest.getParameter("task_hours").matches("[0-9]+") ?
                Double.valueOf(webRequest.getParameter("task_hours")) : 0.0;

        String resourceType = webRequest.getParameter("task_resourcetype");
        String startDate =
                !webRequest.getParameter("task_startdate").isEmpty() ?
                        webRequest.getParameter("task_startdate") : "1990-01-01"; //change to project start date
        String endDate =
                !webRequest.getParameter("task_enddate").isEmpty() ?
                        webRequest.getParameter("task_enddate") : "1990-01-01"; //change to project end date

        //TODO Add to Task to project

        model.addAttribute("Task", taskService.createtask(name, hours, resourceType, startDate, endDate));

        return "Task"; //TODO redirect to /projects/{project_id}
    }


    /**
     * Initial Get method for displaying a task.
     *
     *
     * @Author Andreas
     * @param webRequest
     * @param model
     * @return
     */

    @GetMapping("/task")
    public String task(WebRequest webRequest, Model model) {
        if(model.getAttribute("Task") == null) {
            model.addAttribute("Task", taskService.createtask("Placeholder", 1.0, "test", "1990-01-01", "1991-01-01"));
        }
        return "Task";
    }
}
