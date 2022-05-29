package model;

import java.util.List;

public class Carro {

    private String placa;
    private String modelo;
    private List<TipoCombustivel> combustiveis;
    private Integer capacidade;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public List<TipoCombustivel> getCombustiveis() {
        return combustiveis;
    }

    public void setCombustiveis(List<TipoCombustivel> combustiveis) {
        this.combustiveis = combustiveis;
    }

    @Override
    public String toString() {
        return "Carro{" +
            "\nplaca:'" + placa + '\'' +
            "\nmodelo:'" + modelo + '\'' +
            "\ncapacidade:" + capacidade +
            "\n-------------------------" +
            '}';
    }
}
