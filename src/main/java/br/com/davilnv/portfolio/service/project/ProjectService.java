package br.com.davilnv.portfolio.service.project;

import br.com.davilnv.portfolio.exception.ProjectNotFoundException;
import br.com.davilnv.portfolio.model.ProjectModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {

    ProjectModel saveProject(ProjectModel project);
    List<ProjectModel> findAll();

    Optional<ProjectModel> getProjectById(Long id) throws ProjectNotFoundException;

}
