package br.com.a3.gestaoprojetos.controller;

import br.com.a3.gestaoprojetos.exception.NotFoundException;
import br.com.a3.gestaoprojetos.model.Team;
import br.com.a3.gestaoprojetos.model.User;
import br.com.a3.gestaoprojetos.repository.TeamRepository;

import java.util.List;

public class TeamController {
    private final TeamRepository repository;
    private final UserController userController;

    public TeamController(TeamRepository repository, UserController userController) {
        this.repository = repository;
        this.userController = userController;
    }

    public Team create(String nome, String descricao) {
        Team team = new Team(nome, descricao);
        return repository.save(team);
    }

    public void addMember(int teamId, int userId) {
        Team team = findById(teamId);
        User user = userController.findById(userId);
        team.adicionarMembro(user);
    }

    public Team findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Equipe não encontrada."));
    }

    public List<Team> listAll() {
        return repository.findAll();
    }

    public void delete(int id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Equipe não encontrada para exclusão.");
        }
    }
}
