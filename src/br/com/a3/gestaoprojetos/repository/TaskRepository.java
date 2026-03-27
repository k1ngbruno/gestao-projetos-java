package br.com.a3.gestaoprojetos.repository;

import br.com.a3.gestaoprojetos.model.TaskItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final List<TaskItem> tasks = new ArrayList<>();
    private int nextId = 1;

    public TaskItem save(TaskItem task) {
        if (task.getId() == 0) {
            task.setId(nextId++);
            tasks.add(task);
        }
        return task;
    }

    public List<TaskItem> findAll() {
        tasks.sort(Comparator.comparing(TaskItem::getTitulo, String.CASE_INSENSITIVE_ORDER));
        return new ArrayList<>(tasks);
    }

    public Optional<TaskItem> findById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    public List<TaskItem> findByProjectId(int projectId) {
        return tasks.stream()
                .filter(task -> task.getProjeto().getId() == projectId)
                .sorted(Comparator.comparing(TaskItem::getDataFimPrevista))
                .toList();
    }

    public boolean deleteById(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
