package br.ifes.pmxnet.dominio;

import jakarta.persistence.Entity;

@Entity
public class Pessoa extends EntityBase {

    private String nome;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
