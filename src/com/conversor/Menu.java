package com.conversor;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final ConversorService conversor;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.conversor = new ConversorService();
    }

    public void executar() {
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();
            processarOpcao(opcao);

        } while (opcao != 10);

        System.out.println("\nPrograma encerrado.");
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\nCONVERSOR DE MOEDAS");
        System.out.println("1. Dólar → Peso argentino");
        System.out.println("2. Peso argentino → Dólar");
        System.out.println("3. Dólar → Real brasileiro");
        System.out.println("4. Real brasileiro → Dólar");
        System.out.println("5. Dólar → Peso colombiano");
        System.out.println("6. Peso colombiano → Dólar");
        System.out.println("7. Euro → Real");
        System.out.println("8. Real → Euro");
        System.out.println("9. Ver histórico");
        System.out.println("10. Sair");
        System.out.println();
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> converter("USD", "ARS", "dólares");
            case 2 -> converter("ARS", "USD", "pesos argentinos");
            case 3 -> converter("USD", "BRL", "dólares");
            case 4 -> converter("BRL", "USD", "reais");
            case 5 -> converter("USD", "COP", "dólares");
            case 6 -> converter("COP", "USD", "pesos colombianos");
            case 7 -> converter("EUR", "BRL", "euros");
            case 8 -> converter("BRL", "EUR", "reais");
            case 9 -> conversor.getHistorico().exibir();
            case 10 -> System.out.println("\nObrigado por usar!");
            default -> System.out.println("\nOpção inválida.");
        }
    }

    private void converter(String origem, String destino, String nome) {
        System.out.printf("Digite o valor em %s: ", nome);
        double valor = lerDouble();

        if (valor <= 0) {
            System.out.println("Valor deve ser maior que zero.");
            return;
        }

        try {
            var resultado = conversor.converter(origem, destino, valor);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite novamente: ");
            }
        }
    }
}