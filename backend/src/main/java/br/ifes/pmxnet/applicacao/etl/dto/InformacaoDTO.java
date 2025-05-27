package br.ifes.pmxnet.applicacao.etl.dto;

//import java.util.Date;

public class InformacaoDTO {
    private Long id;
    private String conteudo;
    private Long projetoId;
    // private Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public Date getData() {
    // return this.data;
    // }

    // public void setData(Date data) {
    // this.data = data;
    // }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

}
