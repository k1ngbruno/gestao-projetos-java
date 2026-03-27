package br.com.a3.gestaoprojetos.controller;

import br.com.a3.gestaoprojetos.exception.NotFoundException;
import br.com.a3.gestaoprojetos.model.Project;
import br.com.a3.gestaoprojetos.model.TaskItem;
import br.com.a3.gestaoprojetos.model.TaskStatus;
import br.com.a3.gestaoprojetos.model.User;
import br.com.a3.gestaoprojetos.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    private final TaskRepository repository;
    private final ProjectController projectController;
    private final UserController userController;

    public TaskController(TaskRepository repository, ProjectController projectController, UserController userController) {
        this.repository = repository;
        this.projectController = projectController;
        this.userController = userController;
    }

    public TaskItem create(String titulo, String descricao, LocalDate dataInicio, LocalDate dataFimPrevista,
                           TaskStatus status, int projectId, int responsavelId) {
        Project project = projectController.findById(projectId);
        User responsavel = userController.findById(responsavelId);
        TaskItem task = new TaskItem(titulo, descricao, dataInicio, dataFimPrevista, status, project, responsavel);
        return repository.save(task);
    }

    public void updateStatus(int id, TaskStatus status) {
        TaskItem task = findById(id);
        task.setStatus(status);
    }

    public List<TaskItem> listAll() {
        return repository.findAll();
    }

    public List<TaskItem> listByProject(int projectId) {
        return repository.findByProjectId(projectId);
    }

    public TaskItem findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada."));
    }

    public void delete(int id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Tarefa não encontrada para exclusão.");
        }
    }
}
