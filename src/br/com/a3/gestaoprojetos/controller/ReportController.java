package br.com.a3.gestaoprojetos.controller;

import br.com.a3.gestaoprojetos.model.Project;
import br.com.a3.gestaoprojetos.model.TaskItem;
import br.com.a3.gestaoprojetos.model.TaskStatus;

import java.util.List;

public class ReportController {
    private final ProjectController projectController;
    private final TaskController taskController;

    public ReportController(ProjectController projectController, TaskController taskController) {
        this.projectController = projectController;
        this.taskController = taskController;
    }

    public String generateProjectSummary(int projectId) {
        Project project = projectController.findById(projectId);
        List<TaskItem> tasks = taskController.listByProject(projectId);

        long pendentes = tasks.stream().filter(task -> task.getStatus() == TaskStatus.PENDENTE).count();
        long emAndamento = tasks.stream().filter(task -> task.getStatus() == TaskStatus.EM_ANDAMENTO).count();
        long concluidas = tasks.stream().filter(task -> task.getStatus() == TaskStatus.CONCLUIDA).count();
        long bloqueadas = tasks.stream().filter(task -> task.getStatus() == TaskStatus.BLOQUEADA).count();

        return """
                ---------- RELATÓRIO DO PROJETO ----------
                Projeto: %s
                Status do projeto: %s
                Gerente responsável: %s
                Quantidade de equipes alocadas: %d
                Quantidade total de tarefas: %d
                Tarefas pendentes: %d
                Tarefas em andamento: %d
                Tarefas concluídas: %d
                Tarefas bloqueadas: %d
                -----------------------------------------
                """.formatted(
                project.getNome(),
                project.getStatus(),
                project.getGerenteResponsavel().getNomeCompleto(),
                project.getEquipes().size(),
                tasks.size(),
                pendentes,
                emAndamento,
                concluidas,
                bloqueadas
        );
    }
}
