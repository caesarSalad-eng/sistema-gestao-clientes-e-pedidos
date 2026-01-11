package com.br.sistema_gestao_clientes_e_pedidos.runner;

import com.br.sistema_gestao_clientes_e_pedidos.model.StatusPedido;
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

        while (true){

            menuPrincipal();

        }
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

                menuPedidos();

                break;

            case 3:

                System.out.println("Saindo...");

                System.exit(0);

                break;

            default:

                System.out.println("Opção inválida. Tente Novamente");

        }

    }

    public void menuClientes(){

        int opcaoMenuCliente;

        do {

            System.out.println("\n== MENU CLIENTES ==");

            System.out.println("\n1 - Criar Cliente");
            System.out.println("\n2 - Listar Clientes");
            System.out.println("\n3 - Buscar Cliente por ID");
            System.out.println("\n4 - Buscar Cliente por Email");
            System.out.println("\n5 - Atualizar Cliente");
            System.out.println("\n6 - Deletar Cliente");
            System.out.println("\n 0 - Voltar ao Menu Principal");

             opcaoMenuCliente = sc.nextInt();
            sc.nextLine();

            switch (opcaoMenuCliente){

                case 1:

                    criarCliente();

                break;

                case 2:

                    listarClientes();

                    break;

                case 3:

                    buscarClientePorId();

                    break;

                case 4:

                    buscarClientePorEmail();

                    break;

                case 5:

                    atualizarCliente();

                    break;

                case 6:

                    deletarCliente();

                    break;

                default:

                    System.out.println("Opção inválida. Tente Novamente");

            }

        }while (opcaoMenuCliente != 0);

    }

    public void criarCliente(){

        String nome, email, telefone;

        System.out.println("\n== CRIAÇÃO DE CLIENTES ==");

        System.out.println("\n Digite seu Nome Completo: ");
        nome = sc.nextLine();

        System.out.println("\n Digite seu Email: ");
        email = sc.nextLine();

        System.out.println("\n Digite seu Telefone: ");
        telefone = sc.nextLine();

       String resultadoFinalCriarCliente = clienteService.cadastroCliente(nome, email, telefone);

        System.out.println(resultadoFinalCriarCliente);

    }

    public void listarClientes(){

        System.out.println("\n== LISTA DE CLIENTES CADASTRADOS ==");

        String resultadoFinalListaClientes = clienteService.listarClientes();

        System.out.println(resultadoFinalListaClientes);

    }

    public void buscarClientePorId(){

        Long idClienteBusca;

        System.out.println("\n== BUSCA DE CLIENTE POR ID ==");

        System.out.println("Digite o ID do Cliente que deseja procurar: ");
        idClienteBusca = sc.nextLong();
        sc.nextLine();

        String resultadoFinalBuscaClientePorId = clienteService.buscaClientePorID(idClienteBusca);

        System.out.println(resultadoFinalBuscaClientePorId);

    }

    public void buscarClientePorEmail(){

        String emailBusca;

        System.out.println("\n == BUSCAR CLIENTE POR EMAIL");

        System.out.println("\nDigite o Email do Cliente que deseja buscar");
        emailBusca = sc.nextLine();

        String resultadoFinalBuscaClientePorEmail = clienteService.buscaClientePorEmail(emailBusca);

        System.out.println(resultadoFinalBuscaClientePorEmail);

    }

    public void atualizarCliente(){

        Long idClienteAtualizar;

        String novoNome, novoEmail, novoTelefone;

        System.out.println("\n == ATUALIZAÇÃO DE CLIENTE");

        System.out.println("\nDigite o ID do Cliente que deseja atualizar:");
        idClienteAtualizar = sc.nextLong();
        sc.nextLine();

        System.out.println("\nDigite o Novo Nome que deseja cadastrar: ");
        novoNome = sc.nextLine();

        System.out.println("\nDigite o Novo Email que deseja cadastrar: ");
        novoEmail = sc.nextLine();

        System.out.println("\nDigite o Novo Telefone que deseja cadastrar: ");
        novoTelefone = sc.nextLine();

        String resultadoFinalAtualizarCliente = clienteService.atualizarCliente(idClienteAtualizar, novoNome, novoEmail, novoTelefone);

        System.out.println(resultadoFinalAtualizarCliente);

    }

    public void deletarCliente(){

        Long idClienteExclusao;

        System.out.println("\n == EXCLUSÃO DE CLIENTE");

        System.out.println("\n Digite o ID do Cliente que deseja excluir: ");
        idClienteExclusao = sc.nextLong();
        sc.nextLine();

       String resultadoFinalDeletarCliente =  clienteService.deletarCliente(idClienteExclusao);

        System.out.println(resultadoFinalDeletarCliente);

    }

    public void menuPedidos(){

        int opcaoMenuPedidos;

        do {

            System.out.println("\n== MENU PEDIDOS ==");

            System.out.println("\n1 - Criar Pedido");
            System.out.println("\n2 - Listar Todos os Pedidos");
            System.out.println("\n3 - Listar Pedidos por Status");
            System.out.println("\n4 - Listar Pedidos por Cliente");
            System.out.println("\n5 - Buscar Pedido por ID");
            System.out.println("\n6 - Atualizar Pedido");
            System.out.println("\n7 - Alterar Status do Pedido");
            System.out.println("\n8 - Deletar Pedido");
            System.out.println("\n0 - Voltar ao Menu Principal");

            opcaoMenuPedidos = sc.nextInt();
            sc.nextLine();

            switch (opcaoMenuPedidos){

                case 1:

                    criarPedido();

                    break;

                case 2:

                    listarTodosOsPedidos();

                    break;

                case 3:

                    listarPedidosPorStatus();

                    break;

                case 4:

                    listarPedidosPorCliente();

                    break;

                case 5:

                    buscarPedidoPorId();

                    break;

                case 6:

                    atualizarPedido();

                    break;

                case 7:

                    alterarStatusPedido();

                    break;

                case 8:

                    deletarPedido();

                    break;

                default:

                    System.out.println("Opção inválida. Tente Novamente");

            }

        }while (opcaoMenuPedidos != 0);

    }

    public void criarPedido(){

        long idClientePedido;

        String descricaoPedido;

        double valorTotalPedido;

        System.out.println("\n== CRIAÇÃO DE PEDIDO ==");

        System.out.println("Digite o ID do CLiente que fez o Pedido: ");
        idClientePedido = sc.nextLong();
        sc.nextLine();

        System.out.println("\nDigite a descrição do Pedido: ");
        descricaoPedido = sc.nextLine();

        System.out.println("\nDigite o Valor Total do Pedido: ");
        valorTotalPedido = sc.nextDouble();
        sc.nextLine();

        String resultadoFinalCriarPedido = pedidoService.criarPedido(idClientePedido, descricaoPedido, valorTotalPedido);

        System.out.println(resultadoFinalCriarPedido);

    }

    public void listarTodosOsPedidos(){

        System.out.println("\n== LISTA DE TODOS OS PEDIDOS ==");

       String resultadoFinalListaTodosPedidos =  pedidoService.listarPedidos();

        System.out.println(resultadoFinalListaTodosPedidos);

    }

    public void listarPedidosPorStatus(){

        StatusPedido listaPedidoPorStatus;

        System.out.println("\n== LISTA DE PEDIDOS POR STATUS ==");

        System.out.println("Digite o Status dos Pedidos que dejesa listar: ");
        listaPedidoPorStatus = StatusPedido.valueOf(sc.nextLine());

       String resultadoFinalListarPedidoStatus = pedidoService.listarPedidosPorStatus(listaPedidoPorStatus);

        System.out.println(resultadoFinalListarPedidoStatus);
    }

    public void listarPedidosPorCliente(){

        long idClienteListarPedidos;

        System.out.println("\n== LISTAR PEDIDOS POR CLIENTES ==");

        System.out.println("Digite o Nome do Cliente que deseja listar os Pedidos: ");
        idClienteListarPedidos = sc.nextLong();
        sc.nextLine();

        String resultadoFinalListarPedidosPorCliente = pedidoService.listarPedidosPorCliente(idClienteListarPedidos);

        System.out.println(resultadoFinalListarPedidosPorCliente);

    }

    public void buscarPedidoPorId(){

        long idPedidoBusca;

        System.out.println("\n== BUSCAR PEDIDO POR ID ==");

        System.out.println("\nDigite o ID do Pedido que deseja buscar: ");
        idPedidoBusca = sc.nextLong();
        sc.nextLine();

       String resultadoFinalBuscarPedidoPorId = pedidoService.buscarPedidoPorId(idPedidoBusca);

        System.out.println(resultadoFinalBuscarPedidoPorId);

    }

    public void atualizarPedido(){

        long idPedidoAtualizar;

        String novoCliente;

        double novoValor;

        String novaDescricao;

        System.out.println("\n== ATUALIZAÇÃO DE PEDIDO ==");

        System.out.println("\nDigite o ID do Pedido que deseja atualizar: ");
        idPedidoAtualizar = sc.nextLong();
        sc.nextLine();

        System.out.println("\nDigite o nome do Novo Cliente: ");
        novoCliente = sc.nextLine();

        System.out.println("\nDigite o Novo Valor: ");
        novoValor = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nDigite a Nova Descrição: ");
        novaDescricao = sc.nextLine();


        String resultadoFinalAtualizarPedido = pedidoService.atualizarPedido(idPedidoAtualizar, novoCliente, novoValor, novaDescricao);

        System.out.println(resultadoFinalAtualizarPedido);

    }

    public void alterarStatusPedido(){

        Long idPedidoAlterarStatus;

        StatusPedido alterarStatusPedido;

        System.out.println("\n== ALTERAR STATUS PEDIDO ==");

        System.out.println("\n Digite  o ID do Pedido que deseja alterar o Status: ");
        idPedidoAlterarStatus = sc.nextLong();
        sc.nextLine();

        System.out.println("\nDigite o novo Status");
        alterarStatusPedido = StatusPedido.valueOf(sc.nextLine());

        String resultaadoFinalAlterarStatusPedido = pedidoService.alterarStatusPedido(idPedidoAlterarStatus, alterarStatusPedido);

        System.out.println(resultaadoFinalAlterarStatusPedido);

    }

    public void deletarPedido(){

        Long idPedidoExclusao;

        System.out.println("\n== EXCLUSÃO DE PEDIDO ==");

        System.out.println("Digite o ID do Pedido que deseja deletar: ");
        idPedidoExclusao = sc.nextLong();
        sc.nextLine();

       String resultadoFinalDeletarPedido = pedidoService.deletarPedido(idPedidoExclusao);

        System.out.println(resultadoFinalDeletarPedido);

    }
}
