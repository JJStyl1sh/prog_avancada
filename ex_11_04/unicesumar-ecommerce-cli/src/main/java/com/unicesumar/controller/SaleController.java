package com.unicesumar.controller;

import com.unicesumar.model.entities.Product;
import com.unicesumar.model.entities.Sale;
import com.unicesumar.model.entities.User;
import com.unicesumar.model.repositories.ProductRepository;
import com.unicesumar.model.repositories.SaleRepository;
import com.unicesumar.model.repositories.UserRepository;
import com.unicesumar.service.paymentManagers.PaymentMethodFactory;
import com.unicesumar.service.paymentMethods.PaymentMethod;
import com.unicesumar.service.paymentMethods.PaymentType;
import com.unicesumar.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaleController {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private SaleRepository saleRepository;
    private View view;

    public SaleController(UserRepository userRepository, ProductRepository productRepository, SaleRepository saleRepository, View view) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.view = view;
    }

    public void carregarVenda() {
        try {
            String email = view.getEmail();
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            List<Product> todosProdutos = productRepository.findAll();
            List<Integer> numeros = view.getNumerosProdutos(todosProdutos);

            List<UUID> produtosIds = new ArrayList<>();
            for (int num : numeros) {
                int index = num - 1;
                if (index < 0 || index >= todosProdutos.size()) {
                    throw new RuntimeException("Número inválido: " + num);
                }
                produtosIds.add(todosProdutos.get(index).getUuid());
            }

            List<Product> produtos = productRepository.findByIds(produtosIds);
            PaymentType paymentType = view.getPaymentType();
            PaymentMethod paymentMethod = PaymentMethodFactory.create(paymentType);


            Sale sale = new Sale(user, produtos, paymentType);
            saleRepository.save(sale);

            System.out.println(sale);

        } catch (RuntimeException e) {
            view.showMensagem("Erro: " + e.getMessage());
        }
    }
}
