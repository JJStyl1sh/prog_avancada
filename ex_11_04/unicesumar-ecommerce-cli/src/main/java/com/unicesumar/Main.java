package com.unicesumar;

import com.unicesumar.controller.ProductController;
import com.unicesumar.controller.UserController;

import com.unicesumar.model.repositories.ProductRepository;
import com.unicesumar.model.repositories.UserRepository;
import com.unicesumar.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductRepository listaDeProdutos = null;
        UserRepository listaDeUsuarios = null;

        Connection conn = null;
        
        // Parâmetros de conexão
        String url = "jdbc:sqlite:database.sqlite";

        // Tentativa de conexão
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                listaDeProdutos = new ProductRepository(conn);
                listaDeUsuarios = new UserRepository(conn);
            } else {
                System.out.println("Falha na conexão.");
                System.exit(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            System.exit(1);
        }

        View view = new View();
        UserController userController = new UserController(listaDeUsuarios, view);
        ProductController productController = new ProductController(listaDeProdutos, view);

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            option = view.showMenu();
            switch (option) {
                case 1:
                    System.out.println("Cadastrar Produto");
                    productController.cadastrarProduto();
                    break;
                case 2:
                    System.out.println("Listar Produtos");
                    productController.listarProduto();
                    break;
                case 3:
                    System.out.println("Cadastrar Usuário");
                    userController.cadastrarUsuario();
                    break;
                case 4:
                    System.out.println("Listar Usuários");
                    userController.listarUsuarios();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");

            }

        } while (option != 5);

        scanner.close();
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
