package com.unicesumar.view;

import com.unicesumar.model.entities.Product;
import com.unicesumar.model.entities.User;
import com.unicesumar.service.paymentMethods.PaymentType;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public int showMenu(){
        System.out.println("\n---MENU---");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listas Produtos");
        System.out.println("3 - Cadastrar Usuário");
        System.out.println("4 - Listar Usuários");
        System.out.println("5 - Cadastrar Venda");
        System.out.println("6 - Sair");
        System.out.println("Escolha uma opção: ");
        int opc = scanner.nextInt();
        scanner.nextLine();
        return opc;
    }

    public void showProdutos(List<Product> products){
        System.out.println("Produtos: ");
        products.forEach(System.out::println);
    }

    public void showUsuarios(List<User> users){
        System.out.println("Usuarios: ");
        users.forEach(System.out::println);
    }

    public void showMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public String getEmail(){
        System.out.println("Digite o email do usuario: ");
        return scanner.nextLine();
    }

    public List<UUID> getProdutosIds(){
        System.out.println("Digite o codigo dos produtos, separados por ',' ");
        String ids[] = scanner.nextLine().split(",");
        return Arrays.stream(ids).map(String::trim).map(UUID::fromString).collect(Collectors.toList());
    }
    public PaymentType getPaymentType() {
        System.out.println("Forma de pagamento (PIX, BOLETO, CARTAO):");
        String input = scanner.nextLine().toUpperCase();
        try {
            return PaymentType.valueOf(input);
        } catch (IllegalArgumentException e) {
            showMensagem("Forma de pagamento inválida.");
            return null;
        }
    }
    public List<Integer> getNumerosProdutos(List<Product> produtos) {
        System.out.println("\n--- Produtos Disponíveis ---");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + " - " + produtos.get(i).getName());
        }

        System.out.println("Digite os números dos produtos separados por virgula (ex: 1,3): ");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
