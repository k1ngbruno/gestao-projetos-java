package br.com.a3.gestaoprojetos;

import br.com.a3.gestaoprojetos.controller.ProjectController;
import br.com.a3.gestaoprojetos.controller.ReportController;
import br.com.a3.gestaoprojetos.controller.TaskController;
import br.com.a3.gestaoprojetos.controller.TeamController;
import br.com.a3.gestaoprojetos.controller.UserController;
import br.com.a3.gestaoprojetos.repository.ProjectRepository;
import br.com.a3.gestaoprojetos.repository.TaskRepository;
import br.com.a3.gestaoprojetos.repository.TeamRepository;
import br.com.a3.gestaoprojetos.repository.UserRepository;
import br.com.a3.gestaoprojetos.view.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        TeamRepository teamRepository = new TeamRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        TaskRepository taskRepository = new TaskRepository();

        UserController userController = new UserController(userRepository);
        TeamController teamController = new TeamController(teamRepository, userController);
        ProjectController projectController = new ProjectController(projectRepository, userController, teamController);
        TaskController taskController = new TaskController(taskRepository, projectController, userController);
        ReportController reportController = new ReportController(projectController, taskController);

        ConsoleMenu menu = new ConsoleMenu(
                userController,
                teamController,
                projectController,
                taskController,
                reportController
        );
        menu.start();
    }
}
