package br.com.a3.gestaoprojetos.view;

import br.com.a3.gestaoprojetos.controller.ProjectController;
import br.com.a3.gestaoprojetos.controller.ReportController;
import br.com.a3.gestaoprojetos.controller.TaskController;
import br.com.a3.gestaoprojetos.controller.TeamController;
import br.com.a3.gestaoprojetos.controller.UserController;
import br.com.a3.gestaoprojetos.exception.BusinessException;
import br.com.a3.gestaoprojetos.exception.NotFoundException;
import br.com.a3.gestaoprojetos.exception.ValidationException;
import br.com.a3.gestaoprojetos.model.Project;
import br.com.a3.gestaoprojetos.model.ProjectStatus;
import br.com.a3.gestaoprojetos.model.TaskStatus;
import br.com.a3.gestaoprojetos.model.Team;
import br.com.a3.gestaoprojetos.model.User;
import br.com.a3.gestaoprojetos.model.UserRole;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final UserController userController;
    private final TeamController teamController;
    private final ProjectController projectController;
    private final TaskController taskController;
    private final ReportController reportController;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(UserController userController, TeamController teamController,
                       ProjectController projectController, TaskController taskController,
                       ReportController reportController) {
        this.userController = userController;
        this.teamController = teamController;
        this.projectController = projectController;
        this.taskController = taskController;
        this.reportController = reportController;
    }

    public void start() {
        int option = -1;
        while (option != 0) {
            showMainMenu();
            option = readInt("Escolha uma opção: ");
            try {
                switch (option) {
                    case 1 -> cadastrarUsuario();
                    case 2 -> listarUsuarios();
                    case 3 -> cadastrarEquipe();
                    case 4 -> adicionarMembroNaEquipe();
                    case 5 -> cadastrarProjeto();
                    case 6 -> alocarEquipeEmProjeto();
                    case 7 -> cadastrarTarefa();
                    case 8 -> atualizarStatusProjeto();
                    case 9 -> atualizarStatusTarefa();
                    case 10 -> gerarRelatorioProjeto();
                    case 11 -> carregarDadosExemplo();
                    case 0 -> System.out.println("Encerrando o sistema.");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (ValidationException | NotFoundException | BusinessException |
                     IllegalArgumentException | DateTimeParseException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private void showMainMenu() {
        System.out.println("""
                ===== SISTEMA DE GESTÃO DE PROJETOS E EQUIPES =====
                1 - Cadastrar usuário
                2 - Listar usuários
                3 - Cadastrar equipe
                4 - Adicionar membro em equipe
                5 - Cadastrar projeto
                6 - Alocar equipe em projeto
                7 - Cadastrar tarefa
                8 - Atualizar status do projeto
                9 - Atualizar status da tarefa
                10 - Gerar relatório de projeto
                11 - Carregar dados de exemplo
                0 - Sair
                """);
    }

    private void cadastrarUsuario() {
        System.out.println("=== Cadastro de usuário ===");
        String nome = readLine("Nome completo: ");
        String cpf = readLine("CPF: ");
        String email = readLine("E-mail: ");
        String cargo = readLine("Cargo: ");
        String login = readLine("Login: ");
        String senha = readLine("Senha: ");
        UserRole perfil = readUserRole();
        User user = userController.create(nome, cpf, email, cargo, login, senha, perfil);
        System.out.println("Usuário cadastrado com sucesso. ID: " + user.getId());
    }

    private void listarUsuarios() {
        System.out.println("=== Usuários cadastrados ===");
        List<User> users = userController.listAll();
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        users.forEach(System.out::println);
    }

    private void cadastrarEquipe() {
        System.out.println("=== Cadastro de equipe ===");
        String nome = readLine("Nome da equipe: ");
        String descricao = readLine("Descrição: ");
        Team team = teamController.create(nome, descricao);
        System.out.println("Equipe cadastrada com sucesso. ID: " + team.getId());
    }

    private void adicionarMembroNaEquipe() {
        System.out.println("=== Adicionar membro em equipe ===");
        int teamId = readInt("ID da equipe: ");
        int userId = readInt("ID do usuário: ");
        teamController.addMember(teamId, userId);
        System.out.println("Membro adicionado com sucesso.");
    }

    private void cadastrarProjeto() {
        System.out.println("=== Cadastro de projeto ===");
        String nome = readLine("Nome do projeto: ");
        String descricao = readLine("Descrição: ");
        LocalDate dataInicio = readDate("Data de início (AAAA-MM-DD): ");
        LocalDate dataFim = readDate("Data de término prevista (AAAA-MM-DD): ");
        ProjectStatus status = readProjectStatus();
        int gerenteId = readInt("ID do gerente responsável: ");
        Project project = projectController.create(nome, descricao, dataInicio, dataFim, status, gerenteId);
        System.out.println("Projeto cadastrado com sucesso. ID: " + project.getId());
    }

    private void alocarEquipeEmProjeto() {
        System.out.println("=== Alocar equipe em projeto ===");
        int projectId = readInt("ID do projeto: ");
        int teamId = readInt("ID da equipe: ");
        projectController.addTeamToProject(projectId, teamId);
        System.out.println("Equipe alocada ao projeto com sucesso.");
    }

    private void cadastrarTarefa() {
        System.out.println("=== Cadastro de tarefa ===");
        String titulo = readLine("Título da tarefa: ");
        String descricao = readLine("Descrição: ");
        LocalDate dataInicio = readDate("Data de início (AAAA-MM-DD): ");
        LocalDate dataFim = readDate("Data de fim prevista (AAAA-MM-DD): ");
        TaskStatus status = readTaskStatus();
        int projectId = readInt("ID do projeto: ");
        int responsavelId = readInt("ID do responsável: ");
        taskController.create(titulo, descricao, dataInicio, dataFim, status, projectId, responsavelId);
        System.out.println("Tarefa cadastrada com sucesso.");
    }

    private void atualizarStatusProjeto() {
        System.out.println("=== Atualizar status do projeto ===");
        int projectId = readInt("ID do projeto: ");
        ProjectStatus status = readProjectStatus();
        projectController.updateStatus(projectId, status);
        System.out.println("Status do projeto atualizado com sucesso.");
    }

    private void atualizarStatusTarefa() {
        System.out.println("=== Atualizar status da tarefa ===");
        int taskId = readInt("ID da tarefa: ");
        TaskStatus status = readTaskStatus();
        taskController.updateStatus(taskId, status);
        System.out.println("Status da tarefa atualizado com sucesso.");
    }

    private void gerarRelatorioProjeto() {
        System.out.println("=== Relatório do projeto ===");
        int projectId = readInt("ID do projeto: ");
        System.out.println(reportController.generateProjectSummary(projectId));
    }

    private void carregarDadosExemplo() {
        if (!userController.listAll().isEmpty()) {
            System.out.println("Os dados de exemplo já foram carregados.");
            return;
        }

        User admin = userController.create("Ana Paula Costa", "11122233344", "ana@empresa.com", "Administradora", "ana.admin", "123", UserRole.ADMINISTRADOR);
        User gerente = userController.create("Carlos Mendes", "22233344455", "carlos@empresa.com", "Gerente de Projetos", "carlos.ger", "123", UserRole.GERENTE);
        User colaborador = userController.create("Juliana Lima", "33344455566", "juliana@empresa.com", "Desenvolvedora", "juliana.dev", "123", UserRole.COLABORADOR);

        Team team = teamController.create("Equipe Alfa", "Equipe focada em desenvolvimento web");
        teamController.addMember(team.getId(), admin.getId());
        teamController.addMember(team.getId(), gerente.getId());
        teamController.addMember(team.getId(), colaborador.getId());

        Project project = projectController.create(
                "Portal do Cliente",
                "Sistema para acompanhamento de demandas dos clientes",
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                ProjectStatus.EM_ANDAMENTO,
                gerente.getId()
        );
        projectController.addTeamToProject(project.getId(), team.getId());

        taskController.create(
                "Criar tela de login",
                "Desenvolver a interface inicial e a validação de acesso",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                TaskStatus.EM_ANDAMENTO,
                project.getId(),
                colaborador.getId()
        );

        taskController.create(
                "Modelar banco de dados",
                "Definir tabelas e relacionamentos do projeto",
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                TaskStatus.PENDENTE,
                project.getId(),
                gerente.getId()
        );

        System.out.println("Dados de exemplo carregados com sucesso.");
    }

    private UserRole readUserRole() {
        System.out.println("Perfis disponíveis: 1-ADMINISTRADOR | 2-GERENTE | 3-COLABORADOR");
        int option = readInt("Escolha o perfil: ");
        return switch (option) {
            case 1 -> UserRole.ADMINISTRADOR;
            case 2 -> UserRole.GERENTE;
            case 3 -> UserRole.COLABORADOR;
            default -> throw new IllegalArgumentException("Perfil inválido.");
        };
    }

    private ProjectStatus readProjectStatus() {
        System.out.println("Status disponíveis: 1-PLANEJADO | 2-EM_ANDAMENTO | 3-CONCLUIDO | 4-CANCELADO");
        int option = readInt("Escolha o status do projeto: ");
        return switch (option) {
            case 1 -> ProjectStatus.PLANEJADO;
            case 2 -> ProjectStatus.EM_ANDAMENTO;
            case 3 -> ProjectStatus.CONCLUIDO;
            case 4 -> ProjectStatus.CANCELADO;
            default -> throw new IllegalArgumentException("Status de projeto inválido.");
        };
    }

    private TaskStatus readTaskStatus() {
        System.out.println("Status disponíveis: 1-PENDENTE | 2-EM_ANDAMENTO | 3-CONCLUIDA | 4-BLOQUEADA");
        int option = readInt("Escolha o status da tarefa: ");
        return switch (option) {
            case 1 -> TaskStatus.PENDENTE;
            case 2 -> TaskStatus.EM_ANDAMENTO;
            case 3 -> TaskStatus.CONCLUIDA;
            case 4 -> TaskStatus.BLOQUEADA;
            default -> throw new IllegalArgumentException("Status de tarefa inválido.");
        };
    }

    private String readLine(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    private int readInt(String label) {
        while (true) {
            System.out.print(label);
            String value = scanner.nextLine();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private LocalDate readDate(String label) {
        System.out.print(label);
        return LocalDate.parse(scanner.nextLine());
    }
}
