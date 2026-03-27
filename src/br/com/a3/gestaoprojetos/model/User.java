package br.com.a3.gestaoprojetos.model;

import br.com.a3.gestaoprojetos.util.ValidationUtils;

public class User {
    private int id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private UserRole perfil;

    public User(String nomeCompleto, String cpf, String email, String cargo, String login, String senha, UserRole perfil) {
        setNomeCompleto(nomeCompleto);
        setCpf(cpf);
        setEmail(email);
        setCargo(cargo);
        setLogin(login);
        setSenha(senha);
        setPerfil(perfil);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        ValidationUtils.requirePositive(id, "id");
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        ValidationUtils.requireNotBlank(nomeCompleto, "nome completo");
        this.nomeCompleto = nomeCompleto.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        ValidationUtils.requireValidCpf(cpf);
        this.cpf = cpf.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        ValidationUtils.requireValidEmail(email);
        this.email = email.trim().toLowerCase();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        ValidationUtils.requireNotBlank(cargo, "cargo");
        this.cargo = cargo.trim();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        ValidationUtils.requireNotBlank(login, "login");
        this.login = login.trim().toLowerCase();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        ValidationUtils.requireNotBlank(senha, "senha");
        this.senha = senha;
    }

    public UserRole getPerfil() {
        return perfil;
    }

    public void setPerfil(UserRole perfil) {
        if (perfil == null) {
            throw new IllegalArgumentException("O perfil é obrigatório.");
        }
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Cargo: %s | Perfil: %s | Login: %s",
                id, nomeCompleto, cargo, perfil, login);
    }
}
