package br.com.davilnv.portfolio.controller;

import br.com.davilnv.portfolio.exception.ProjectNotFoundException;
import br.com.davilnv.portfolio.model.ProjectModel;
import br.com.davilnv.portfolio.service.person.PersonService;
import br.com.davilnv.portfolio.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

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

    @GetMapping("/updateProject")
    public String updateProject(@RequestParam Long id, Model model) {
        try {
            model.addAttribute("project", projectService.getProjectById(id));
            model.addAttribute("statusGetProject", true);
        } catch (ProjectNotFoundException e) {
            model.addAttribute("statusGetProject", false);
        }
        return "/pages/project";
    }

    @PostMapping("/updateProject")
    public RedirectView updateProject(
            @ModelAttribute("project") ProjectModel projectModel,
            RedirectAttributes redirectAttributes
    ) {
        final RedirectView redirectView = new RedirectView("/project/viewProject", true);
        ProjectModel projectModelSaved = projectService.saveProject(projectModel);
        redirectAttributes.addFlashAttribute("projectUpdate", projectModelSaved);
        redirectAttributes.addFlashAttribute("updateProjectSucess", true);
        return redirectView;
    }
}
