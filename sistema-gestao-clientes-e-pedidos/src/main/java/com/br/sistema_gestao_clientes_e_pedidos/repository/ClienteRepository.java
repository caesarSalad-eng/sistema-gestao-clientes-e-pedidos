package com.br.sistema_gestao_clientes_e_pedidos.repository;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);


}
