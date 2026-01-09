package com.br.sistema_gestao_clientes_e_pedidos.service;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import com.br.sistema_gestao_clientes_e_pedidos.model.Pedido;
import com.br.sistema_gestao_clientes_e_pedidos.model.StatusPedido;
import com.br.sistema_gestao_clientes_e_pedidos.repository.ClienteRepository;
import com.br.sistema_gestao_clientes_e_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository){

        this.pedidoRepository = pedidoRepository;

        this.clienteRepository = clienteRepository;

    }

    public String criarPedido(Long idCliente, String descricao, Double valorTotal){

        if(idCliente == null){

            return "Id do Cliente inválido. Tente Novamente";

        }

         Optional<Cliente> buscarClienteExistente = clienteRepository.findById(idCliente);

         if (buscarClienteExistente.isEmpty()){

             return "Cliente não encontrado. Tente Novamente";

         }

         Cliente cliente = buscarClienteExistente.get();

         List<Pedido> buscarPedidoCliente = pedidoRepository.findByCliente(cliente);

         if (buscarPedidoCliente.isEmpty()){

             return "Cliente ainda não tem pedidos ATIVOS. Está apto para realizar pedido";

         }

         for (Pedido pedido : buscarPedidoCliente){

             if (pedido.getStatusPedido() == StatusPedido.ATRASADO || pedido.getStatusPedido() == StatusPedido.ATIVO){

                 return "Cliente tem pedidos ATRASADOS ou ATIVOS não foi possível criar um novo pedido";

             }

         }

         if (valorTotal <= 0){

            return "Valor inválido. Tente Novamente";

         }

         Pedido criacaoPedido = new Pedido();

         criacaoPedido.setCliente(cliente);
         criacaoPedido.setValorTotal(valorTotal);
         criacaoPedido.setDescricao(descricao);
         criacaoPedido.setDataPedido(new Date());
         criacaoPedido.setStatusPedido(StatusPedido.ATIVO);

         pedidoRepository.save(criacaoPedido);

         return "Pedido criado com Sucesso!!";

    }

    public String listarPedidos(){

        List<Pedido> listaPedidos = pedidoRepository.findAll();

        if (listaPedidos.isEmpty()){

            return "Não tem pedidos ATIVOS no momento";

        }

        StringBuilder resultadoListarPedidos = new StringBuilder();

        for (Pedido pedido : listaPedidos){

            resultadoListarPedidos.append("Id: ").append(pedido.getId());
            resultadoListarPedidos.append("Cliente Vinculado: ").append(pedido.getCliente());
            resultadoListarPedidos.append("Status: ").append(pedido.getStatusPedido());
            resultadoListarPedidos.append("Valor: ").append(pedido.getValorTotal());
            resultadoListarPedidos.append("Datas: ").append(pedido.getDataPedido());

        }

        return resultadoListarPedidos.toString();

    }

    public String listarPedidosPorStatus(StatusPedido statusPedido){

        List<Pedido> listarPorStatus = pedidoRepository.findByStatus(statusPedido);

        if (listarPorStatus.isEmpty()){

            return "A lista está vazia";

        }

        StringBuilder resultadoListarPorStatus = new StringBuilder();

        for (Pedido pedidosListarPorStatus : listarPorStatus){

            resultadoListarPorStatus.append("Id: ").append(pedidosListarPorStatus.getId());
            resultadoListarPorStatus.append("Cliente Vinculado: ").append(pedidosListarPorStatus.getCliente());
            resultadoListarPorStatus.append("Status: ").append(pedidosListarPorStatus.getStatusPedido());
            resultadoListarPorStatus.append("Valor: ").append(pedidosListarPorStatus.getValorTotal());
            resultadoListarPorStatus.append("Datas: ").append(pedidosListarPorStatus.getDataPedido());

        }

        return resultadoListarPorStatus.toString();
    }

    public String listarPedidosPorCliente(Long idCliente){

        if (idCliente == null){

            return "Id inválido. Tente Novamente";

        }

        List<Pedido> listarPedidosPorCliente = pedidoRepository.findByCliente(idCliente);

        StringBuilder resultadoListaPorCliente = new StringBuilder();

        for (Pedido pedidoListaPorCliente : listarPedidosPorCliente){

            resultadoListaPorCliente.append("Id: ").append(pedidoListaPorCliente.getId());
            resultadoListaPorCliente.append("Cliente Vinculado: ").append(pedidoListaPorCliente.getCliente());
            resultadoListaPorCliente.append("Status: ").append(pedidoListaPorCliente.getStatusPedido());
            resultadoListaPorCliente.append("Valor: ").append(pedidoListaPorCliente.getValorTotal());
            resultadoListaPorCliente.append("Datas: ").append(pedidoListaPorCliente.getDataPedido());

        }

        return resultadoListaPorCliente.toString();

    }

    public String buscarPedidoPorId(Long idPedido){

        if (idPedido == null){

            return "Id inválido. Tente Novamente";

        }

        Optional<Pedido> buscarPedidoPorId = pedidoRepository.findById(idPedido);

        if (buscarPedidoPorId.isEmpty()){

            return "Não existe pedido com esse Id ";

        }


        Pedido pedidoBuscaPorId = buscarPedidoPorId.get();

        StringBuilder resultadoBuscaPedidoPorId = new StringBuilder();

        resultadoBuscaPedidoPorId.append("Id: ").append(pedidoBuscaPorId.getId());
        resultadoBuscaPedidoPorId.append("Cliente Vinculado: ").append(pedidoBuscaPorId.getCliente());
        resultadoBuscaPedidoPorId.append("Status: ").append(pedidoBuscaPorId.getStatusPedido());
        resultadoBuscaPedidoPorId.append("Valor: ").append(pedidoBuscaPorId.getValorTotal());
        resultadoBuscaPedidoPorId.append("Datas: ").append(pedidoBuscaPorId.getDataPedido());

       return resultadoBuscaPedidoPorId.toString();

    }

    public String atualizarPedido(Long idPedido, Cliente novoCliente, Double novoValorTotal, Date novaData, String novaDescricao){

        if (idPedido == null){

            return "Id inválido. Tente Novamente";

        }

        Optional<Pedido> buscarPedidoAtualizar = pedidoRepository.findById(idPedido);

        if (buscarPedidoAtualizar.isEmpty()){

            return "Pedido não encontrado";

        }

        Pedido pedidoAtualizar = buscarPedidoAtualizar.get();

        if (pedidoAtualizar.getStatusPedido() == StatusPedido.FINALIZADO || pedidoAtualizar.getStatusPedido() ==  StatusPedido.CANCELADO){

            return "Não é possível atualizar o pedido porque ele já foi FINALIZADO ou foi CANCELADO";

        }

        if (novoCliente == null){

            return "Novo Cliente inválido.Tente Novamente";

        }

        if (novoValorTotal < 0){

            return "Novo Valor inválido. Tente Novamente";

        }

        if (novaData == null){

            return "Nova Data inválida. Tente Novamente";

        }

        if (novaDescricao == null){

            return "Nova Descrição inválida. Tente Novamente";

        }

        pedidoAtualizar.setCliente(novoCliente);
        pedidoAtualizar.setValorTotal(novoValorTotal);
        pedidoAtualizar.setDataPedido(novaData);
        pedidoAtualizar.setDescricao(novaDescricao);

        return "Pedido atualizado com Sucesso!!";

    }

    public String alterarStatusPedido(StatusPedido statusPedido){

        


    }


}
