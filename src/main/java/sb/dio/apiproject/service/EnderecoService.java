package sb.dio.apiproject.service;

import sb.dio.apiproject.model.Endereco;

public interface EnderecoService {

    Iterable<Endereco> buscarTodos();
    Endereco buscarPorCep(String cep);
    Endereco inserir(Endereco endereco);
    void deletar(String cep);
    
}