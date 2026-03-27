package br.com.a3.gestaoprojetos.repository;

import br.com.a3.gestaoprojetos.model.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TeamRepository {
    private final List<Team> teams = new ArrayList<>();
    private int nextId = 1;

    public Team save(Team team) {
        if (team.getId() == 0) {
            team.setId(nextId++);
            teams.add(team);
        }
        return team;
    }

    public List<Team> findAll() {
        teams.sort(Comparator.comparing(Team::getNome, String.CASE_INSENSITIVE_ORDER));
        return new ArrayList<>(teams);
    }

    public Optional<Team> findById(int id) {
        return teams.stream().filter(team -> team.getId() == id).findFirst();
    }

    public boolean deleteById(int id) {
        return teams.removeIf(team -> team.getId() == id);
    }
}
