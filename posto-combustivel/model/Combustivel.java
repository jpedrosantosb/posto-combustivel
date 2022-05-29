package model;

public class Combustivel {
    private String nome;
    private Double preco;
    private Integer codCombustivel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getCodCombustivel() {
        return codCombustivel;
    }

    public void setCodCombustivel(Integer codCombustivel) {
        this.codCombustivel = codCombustivel;
    }

    @Override
    public String toString() {
        return "Combustivel{" +
            "nome='" + nome + '\'' +
            ", código=" + codCombustivel +
            '}';
    }
}
