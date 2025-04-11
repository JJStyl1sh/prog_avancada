package com.unicesumar.controller;

import com.unicesumar.model.entities.User;
import com.unicesumar.model.repositories.UserRepository;
import com.unicesumar.view.View;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private Scanner scanner = new Scanner(System.in);
    private UserRepository repository;
    private View view;

    public UserController(UserRepository repository, View view) {
        this.repository = repository;
        this.view = view;
    }

    public void cadastrarUsuario(){
        System.out.println("Digite o nome do usuario: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o email do usuario: ");
        String email = scanner.nextLine();

        System.out.println("Digite a senha do usuario: ");
        String senha = scanner.nextLine();

        User user = new User(nome, email, senha);
        repository.save(user);
    }

    public void listarUsuarios(){
        List<User> users = repository.findAll();
        view.showUsuarios(users);
    }
}
