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
        scanner.nextLine();
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
}
