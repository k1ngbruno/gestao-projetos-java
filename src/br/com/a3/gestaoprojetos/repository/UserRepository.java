package br.com.a3.gestaoprojetos.repository;

import br.com.a3.gestaoprojetos.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(nextId++);
            users.add(user);
        }
        return user;
    }

    public List<User> findAll() {
        users.sort(Comparator.comparing(User::getNomeCompleto, String.CASE_INSENSITIVE_ORDER));
        return new ArrayList<>(users);
    }

    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findByCpf(String cpf) {
        return users.stream().filter(user -> user.getCpf().equalsIgnoreCase(cpf)).findFirst();
    }

    public Optional<User> findByLogin(String login) {
        return users.stream().filter(user -> user.getLogin().equalsIgnoreCase(login)).findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public List<User> searchByName(String term) {
        String normalized = term.toLowerCase();
        return users.stream()
                .filter(user -> user.getNomeCompleto().toLowerCase().contains(normalized))
                .sorted(Comparator.comparing(User::getNomeCompleto, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public boolean deleteById(int id) {
        return users.removeIf(user -> user.getId() == id);
    }
}
