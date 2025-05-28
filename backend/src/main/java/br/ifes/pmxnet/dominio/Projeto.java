package br.ifes.pmxnet.dominio;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class Projeto extends EntityBase {

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Ferramenta> ferramentas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Informacao> informacoes;

    public List<Ferramenta> getFerramentas() {
        return this.ferramentas;
    }

    public void setFerramentas(List<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return criador;
    }

    public void setUsuario(Usuario criador) {
        this.criador = criador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public List<Informacao> getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(List<Informacao> informacoes) {
        this.informacoes = informacoes;
    }

    public boolean isOwner(Long id) {
        if (this.getUsuario().getId() == id) {
            return true;
        }
        return false;
    }

}
