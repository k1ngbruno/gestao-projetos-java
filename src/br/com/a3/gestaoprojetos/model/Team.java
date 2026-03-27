package br.com.a3.gestaoprojetos.model;

import br.com.a3.gestaoprojetos.util.ValidationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private int id;
    private String nome;
    private String descricao;
    private final List<User> membros = new ArrayList<>();

    public Team(String nome, String descricao) {
        setNome(nome);
        setDescricao(descricao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        ValidationUtils.requirePositive(id, "id");
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        ValidationUtils.requireNotBlank(nome, "nome da equipe");
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        ValidationUtils.requireNotBlank(descricao, "descrição da equipe");
        this.descricao = descricao.trim();
    }

    public List<User> getMembros() {
        return Collections.unmodifiableList(membros);
    }

    public void adicionarMembro(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }
        boolean exists = membros.stream().anyMatch(member -> member.getId() == user.getId());
        if (!exists) {
            membros.add(user);
        }
    }

    public void removerMembro(int userId) {
        membros.removeIf(member -> member.getId() == userId);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Equipe: %s | Membros: %d", id, nome, membros.size());
    }
}
