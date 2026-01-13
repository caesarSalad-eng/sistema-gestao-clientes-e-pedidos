package com.br.sistema_gestao_clientes_e_pedidos.service;

import com.br.sistema_gestao_clientes_e_pedidos.model.Cliente;
import com.br.sistema_gestao_clientes_e_pedidos.model.StatusPedido;
import com.br.sistema_gestao_clientes_e_pedidos.repository.ClienteRepository;
import com.br.sistema_gestao_clientes_e_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

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

            return "\nNome inválido. Tente Novamente";

        }

        if (email == null || email.isBlank()){

            return "\nEmail inválido. Tente Novamente";

        }

        Optional<Cliente> optClienteEmailExistente = clienteRepository.findByEmail(email);

        if(optClienteEmailExistente.isPresent()){

            return "\nEsse Email já está cadastrado. Tente Novamente";

        }

        if (telefone == null || telefone.isBlank()){

            return "\nTelefone inválido. Tente Novamente";

        }

        cliente.setNome(nome.toUpperCase().trim());
        cliente.setEmail(email.toLowerCase().trim());
        cliente.setTelefone(telefone);

        clienteRepository.save(cliente);

        return "\nCliente cadastrado com Sucesso!!";

    }

    public String listarClientes(){

        List<Cliente> listaClientes = clienteRepository.findAll();

        if (listaClientes.isEmpty()){

            return "\nNão existem clientes cadastrados";

        }

        StringBuilder resultadoListaClientes = new StringBuilder();

        for (Cliente clientes : listaClientes){

            resultadoListaClientes.append("\n\nId: ").append(clientes.getId());
            resultadoListaClientes.append("\nNome: ").append(clientes.getNome());
            resultadoListaClientes.append("\nEmail: ").append(clientes.getEmail());
            resultadoListaClientes.append("\nTelefone: ").append(clientes.getTelefone());

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

        resultadoBuscaPorId.append("\nId: ").append(cliente.getId());
        resultadoBuscaPorId.append("\nNome: ").append(cliente.getNome());
        resultadoBuscaPorId.append("\nEmail: ").append(cliente.getEmail());
        resultadoBuscaPorId.append("\nTelefone: ").append(cliente.getTelefone());

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

        resultadoBuscaClientePorEmail.append("\nId: ").append(cliente.getId());
        resultadoBuscaClientePorEmail.append("\nNome: ").append(cliente.getNome());
        resultadoBuscaClientePorEmail.append("\nEmail: ").append(cliente.getEmail());
        resultadoBuscaClientePorEmail.append("\nTelefone: ").append(cliente.getTelefone());

        return resultadoBuscaClientePorEmail.toString();

    }

    public String atualizarCliente(Long idClienteExistente, String novoNome, String novoEmail, String novoTelefone){

        Cliente novoCliente;

        Optional<Cliente> buscaClienteExistente = clienteRepository.findById(idClienteExistente);

        if (buscaClienteExistente.isEmpty()){

            return "\nCliente não encontrado. Tente Novamente";

        }

        novoCliente = buscaClienteExistente.get();

        if (novoNome.isBlank()){

            return "\nNome inválido. Tente Novamente";

        }

        if (novoEmail.isBlank()){

            return "\nEmail inválido. Tente Novamente";

        }


        if (novoTelefone.isBlank()){

            return "\nTelefone inválido. Tente Novamente";

        }

        novoCliente.setNome(novoNome);
        novoCliente.setEmail(novoEmail);
        novoCliente.setTelefone(novoTelefone);

        Optional<Cliente> buscaEmailExistente = clienteRepository.findByEmail(novoEmail);

        if (buscaEmailExistente.isPresent() && !buscaEmailExistente.get().getId().equals(idClienteExistente)) {

            return "\nEste email já está cadastrado. Tente Novamente";
        }
        clienteRepository.save(novoCliente);

        return "\nUsuário atualizado com Sucesso!!";

    }

    public String deletarCliente(Long idClienteExclusao){

        if (idClienteExclusao == null){

            return "\nId inválido. Tente Novamente";

        }

        Optional<Cliente> buscaClienteExclusao = clienteRepository.findById(idClienteExclusao);

        if (buscaClienteExclusao.isEmpty()){

            return "\nCliente não encontrado. Tente Novamente";

        }

        Cliente clienteExclusao = buscaClienteExclusao.get();

        boolean clienteTemPedidosBloqueantes = pedidoRepository.existsByClienteIdAndStatusPedidoIn(idClienteExclusao, List.of(StatusPedido.ATIVO, StatusPedido.ATRASADO));

        if (clienteTemPedidosBloqueantes) {

            return "O cliente possui pedidos ATIVOS ou ATRASADOS. Não é possível excluir";

        }

        clienteRepository.delete(clienteExclusao);

        return "\nO cliente deletado com Sucesso!!";


    }


}
