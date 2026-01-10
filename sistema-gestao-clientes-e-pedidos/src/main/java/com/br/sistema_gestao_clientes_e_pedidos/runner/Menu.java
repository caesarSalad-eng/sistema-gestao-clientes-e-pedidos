package com.br.sistema_gestao_clientes_e_pedidos.runner;

import com.br.sistema_gestao_clientes_e_pedidos.service.ClienteService;
import com.br.sistema_gestao_clientes_e_pedidos.service.PedidoService;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

public class Menu implements CommandLineRunner {

    private final ClienteService clienteService;

    private final PedidoService pedidoService;

    private final Scanner sc = new Scanner(System.in);

    public Menu(ClienteService clienteService, PedidoService pedidoService){

        this.clienteService = clienteService;

        this.pedidoService = pedidoService;

    }

    @Override
    public void run(String... args){

        menuPrincipal();

    }

    public void menuPrincipal(){

        System.out.println("\n== MENU PRINCIPAL ==");

        System.out.println("\nDIGITE O SUBMENU QUE DEJESAR IR");

        System.out.println("\n1 - Gerenciar Clientes");
        System.out.println("\n2 - Gerenciar Pedidos");
        System.out.println("\n3 - Sair");
        int opcaoMenuPrincipal = sc.nextInt();
        sc.nextLine();

        switch (opcaoMenuPrincipal) {

            case 1:

                menuClientes();

                break;

            case 2:

                menuPedidos;

                break;

            case 3:

                System.out.println("Saindo...");

                System.exit(0);

                break;

        }

    }

    public void menuClientes(){

        do {

            System.out.println("\n== MENU CLIENTES ==");


        }while ();

    }
}
