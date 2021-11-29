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


    @PostMapping("/create-task")
    public String createTask(WebRequest webRequest, Model model) {
        String name = webRequest.getParameter("task_name");
        model.addAttribute("Task",taskService.createtask(name));
        return "Task";
    }


    @GetMapping("/task")
    public String task(WebRequest webRequest, Model model){
        model.addAttribute("Task", taskService.createtask("Placeholder"));
        return "Task";
    }
}
