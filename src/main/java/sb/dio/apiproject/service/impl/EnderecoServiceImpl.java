package sb.dio.apiproject.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sb.dio.apiproject.model.Endereco;
import sb.dio.apiproject.model.EnderecoRepository;
import sb.dio.apiproject.service.EnderecoService;
import sb.dio.apiproject.service.ViaCepService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Endereco buscarPorCep(String cep) {
        if (!cep.contains("-"))
            cep = cep.substring(0, 5) + "-" + cep.substring(5);
        return enderecoRepository.findById(cep).get();
    }

    @Override
    public Iterable<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    @Override
    public void deletar(String cep) {
        if (!cep.contains("-"))
            cep = cep.substring(0, 5) + "-" + cep.substring(5);
        enderecoRepository.deleteById(cep);
    }

    @Override
    public Endereco inserir(Endereco endereco) {
        Optional<Endereco> enderecoBd = enderecoRepository.findById(endereco.getCep());
        if (enderecoBd.isPresent()) {
            return enderecoBd.get();
        }
        endereco = viaCepService.consultarCep(endereco.getCep());
        enderecoRepository.save(endereco);
        return endereco;
    }
    
}
