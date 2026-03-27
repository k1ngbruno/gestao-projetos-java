package br.com.a3.gestaoprojetos.repository;

import br.com.a3.gestaoprojetos.model.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProjectRepository {
    private final List<Project> projects = new ArrayList<>();
    private int nextId = 1;

    public Project save(Project project) {
        if (project.getId() == 0) {
            project.setId(nextId++);
            projects.add(project);
        }
        return project;
    }

    public List<Project> findAll() {
        projects.sort(Comparator.comparing(Project::getNome, String.CASE_INSENSITIVE_ORDER));
        return new ArrayList<>(projects);
    }

    public Optional<Project> findById(int id) {
        return projects.stream().filter(project -> project.getId() == id).findFirst();
    }

    public List<Project> searchByName(String term) {
        String normalized = term.toLowerCase();
        return projects.stream()
                .filter(project -> project.getNome().toLowerCase().contains(normalized))
                .sorted(Comparator.comparing(Project::getNome, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public boolean deleteById(int id) {
        return projects.removeIf(project -> project.getId() == id);
    }
}
