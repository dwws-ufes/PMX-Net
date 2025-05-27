package br.ifes.pmxnet.dominio;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Informacao extends EntityBase {

    private String conteudo;
    // private Date data;
    @ManyToOne
    private Projeto projeto;

    // public Date getData() {
    // return this.data;
    // }

    // public void setData(Date data) {
    // this.data = data;
    // }

    public Projeto getProjeto() {
        return projeto;
    }

    public Usuario getUsuario() {
        return this.projeto.getUsuario();
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isOwner(Long id) {
        if (this.getUsuario().getId() == id) {
            return true;
        }
        return false;
    }

}
