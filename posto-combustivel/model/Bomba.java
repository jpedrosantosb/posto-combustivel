package model;

import interfaces.Servicos;

public class Bomba {

    private String nome;

    private Combustivel combustivel;

    private Integer totalizador = 0;

    private Double velAbastecimentoMin;

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void totalizadorBomba(Integer totalAbastecido){
        totalizador = totalizador + totalAbastecido;
    }

    public Integer getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(Integer totalizador) {
        this.totalizador = totalizador;
    }

    public Double getVelAbastecimentoMin() {
        return velAbastecimentoMin;
    }

    public void setVelAbastecimentoMin(Double velAbastecimentoMin) {
        this.velAbastecimentoMin = velAbastecimentoMin;
    }
}
