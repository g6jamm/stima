package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mock.SubProjectRepositoryStub;
import com.g6jamm.stima.domain.service.SubProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class SubProjectController {

  private final SubProjectService SUBPROJECT_SERVICE = new SubProjectService(new SubProjectRepositoryStub());

  @GetMapping("/subproject/{subProjectId}")
  public String subProjectPage() {
    return "subProject";
  }

  @GetMapping("/subproject/createnew/")
  public String createSubProject(Model model) {
    model.addAttribute("true", "isCreate");
    return "subProject";
  }

  @PostMapping("/subproject/createnew/")
  public String createSubProject(WebRequest webRequest) {
    String subProjectName = webRequest.getParameter("name");
    String startDate = webRequest.getParameter("startDate");
    String endDate = webRequest.getParameter("endDate");
    //TODO check if valid date
    //TODO check if date are inside project start and end
    SUBPROJECT_SERVICE.createSubProject(subProjectName, LocalDate.parse(startDate), LocalDate.parse(endDate));

    return "redirect:/subproject";
  }

}
