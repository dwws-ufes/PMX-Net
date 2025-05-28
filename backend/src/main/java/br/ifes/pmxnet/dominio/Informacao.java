package br.ifes.pmxnet.dominio;

import jakarta.persistence.*;

@Entity
public class Informacao extends EntityBase {

    private String conteudo;
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
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
