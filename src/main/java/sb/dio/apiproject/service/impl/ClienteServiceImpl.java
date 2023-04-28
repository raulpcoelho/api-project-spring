package sb.dio.apiproject.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.dio.apiproject.model.Cliente;
import sb.dio.apiproject.model.ClienteRepository;
import sb.dio.apiproject.model.Endereco;
import sb.dio.apiproject.model.EnderecoRepository;
import sb.dio.apiproject.service.ClienteService;
import sb.dio.apiproject.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public void inserir(Cliente cliente) {
        verificaEnderecoSalvaCliente(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteExsistenteOptional = clienteRepository.findById(id);
        if (clienteExsistenteOptional.isEmpty())
            return;
        Cliente clienteExistente = clienteExsistenteOptional.get();
        if (cliente.getNome() != null)
            clienteExistente.setNome(cliente.getNome());
        if (cliente.getEndereco() != null) {
            String cep = cliente.getEndereco().getCep();
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                Endereco novo = viaCepService.consultarCep(cep);
                enderecoRepository.save(novo);
                return novo;
            });
            clienteExistente.setEndereco(endereco);    
        }
        clienteRepository.save(clienteExistente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void verificaEnderecoSalvaCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novo = viaCepService.consultarCep(cep);
            enderecoRepository.save(novo);
            return novo;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}