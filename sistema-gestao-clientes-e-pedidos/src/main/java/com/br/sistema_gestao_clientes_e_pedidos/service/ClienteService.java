package com.br.sistema_gestao_clientes_e_pedidos.service;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import com.br.sistema_gestao_clientes_e_pedidos.repository.ClienteRepository;
import com.br.sistema_gestao_clientes_e_pedidos.repository.PedidoRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PedidoRepository pedidoRepository;

    public ClienteService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository){

        this.clienteRepository = clienteRepository;

        this.pedidoRepository = pedidoRepository;

    }

    public String cadastroCliente(String nome, String email, String telefone){

        Cliente cliente = new Cliente();

        if (nome == null || nome.isBlank()){

            return "Nome inválido. Tente Novamente";

        }

        if (email == null || email.isBlank()){

            return "Email inválido. Tente Novamente";

        }

        Optional<Cliente> optClienteEmailExistente = clienteRepository.findByEmail(email);

        if(optClienteEmailExistente.isPresent()){

            return "Esse Email já está cadastrado. Tente Novamente";

        }

        if (telefone == null || telefone.isBlank()){

            return "Telefone inválido. Tente Novamente";

        }

        cliente.setNome(nome.toUpperCase().trim());
        cliente.setEmail(email.toLowerCase().trim());

        clienteRepository.save(cliente);

        return "Cliente cadastrado com Sucesso!!";

    }

    public String listarClientes(){

        List<Cliente> listaClientes = clienteRepository.findAll();

        if (listaClientes.isEmpty()){

            return "Não existem clientes cadastrados";

        }

        StringBuilder resultadoListaClientes = new StringBuilder();

        for (Cliente clientes : listaClientes){

            resultadoListaClientes.append("Id: ").append(clientes.getId());
            resultadoListaClientes.append("Nome: ").append(clientes.getNome());
            resultadoListaClientes.append("Email: ").append(clientes.getEmail());
            resultadoListaClientes.append("Telefone: ").append(clientes.getTelefone());
            resultadoListaClientes.append("Pedidos: ").append(clientes.getPedidos());

        }

        return resultadoListaClientes.toString();

    }

    public String buscaClientePorID(Long idCliente){

        if (idCliente == null){

            return "Id inválido. Tente Novamente";

        }

        Optional<Cliente> optClienteFindById = clienteRepository.findById(idCliente);

        if (optClienteFindById.isEmpty()){

            return "Cliente não encontrado";

        }

        StringBuilder resultadoBuscaPorId = new StringBuilder();

        Cliente cliente = optClienteFindById.get();

        resultadoBuscaPorId.append("Id: ").append(cliente.getId());
        resultadoBuscaPorId.append("Nome: ").append(cliente.getNome());
        resultadoBuscaPorId.append("Email: ").append(cliente.getEmail());
        resultadoBuscaPorId.append("Telefone: ").append(cliente.getTelefone());
        resultadoBuscaPorId.append("Pedidos: ").append(cliente.getPedidos());

        return resultadoBuscaPorId.toString();


    }

    public String buscaClientePorEmail(String email){

        if (email.isBlank()){

            return "Email inválido. Tente Novamente";

        }

        Optional<Cliente> optClienteFindByEmail = clienteRepository.findByEmail(email);

        if (optClienteFindByEmail.isEmpty()){

            return "Cliente não encontrado.";

        }
        
        Cliente cliente = optClienteFindByEmail.get();

        StringBuilder resultadoBuscaClientePorEmail = new StringBuilder();

        resultadoBuscaClientePorEmail.append("Id: ").append(cliente.getId());
        resultadoBuscaClientePorEmail.append("Nome: ").append(cliente.getNome());
        resultadoBuscaClientePorEmail.append("Email: ").append(cliente.getEmail());
        resultadoBuscaClientePorEmail.append("Telefone: ").append(cliente.getTelefone());
        resultadoBuscaClientePorEmail.append("Pedidos: ").append(cliente.getPedidos());

        return resultadoBuscaClientePorEmail.toString();

    }

}
