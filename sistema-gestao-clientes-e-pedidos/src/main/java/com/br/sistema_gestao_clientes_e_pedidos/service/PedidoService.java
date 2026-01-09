package com.br.sistema_gestao_clientes_e_pedidos.service;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import com.br.sistema_gestao_clientes_e_pedidos.repository.ClienteRepository;
import com.br.sistema_gestao_clientes_e_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository){

        this.pedidoRepository = pedidoRepository;

        this.clienteRepository = clienteRepository;

    }

    public String criarPedido(Long idCliente, String descricao, Double valorTotal, Date data){

         Optional<Cliente> buscarClienteExistente = clienteRepository.findById(idCliente);

         if (buscarClienteExistente.isEmpty()){

             return "Cliente não encontrado. Tente Novamente";

         }

         Cliente cliente = buscarClienteExistente.get();

         if (!cliente.getPedidos().isEmpty() || cliente.){

             return "Não foi possível criar um pedido porque o Cliente tem pedidos Atrasados ou Ativos";

         }

         if (valorTotal <= 0){

            return "Valor inválido. Tente Novamente";

         }

         


    }


}
