package br.ifes.pmxnet.dominio;

import jakarta.persistence.*;

@Entity
public class Informacao extends EntityBase {

    private String conteudo;
    @ManyToOne
    private Projeto projeto;
    @ManyToOne
    private Ferramenta ferramenta;
    @ManyToOne
    private Tipo tipo;

    public Projeto getProjeto() { return projeto; }

    public Usuario getUsuario() { return this.projeto.getUsuario(); }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

}
