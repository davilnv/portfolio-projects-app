package br.com.davilnv.portfolio.controller;

import br.com.davilnv.portfolio.model.ProjectModel;
import br.com.davilnv.portfolio.service.person.PersonService;
import br.com.davilnv.portfolio.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final PersonService personService;

    @Autowired
    public ProjectController(ProjectService projectService, PersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @GetMapping("/viewProject")
    public String getProjects(Model model) {
        model.addAttribute("project", new ProjectModel());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("managers", personService.findAllByEmployeeFalse());
        return "/pages/project";

    }

    @PostMapping("/addProject")
    public RedirectView addProject(
            @ModelAttribute("project") ProjectModel projectModel,
            RedirectAttributes redirectAttributes
    ) {
        final RedirectView redirectView = new RedirectView("/project/viewProject", true);
        ProjectModel projectModelSaved = projectService.saveProject(projectModel);
        redirectAttributes.addFlashAttribute("projectModel", projectModelSaved);
        redirectAttributes.addFlashAttribute("addProjectSucess", true);
        return redirectView;
    }
}
