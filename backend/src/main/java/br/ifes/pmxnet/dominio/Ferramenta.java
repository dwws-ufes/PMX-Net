package br.ifes.pmxnet.dominio;

import jakarta.persistence.*;

@Entity
public class Ferramenta extends EntityBase {

    private String nome;
    private String link;
    private String nome_usuario;
    private String token;
    private String tipo;

    @ManyToOne
    private Projeto projeto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Projeto getProjeto() {
        return this.projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getUsuario() {
        return this.projeto.getUsuario();
    }

    public boolean isOwner(Long id) {
        if (this.getUsuario().getId() == id) {
            return true;
        }
        return false;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
