package com.unicesumar.model.entities;


import com.unicesumar.service.paymentMethods.PaymentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Sale extends Entity{
    private User user;
    private List<Product> products;
    private PaymentType formaPagamento;
    private double  valorTotal;
    private LocalDateTime saleDate;

    public Sale(UUID uuid, User user, List<Product> products, PaymentType formaPagamento) {
        super(uuid);
        this.user = user;
        this.products = products;
        this.formaPagamento = formaPagamento;
        this.valorTotal = products.stream().mapToDouble(Product::getPrice).sum();
    }
    public Sale( User user, List<Product> products, PaymentType formaPagamento) {
        this.user = user;
        this.products = products;
        this.formaPagamento = formaPagamento;
        this.valorTotal = products.stream().mapToDouble(Product::getPrice).sum();
        this.saleDate = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public PaymentType getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(PaymentType formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        String produtosFormatados = this.products.stream().map(p -> "- " + p.getName()).collect(Collectors.joining("\n"));
        return "Resumo da venda: " +
                "\nCliente: " + this.user.getName() +
                "\nProdutos: " + produtosFormatados +
                "\nValor total: " + this.valorTotal +
                "\nPagamento: " + this.formaPagamento.getDescription();
    }
}
