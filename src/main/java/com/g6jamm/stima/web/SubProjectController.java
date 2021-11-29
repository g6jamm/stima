package com.g6jamm.stima.web;

import com.g6jamm.stima.data.repository.mock.SubProjectRepositoryStub;
import com.g6jamm.stima.domain.model.SubProject;
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
  private SubProject subP = SUBPROJECT_SERVICE.createSubProject("TEST", LocalDate.of(2021, 5, 6), LocalDate.of(2021, 6, 5));
  //TODO skal fjernes

  @GetMapping("/subproject") //TODO /{subProjectId}
  public String subProjectPage(Model model) {
    model.addAttribute("subProject", subP);
    return "subProject";
  }

  @GetMapping("/subproject/createnew/") //TODO /{subProjectId}
  public String createSubProject(Model model) {
    model.addAttribute("true", "isCreate");
    return "subProject";
  }

  @PostMapping("/subproject/") //TODO /{subProjectId}
  public String createSubProject(WebRequest webRequest, Model model) {
    String subProjectName = webRequest.getParameter("name");
    String startDate = webRequest.getParameter("startDate");
    String endDate = webRequest.getParameter("endDate");
    //TODO check if valid date
    //TODO check if date are inside project start and end
    subP = SUBPROJECT_SERVICE.createSubProject(subProjectName, LocalDate.parse(startDate), LocalDate.parse(endDate));

    model.addAttribute("subProject", subP);

    return "subProject";
  }

}
