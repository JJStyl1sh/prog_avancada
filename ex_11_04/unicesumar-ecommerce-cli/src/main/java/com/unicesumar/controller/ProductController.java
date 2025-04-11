package com.unicesumar.controller;

import com.unicesumar.model.entities.Product;
import com.unicesumar.model.repositories.ProductRepository;
import com.unicesumar.view.View;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private Scanner scanner = new Scanner(System.in);
    private ProductRepository productRepository;
    private View view;

    public ProductController(ProductRepository productRepository, View view) {
        this.productRepository = productRepository;
        this.view = view;
    }

    public void cadastrarProduto(){
        System.out.println("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.println("Digte o preço do produto: ");
        double preco = scanner.nextDouble();

        Product product = new Product(nome, preco);
        productRepository.save(product);
        scanner.nextLine();
    }

    public void listarProduto(){
        List<Product> products = productRepository.findAll();
        view.showProdutos(products);
    }
}
