package com.br.sistema_gestao_clientes_e_pedidos.service;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import com.br.sistema_gestao_clientes_e_pedidos.model.Pedido;
import com.br.sistema_gestao_clientes_e_pedidos.model.StatusPedido;
import com.br.sistema_gestao_clientes_e_pedidos.repository.ClienteRepository;
import com.br.sistema_gestao_clientes_e_pedidos.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public String criarPedido(Long idCliente, String descricao, double valorTotal){

        if(idCliente == null){

            return "\nId do Cliente inválido. Tente Novamente";

        }

         Optional<Cliente> buscarClienteExistente = clienteRepository.findById(idCliente);

         if (buscarClienteExistente.isEmpty()){

             return "\nCliente não encontrado. Tente Novamente";

         }

         Cliente cliente = buscarClienteExistente.get();

         List<Pedido> buscarPedidoCliente = pedidoRepository.findByClienteId(idCliente);

         for (Pedido pedido : buscarPedidoCliente){

             if (pedido.getStatusPedido() == StatusPedido.ATRASADO){

                 return "\nCliente tem pedidos ATRASADOS não foi possível criar um novo pedido";

             }

         }

         if (valorTotal <= 0){

            return "\nValor inválido. Tente Novamente";

         }

         Pedido criacaoPedido = new Pedido();

         criacaoPedido.setCliente(cliente);
         criacaoPedido.setDescricao(descricao);
         criacaoPedido.setValorTotal(valorTotal);
         criacaoPedido.setStatusPedido(StatusPedido.ATIVO);

         pedidoRepository.save(criacaoPedido);

         return "\nPedido criado com Sucesso!!";

    }

    public String listarPedidos(){

        List<Pedido> listaPedidos = pedidoRepository.findAll();

        if (listaPedidos.isEmpty()){

            return "\nNão existem pedidos cadastrados";

        }

        StringBuilder resultadoListarPedidos = new StringBuilder();

        for (Pedido pedido : listaPedidos){

            System.out.println("DEBUG: Encontrei Pedido ID " + pedido.getId() + " para o cliente " + pedido.getCliente().getNome());

            resultadoListarPedidos.append("\n\nId: ").append(pedido.getId());
            resultadoListarPedidos.append("\nCliente Vinculado: ").append(pedido.getCliente().getNome());
            resultadoListarPedidos.append("\nStatus: ").append(pedido.getStatusPedido());
            resultadoListarPedidos.append("\nValor: ").append(pedido.getValorTotal());
            resultadoListarPedidos.append("\nDescrição: ").append(pedido.getDescricao());
            resultadoListarPedidos.append("\nDatas: ").append(pedido.getDataPedido());

        }

        return resultadoListarPedidos.toString();

    }

    public String listarPedidosPorStatus(StatusPedido statusPedido){

        List<Pedido> listarPorStatus = pedidoRepository.findByStatusPedido(statusPedido);

        if (listarPorStatus.isEmpty()){

            return "\nA lista está vazia";

        }

        StringBuilder resultadoListarPorStatus = new StringBuilder();

        for (Pedido pedidosListarPorStatus : listarPorStatus){

            resultadoListarPorStatus.append("\n\nId: ").append(pedidosListarPorStatus.getId());
            resultadoListarPorStatus.append("\nCliente Vinculado: ").append(pedidosListarPorStatus.getCliente().getNome());
            resultadoListarPorStatus.append("\nStatus: ").append(pedidosListarPorStatus.getStatusPedido());
            resultadoListarPorStatus.append("\nValor: ").append(pedidosListarPorStatus.getValorTotal());
            resultadoListarPorStatus.append("\nDatas: ").append(pedidosListarPorStatus.getDataPedido());

        }

        return resultadoListarPorStatus.toString();
    }

    public String listarPedidosPorCliente(Long idCliente){

        List<Pedido> listarPedidosPorCliente = pedidoRepository.findByClienteId(idCliente);

        StringBuilder resultadoListaPorCliente = new StringBuilder();

        for (Pedido pedidoListaPorCliente : listarPedidosPorCliente){

            resultadoListaPorCliente.append("\n\nId: ").append(pedidoListaPorCliente.getId());
            resultadoListaPorCliente.append("\nCliente Vinculado: ").append(pedidoListaPorCliente.getCliente().getNome());
            resultadoListaPorCliente.append("\nStatus: ").append(pedidoListaPorCliente.getStatusPedido());
            resultadoListaPorCliente.append("\nDescrição: ").append(pedidoListaPorCliente.getDescricao());
            resultadoListaPorCliente.append("\nValor: ").append(pedidoListaPorCliente.getValorTotal());
            resultadoListaPorCliente.append("\nDatas: ").append(pedidoListaPorCliente.getDataPedido());

        }

        return resultadoListaPorCliente.toString();

    }

    public String buscarPedidoPorId(Long idPedido){

        if (idPedido == null){

            return "\nId inválido. Tente Novamente";

        }

        Optional<Pedido> buscarPedidoPorId = pedidoRepository.findById(idPedido);

        if (buscarPedidoPorId.isEmpty()){

            return "\nNão existe pedido com esse Id ";

        }


        Pedido pedidoBuscaPorId = buscarPedidoPorId.get();

        StringBuilder resultadoBuscaPedidoPorId = new StringBuilder();

        resultadoBuscaPedidoPorId.append("\n\nId: ").append(pedidoBuscaPorId.getId());
        resultadoBuscaPedidoPorId.append("\nCliente Vinculado: ").append(pedidoBuscaPorId.getCliente().getNome());
        resultadoBuscaPedidoPorId.append("\nStatus: ").append(pedidoBuscaPorId.getStatusPedido());
        resultadoBuscaPedidoPorId.append("\nDescrição: ").append(pedidoBuscaPorId.getDescricao());
        resultadoBuscaPedidoPorId.append("\nValor: ").append(pedidoBuscaPorId.getValorTotal());
        resultadoBuscaPedidoPorId.append("\nDatas: ").append(pedidoBuscaPorId.getDataPedido());

       return resultadoBuscaPedidoPorId.toString();

    }

    public String atualizarPedido(Long idPedido, Long idNovoCliente, double novoValorTotal, String novaDescricao) {

        Optional<Pedido> buscarPedidoAtualizar = pedidoRepository.findById(idPedido);

        if (buscarPedidoAtualizar.isEmpty()){

            return "\nPedido não encontrado";

        }

        Pedido pedidoAtualizar = buscarPedidoAtualizar.get();

        if (pedidoAtualizar.getStatusPedido() == StatusPedido.FINALIZADO || pedidoAtualizar.getStatusPedido() == StatusPedido.CANCELADO) {

            return "\nNão é possível atualizar um pedido FINALIZADO ou CANCELADO";

        }

        Optional<Cliente> clienteNoBanco = clienteRepository.findById(idNovoCliente);

        if (clienteNoBanco.isEmpty()){

            return "\nNovo Cliente não encontrado no sistema!";

        }

        pedidoAtualizar.setCliente(clienteNoBanco.get());
        pedidoAtualizar.setValorTotal(novoValorTotal);
        pedidoAtualizar.setDescricao(novaDescricao);

        pedidoRepository.save(pedidoAtualizar);

        return "\nPedido atualizado com Sucesso!!";
    }

    public String alterarStatusPedido(Long idPedido, StatusPedido novoStatus){

        if(idPedido == null){

            return "\nId Pedido inválido. Tente Novamente";

        }

        Optional<Pedido> buscaPedido = pedidoRepository.findById(idPedido);

        if (buscaPedido.isEmpty()){

            return "\nNenhum pedido encontrado";

        }

        Pedido pedido = buscaPedido.get();
        StatusPedido statusAtual = pedido.getStatusPedido() ;

        if (statusAtual == StatusPedido.FINALIZADO || statusAtual == StatusPedido.CANCELADO){

            return "\nPedido foi CANCELADO ou já foi FINALIZADO. Não é possível fazer a alteração";

        }

        if (statusAtual == novoStatus){

            return "\nO Pedido já está com esse Status";

        }

        if (statusAtual == StatusPedido.ATRASADO){

            if (novoStatus == StatusPedido.ATIVO){

                return "\nPedido ATRASADO não pode voltar para ATIVO";

            }

        }

        pedido.setStatusPedido(novoStatus);
        pedidoRepository.save(pedido);

        return "\nStatus atualizado com Sucesso!!";

    }

    public String deletarPedido(Long idPedido){

        if (idPedido == null){

            return "\nId do Pedido inválido. Tente Novamente";

        }

        Optional<Pedido> buscaPedidoExclusao = pedidoRepository.findById(idPedido);

        if (buscaPedidoExclusao.isEmpty()){

            return "\nPedido não encontrado";

        }

        Pedido pedidoExclusao = buscaPedidoExclusao.get();

        if (pedidoExclusao.getStatusPedido() == StatusPedido.ATIVO || pedidoExclusao.getStatusPedido() == StatusPedido.ATRASADO){

            return "\nNão foi possível deletar o Pedido porque ele está ATIVO ou ATRASADO";

        }

        pedidoRepository.delete(pedidoExclusao);

        return "\nPedido deletado com Sucesso!!";

    }


}
