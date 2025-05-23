package br.ifes.pmxnet.dominio;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Projeto extends EntityBase {


    private String nome;
    @ManyToOne
    private Usuario criador;

    @OneToMany
    private List<Informacao> informacoes;


    public String getNome() { return nome; }

    public Usuario getUsuario() { return criador; }
    public void  setUsuario(Usuario criador) { this.criador = criador; }


    public void setNome(String nome) { this.nome = nome; }

}
