package br.com.a3.gestaoprojetos.model;

import br.com.a3.gestaoprojetos.util.ValidationUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project {
    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private ProjectStatus status;
    private User gerenteResponsavel;
    private final List<Team> equipes = new ArrayList<>();

    public Project(String nome, String descricao, LocalDate dataInicio, LocalDate dataTerminoPrevista,
                   ProjectStatus status, User gerenteResponsavel) {
        setNome(nome);
        setDescricao(descricao);
        setDataInicio(dataInicio);
        setDataTerminoPrevista(dataTerminoPrevista);
        setStatus(status);
        setGerenteResponsavel(gerenteResponsavel);
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
        ValidationUtils.requireNotBlank(nome, "nome do projeto");
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        ValidationUtils.requireNotBlank(descricao, "descrição do projeto");
        this.descricao = descricao.trim();
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início é obrigatória.");
        }
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTerminoPrevista() {
        return dataTerminoPrevista;
    }

    public void setDataTerminoPrevista(LocalDate dataTerminoPrevista) {
        if (dataTerminoPrevista == null) {
            throw new IllegalArgumentException("A data de término prevista é obrigatória.");
        }
        this.dataTerminoPrevista = dataTerminoPrevista;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("O status é obrigatório.");
        }
        this.status = status;
    }

    public User getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(User gerenteResponsavel) {
        if (gerenteResponsavel == null || gerenteResponsavel.getPerfil() != UserRole.GERENTE) {
            throw new IllegalArgumentException("O projeto precisa de um gerente responsável.");
        }
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public List<Team> getEquipes() {
        return Collections.unmodifiableList(equipes);
    }

    public void adicionarEquipe(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Equipe inválida.");
        }
        boolean exists = equipes.stream().anyMatch(item -> item.getId() == team.getId());
        if (!exists) {
            equipes.add(team);
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Projeto: %s | Status: %s | Gerente: %s",
                id, nome, status, gerenteResponsavel.getNomeCompleto());
    }
}
