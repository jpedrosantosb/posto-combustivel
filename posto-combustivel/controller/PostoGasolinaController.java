package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Bomba;
import model.Carro;
import model.Combustivel;
import model.TipoCombustivel;
import service.PostoGasolinaService;

public class PostoGasolinaController {

    public static void menu(){
        System.out.println("\n\n\n\tBem vindo ao Posto de Gasolina, escolha uma opção:");
        System.out.println("1. Incluir novo modelo de veículo.");
        System.out.println("2. Incluir novo tipo de combustível.");
        System.out.println("3. Incluir nova bomba de combustível.");
        System.out.println("4. Mudar critério de enfileiramento.");
        System.out.println("5. Iniciar abastecimento.");
        System.out.println("0. Sair.");
        System.out.println("Opcao: ");
    }

    public static void main(String[] args) {

        PostoGasolinaService postoGasolinaService = new PostoGasolinaService();

        TipoCombustivel tipoGasolina = new TipoCombustivel();
		tipoGasolina.setNomeCombustivel("GASOLINA");
		
		TipoCombustivel tipoEtanol = new TipoCombustivel();
		tipoEtanol.setNomeCombustivel("ETANOL");
		
		List<Carro> listaCarros = new ArrayList<>();
		Carro carro1 = new Carro();
		carro1.setModelo("FIAT-UNO");
		carro1.setPlaca("JGG-7389");
		carro1.setCapacidade(48);
		carro1.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro2 = new Carro();
		carro2.setModelo("FORD-KA");
		carro2.setPlaca("JGF-8573");
		carro2.setCapacidade(55);
		carro2.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro3 = new Carro();
		carro3.setModelo("AUDI-A1");
		carro3.setPlaca("JGE-0620");
		carro3.setCapacidade(45);
		carro3.setCombustiveis(Arrays.asList(tipoGasolina));
		
		Carro carro4 = new Carro();
		carro4.setModelo("CITROEN-C3");
		carro4.setPlaca("JWM-1235");
		carro4.setCapacidade(47);
		carro4.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro5 = new Carro();
		carro5.setModelo("RENAULT-CLIO");
		carro5.setPlaca("KRM-6771");
		carro5.setCapacidade(50);
		carro5.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro6 = new Carro();
		carro6.setModelo("AUDI-A1");
		carro6.setPlaca("JGE-4583");
		carro6.setCapacidade(45);
		carro6.setCombustiveis(Arrays.asList(tipoGasolina));
		
		Carro carro7 = new Carro();
		carro7.setModelo("FORD-KA");
		carro7.setPlaca("JGE-9393");
		carro7.setCapacidade(55);
		carro7.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro8 = new Carro();
		carro8.setModelo("RENAULT-CLIO");
		carro8.setPlaca("JGE-0611");
		carro8.setCapacidade(50);
		carro8.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro9 = new Carro();
		carro9.setModelo("CITROEN-C3;JHM-7491;47");
		carro9.setPlaca("JHM-7491");
		carro9.setCapacidade(47);
		carro9.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro10 = new Carro();
		carro10.setModelo("FORD-KA");
		carro10.setPlaca("JGM-4773");
		carro10.setCapacidade(55);
		carro10.setCombustiveis(Arrays.asList(tipoEtanol, tipoGasolina));
		
		Carro carro11 = new Carro();
		carro11.setModelo("AUDI-A4");
		carro11.setPlaca("JMM-7513");
		carro11.setCapacidade(65);
		carro11.setCombustiveis(Arrays.asList(tipoGasolina));
		
		listaCarros.add(carro1);
		listaCarros.add(carro2);
		listaCarros.add(carro3);
		listaCarros.add(carro4);
		listaCarros.add(carro5);
		listaCarros.add(carro6);
		listaCarros.add(carro7);
		listaCarros.add(carro8);
		listaCarros.add(carro9);
		listaCarros.add(carro10);
		listaCarros.add(carro11);
		
        List<Combustivel> listaCombustiveis = getCombustiveisDefault();

        List<Bomba> listaBombas = new ArrayList<>();

        int opcao;
        Scanner entrada = new Scanner(System.in);

        do{
            menu();
            opcao = entrada.nextInt();

            switch(opcao){
                case 1:
                    listaCarros.add(postoGasolinaService.incluirNovoVeiculo(listaCombustiveis));
                    System.out.println("Ordem de carros na fila:");
                    System.out.println(listaCarros);
                    System.out.println("-------------------------------");
                    break;

                case 2:
                    Combustivel combustivel = postoGasolinaService.incluirTipoCombustivel();
                    listaCombustiveis.add(combustivel);
                    break;

                case 3:
                    Bomba bomba = postoGasolinaService.incluirBomba(listaCombustiveis);
                    listaBombas.add(bomba);
                    break;

                case 4:
                    listaCarros = postoGasolinaService.mudarCriterio(listaCarros);
                    System.out.println("Nova ordem de carros na fila:");
                    System.out.println(listaCarros);
                    System.out.println("-------------------------------");
                    break;

                case 5:
                    postoGasolinaService.abastecer(listaCarros, listaCombustiveis, listaBombas);
                    listaBombas.forEach(bomb -> {
                        System.out.println("Total abastecido de " + bomb.getCombustivel().getNome() +  " na " + bomb.getNome() + ": " + bomb.getTotalizador() + " litros");
                    });

                    break;
                default:
                    System.out.println("Op��o invalida.");
            }
        } while(opcao != 0);
    }

    private static List<Combustivel> getCombustiveisDefault() {
        List<Combustivel> listaCombustiveis = new ArrayList<>();
        Combustivel combustivel1 = new Combustivel();
        combustivel1.setNome("GASOLINA");
        combustivel1.setPreco(2.90);
        combustivel1.setCodCombustivel(1);

        Combustivel combustivel2 = new Combustivel();
        combustivel2.setNome("ETANOL");
        combustivel2.setPreco(2.27);
        combustivel2.setCodCombustivel(2);

        listaCombustiveis.add(combustivel1);
        listaCombustiveis.add(combustivel2);
        return listaCombustiveis;
    }
}
