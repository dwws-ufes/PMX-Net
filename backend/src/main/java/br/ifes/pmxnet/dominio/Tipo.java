package br.ifes.pmxnet.dominio;


import jakarta.persistence.*;

@Entity
public class Tipo extends EntityBase{

    private String nome;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

}
