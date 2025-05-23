package br.ifes.pmxnet.dominio;

import jakarta.persistence.*;

@Entity


public class Ferramenta extends EntityBase {

    private String nome;
    private String descricao;

    @OneToOne
    private Projeto projeto;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Projeto getProjeto() { return this.projeto; }
    public void setProjeto(Projeto projeto) { this.projeto = projeto; }

    public Usuario getUsuario() { return this.projeto.getUsuario(); }

}
