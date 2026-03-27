package br.com.a3.gestaoprojetos.controller;

import br.com.a3.gestaoprojetos.exception.NotFoundException;
import br.com.a3.gestaoprojetos.model.Project;
import br.com.a3.gestaoprojetos.model.ProjectStatus;
import br.com.a3.gestaoprojetos.model.Team;
import br.com.a3.gestaoprojetos.model.User;
import br.com.a3.gestaoprojetos.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.List;

public class ProjectController {
    private final ProjectRepository repository;
    private final UserController userController;
    private final TeamController teamController;

    public ProjectController(ProjectRepository repository, UserController userController, TeamController teamController) {
        this.repository = repository;
        this.userController = userController;
        this.teamController = teamController;
    }

    public Project create(String nome, String descricao, LocalDate dataInicio, LocalDate dataTermino,
                          ProjectStatus status, int gerenteId) {
        User gerente = userController.findById(gerenteId);
        Project project = new Project(nome, descricao, dataInicio, dataTermino, status, gerente);
        return repository.save(project);
    }

    public void addTeamToProject(int projectId, int teamId) {
        Project project = findById(projectId);
        Team team = teamController.findById(teamId);
        project.adicionarEquipe(team);
    }

    public void updateStatus(int projectId, ProjectStatus status) {
        Project project = findById(projectId);
        project.setStatus(status);
    }

    public List<Project> listAll() {
        return repository.findAll();
    }

    public List<Project> searchByName(String term) {
        return repository.searchByName(term);
    }

    public Project findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Projeto não encontrado."));
    }

    public void delete(int id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Projeto não encontrado para exclusão.");
        }
    }
}
