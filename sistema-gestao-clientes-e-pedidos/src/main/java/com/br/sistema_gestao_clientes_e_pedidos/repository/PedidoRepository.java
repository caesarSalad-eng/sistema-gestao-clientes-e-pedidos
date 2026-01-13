package com.br.sistema_gestao_clientes_e_pedidos.repository;

import com.br.sistema_gestao_clientes_e_pedidos.model.Pedido;
import com.br.sistema_gestao_clientes_e_pedidos.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

        List<Pedido> findByClienteId(Long idCliente);

        List<Pedido> findByStatusPedido(StatusPedido statusPedido);

        boolean existsByClienteIdAndStatusPedidoIn(Long clienteId, List<StatusPedido> status);

}
