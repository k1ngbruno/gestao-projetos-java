package br.com.a3.gestaoprojetos.controller;

import br.com.a3.gestaoprojetos.exception.BusinessException;
import br.com.a3.gestaoprojetos.exception.NotFoundException;
import br.com.a3.gestaoprojetos.model.User;
import br.com.a3.gestaoprojetos.model.UserRole;
import br.com.a3.gestaoprojetos.repository.UserRepository;

import java.util.List;

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public User create(String nome, String cpf, String email, String cargo, String login, String senha, UserRole perfil) {
        if (repository.findByCpf(cpf).isPresent()) {
            throw new BusinessException("Já existe um usuário cadastrado com esse CPF.");
        }
        if (repository.findByEmail(email).isPresent()) {
            throw new BusinessException("Já existe um usuário cadastrado com esse e-mail.");
        }
        if (repository.findByLogin(login).isPresent()) {
            throw new BusinessException("Já existe um usuário cadastrado com esse login.");
        }
        User user = new User(nome, cpf, email, cargo, login, senha, perfil);
        return repository.save(user);
    }

    public List<User> listAll() {
        return repository.findAll();
    }

    public List<User> searchByName(String term) {
        return repository.searchByName(term);
    }

    public User findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    public void update(int id, String nome, String email, String cargo, UserRole perfil) {
        User user = findById(id);
        user.setNomeCompleto(nome);
        user.setEmail(email);
        user.setCargo(cargo);
        user.setPerfil(perfil);
    }

    public void delete(int id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Usuário não encontrado para exclusão.");
        }
    }
}
