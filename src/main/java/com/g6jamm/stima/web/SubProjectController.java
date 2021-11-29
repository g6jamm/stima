package com.g6jamm.stima.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SubProjectController {


    @GetMapping("/subproject") // TODO rename, skal nok hedde noget andet
    public String subProjectPage() {
        return "subProject";
    }


    @PostMapping("/create-task")
    public String createTask(WebRequest webRequest) {
        String name = webRequest.getParameter("task_name");

        return "Task";
    }
}
