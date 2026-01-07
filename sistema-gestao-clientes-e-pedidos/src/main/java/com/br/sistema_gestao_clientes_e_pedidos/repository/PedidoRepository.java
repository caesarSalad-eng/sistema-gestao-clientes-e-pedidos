package com.br.sistema_gestao_clientes_e_pedidos.repository;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import com.br.sistema_gestao_clientes_e_pedidos.model.Pedido;
import com.br.sistema_gestao_clientes_e_pedidos.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

        List<Pedido> findByCliente(Cliente cliente);

        List<Pedido> findByStatus(StatusPedido statusPedido);

}
