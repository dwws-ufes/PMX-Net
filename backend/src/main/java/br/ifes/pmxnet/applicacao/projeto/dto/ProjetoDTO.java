package br.ifes.pmxnet.applicacao.projeto.dto;

import java.util.List;

public class ProjetoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long usuarioId;
    private List<FerramentaDTO> ferramentas;

    public void setFerramentas(List<FerramentaDTO> ferramentas) {
        this.ferramentas = ferramentas;
    }

    public List<FerramentaDTO> getFerramentas() {
        return this.ferramentas;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(Long id) {
        this.usuarioId = id;
    }

    public Long getUsuarioId() {
        return this.usuarioId;
    }

}
