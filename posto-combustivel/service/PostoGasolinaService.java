package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import interfaces.Servicos;
import model.Bomba;
import model.Carro;
import model.Combustivel;
import model.TipoCombustivel;

public class PostoGasolinaService implements Servicos {

    @Override
    public Integer abastecer(List<Carro> filaCarros, List<Combustivel> combustiveis, List<Bomba> listaBombas) {

        zerarTotalizadores(listaBombas);

        filaCarros.forEach(carro -> {
            List<Combustivel> listaNovaCombustivel = new ArrayList<>();
            carro.getCombustiveis().forEach(tipoCombustivel -> {
                combustiveis.forEach(combustivel -> {
                    if(tipoCombustivel.getNomeCombustivel().equals(combustivel.getNome())){
                        listaNovaCombustivel.add(combustivel);
                    }
                });
            });

            if(carro.getCombustiveis().size() > 1){

                List<Combustivel> combustivelFiltrado = listaNovaCombustivel.stream()
                    .sorted(Comparator.comparing(Combustivel::getPreco)).collect(Collectors.toList());

                Optional<Combustivel> combu = combustivelFiltrado.stream().findFirst();

                listaBombas.forEach(bomba -> {
                    if(bomba.getCombustivel().getNome().equals(combu.get().getNome())){
                        bomba.totalizadorBomba(carro.getCapacidade());
                        System.out.println(carro.getCapacidade()/bomba.getVelAbastecimentoMin() + " minutos " + "Veículo modelo " +  carro.getModelo() + " placa " + carro.getPlaca() + " foi abastecido com " + carro.getCapacidade() + " de " + bomba.getCombustivel().getNome());
                    }
                });
            }else{
                listaBombas.forEach(bomba -> {

                    AtomicReference<String> nomeCombustivel = new AtomicReference<>(new String());

                    carro.getCombustiveis().forEach(carroComb -> {
                        nomeCombustivel.set(carroComb.getNomeCombustivel());
                    });

                    if(bomba.getCombustivel().getNome() == nomeCombustivel.get()){
                        bomba.totalizadorBomba(carro.getCapacidade());
                        System.out.println(carro.getCapacidade()/bomba.getVelAbastecimentoMin() + " minutos " + "Veículo modelo " +  carro.getModelo() + " placa " + carro.getPlaca() + " foi abastecido com " + carro.getCapacidade() + " litros de " + bomba.getCombustivel().getNome());
                    }
                });
            }
        });

        return null;
    }

    private void zerarTotalizadores(List<Bomba> listaBombas) {
        listaBombas.forEach(bomba -> {
            bomba.setTotalizador(0);
        });
    }

    @Override
    public Carro incluirNovoVeiculo(List<Combustivel> combustiveis) {

        Carro carro = new Carro();

        Scanner entrada = new Scanner(System.in);

        System.out.println("1. Digite a placa: ");
        String placa = entrada.next();

        System.out.println("2. Digite o Modelo: ");
        String modelo = entrada.next();

        Integer opcao = 0;
        List<TipoCombustivel> tipoCombustiveis = new ArrayList<>();
        do{
            System.out.println("3. Digite o código do tipo do combustível: ");
            System.out.println(combustiveis);
            Integer tipoCombustivel = entrada.nextInt();

            AtomicReference<String> nomeCombustivel = new AtomicReference<>(new String());

            combustiveis.forEach(combustivel -> {
                if(combustivel.getCodCombustivel().equals(tipoCombustivel)){
                    nomeCombustivel.set(combustivel.getNome());
                }
            });

            TipoCombustivel tipoCombustivel1 = new TipoCombustivel(nomeCombustivel.get());

            tipoCombustiveis.add(tipoCombustivel1);
            System.out.println("3. Deseja adicionar outro tipo de combustível? 1 - SIM 2 - NÃO");
            opcao = entrada.nextInt();

        }while (opcao != 2);

        System.out.println("4. Digite a capacidade do tanque: ");
        Integer capacidade = entrada.nextInt();

        carro.setPlaca(placa);
        carro.setModelo(modelo);
        carro.setCombustiveis(tipoCombustiveis);
        carro.setCapacidade(capacidade);

        return carro;
    }

    @Override
    public Combustivel incluirTipoCombustivel() {

        Combustivel combustivel = new Combustivel();
        Scanner entrada = new Scanner(System.in);

        System.out.println("1. Digite a nome: ");
        String nome = entrada.next();


        System.out.println("1. Digite a preço: ");
        Double preco = entrada.nextDouble();


        System.out.println("1. Digite a código: ");
        Integer codigo = entrada.nextInt();

        combustivel.setNome(nome);
        combustivel.setPreco(preco);
        combustivel.setCodCombustivel(codigo);

        return combustivel;
    }

    @Override
    public Bomba incluirBomba(List<Combustivel> listaCombustiveis) {

        Bomba bomba = new Bomba();
        Scanner entrada = new Scanner(System.in);

        System.out.println("1. Digite a nome da bomba: ");
        String nome = entrada.next();

        System.out.println("2. Digite a velocidade de abastecimento por minuto: ");
        Double velPorMin = entrada.nextDouble();

        System.out.println("3. Digite o código do combustível desejado: ");
        System.out.println(listaCombustiveis);
        Integer codigo = entrada.nextInt();

        listaCombustiveis.forEach(combustivel -> {
            if(Objects.equals(combustivel.getCodCombustivel(), codigo)){
                bomba.setCombustivel(combustivel);
            }
        });

        bomba.setNome(nome);
        bomba.setVelAbastecimentoMin(velPorMin);

        return bomba;
    }

    @Override
    public List<Carro> mudarCriterio(List<Carro> carros) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("1. Escolha a ordenação desejada: 1 - CAPACIDADE TANQUE");
        Integer resposta = entrada.nextInt();

        if(resposta == 1){

            carros = carros.stream()
                .sorted(Comparator.comparing(Carro::getCapacidade)).collect(Collectors.toList());

        }else if(resposta == 2){
            carros = carros.stream()
                .sorted(Comparator.comparing(Carro::getPlaca)).collect(Collectors.toList());
        }else{
            System.out.println("RESPOSTA INCORRETA");
        }

        return carros;
    }
}
