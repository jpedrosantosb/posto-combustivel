package interfaces;

import java.util.List;

import model.Bomba;
import model.Carro;
import model.Combustivel;

public interface Servicos {

    Integer abastecer(List<Carro> filaCarros, List<Combustivel> combustiveis, List<Bomba> listaBombas);

    Carro incluirNovoVeiculo(List<Combustivel> combustiveis);

    Combustivel incluirTipoCombustivel();

    Bomba incluirBomba(List<Combustivel> listaCombustiveis);

    List<Carro> mudarCriterio(List<Carro> carros);

}
