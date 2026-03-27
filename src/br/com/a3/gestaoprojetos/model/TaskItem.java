package br.com.a3.gestaoprojetos.model;

import br.com.a3.gestaoprojetos.util.ValidationUtils;

import java.time.LocalDate;

public class TaskItem {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;
    private TaskStatus status;
    private Project projeto;
    private User responsavel;

    public TaskItem(String titulo, String descricao, LocalDate dataInicio, LocalDate dataFimPrevista,
                    TaskStatus status, Project projeto, User responsavel) {
        setTitulo(titulo);
        setDescricao(descricao);
        setDataInicio(dataInicio);
        setDataFimPrevista(dataFimPrevista);
        setStatus(status);
        setProjeto(projeto);
        setResponsavel(responsavel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        ValidationUtils.requirePositive(id, "id");
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        ValidationUtils.requireNotBlank(titulo, "título da tarefa");
        this.titulo = titulo.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        ValidationUtils.requireNotBlank(descricao, "descrição da tarefa");
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

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(LocalDate dataFimPrevista) {
        if (dataFimPrevista == null) {
            throw new IllegalArgumentException("A data de fim prevista é obrigatória.");
        }
        this.dataFimPrevista = dataFimPrevista;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("O status da tarefa é obrigatório.");
        }
        this.status = status;
    }

    public Project getProjeto() {
        return projeto;
    }

    public void setProjeto(Project projeto) {
        if (projeto == null) {
            throw new IllegalArgumentException("O projeto é obrigatório.");
        }
        this.projeto = projeto;
    }

    public User getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(User responsavel) {
        if (responsavel == null) {
            throw new IllegalArgumentException("O responsável é obrigatório.");
        }
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Tarefa: %s | Status: %s | Projeto: %s | Responsável: %s",
                id, titulo, status, projeto.getNome(), responsavel.getNomeCompleto());
    }
}
