package com.unicesumar.view;

import com.unicesumar.model.entities.Product;
import com.unicesumar.model.entities.User;

import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public int showMenu(){
        System.out.println("\n---MENU---");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listas Produtos");
        System.out.println("3 - Cadastrar Usuário");
        System.out.println("4 - Listar Usuários");
        System.out.println("5 - Sair");
        System.out.println("Escolha uma opção: ");
        return scanner.nextInt();
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
}
