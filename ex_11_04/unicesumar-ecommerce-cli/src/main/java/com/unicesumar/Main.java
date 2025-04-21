package com.unicesumar;

import com.unicesumar.controller.ProductController;
import com.unicesumar.controller.SaleController;
import com.unicesumar.controller.UserController;

import com.unicesumar.model.repositories.ProductRepository;
import com.unicesumar.model.repositories.SaleRepository;
import com.unicesumar.model.repositories.UserRepository;
import com.unicesumar.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductRepository listaDeProdutos = null;
        UserRepository listaDeUsuarios = null;
        SaleRepository listaDeVendas = null;

        Connection conn = null;

// No Main.java, altere a URL:
        String url = "jdbc:sqlite:database.sqlite";

        // Tentativa de conexão
        try {
            conn = DriverManager.getConnection(url);
            executarScriptSQL(conn, "init.sql");

            if (conn != null) {
                listaDeProdutos = new ProductRepository(conn);
                listaDeUsuarios = new UserRepository(conn);
                listaDeVendas = new SaleRepository(conn);
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
        SaleController saleController = new SaleController(listaDeUsuarios, listaDeProdutos, listaDeVendas, view);

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
                    System.out.println("Cadastrando Venda");
                    saleController.carregarVenda();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");

            }

        } while (option != 6);

        scanner.close();
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void executarScriptSQL(Connection conn, String nomeArquivo) {
        try (InputStream input = Main.class.getResourceAsStream("/" + nomeArquivo)) {
            if (input == null) {
                throw new RuntimeException("Arquivo não encontrado: init.sql");
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(input, StandardCharsets.UTF_8)
            );

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            for (String statement : sql.toString().split(";")) {
                if (!statement.trim().isEmpty()) {
                    conn.createStatement().execute(statement);
                }
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Erro no init.sql: " + e.getMessage());
        }
    }
}