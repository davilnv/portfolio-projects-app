package br.com.davilnv.portfolio.service.project;

import br.com.davilnv.portfolio.exception.ProjectNotFoundException;
import br.com.davilnv.portfolio.model.ProjectModel;
import br.com.davilnv.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveProject(ProjectModel project) {
        repository.save(project);
    }

    @Override
    public List<ProjectModel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ProjectModel> getProjectById(Long id) throws ProjectNotFoundException {
        Optional<ProjectModel> project = repository.findById(id);
        if (project.isEmpty()) {
            throw new ProjectNotFoundException("Projeto não encontrado");
        }
        return project;
    }
}
