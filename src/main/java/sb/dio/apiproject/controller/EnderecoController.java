package sb.dio.apiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import sb.dio.apiproject.model.Endereco;
import sb.dio.apiproject.service.EnderecoService;



@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<Iterable<Endereco>> buscarTodos() {
        return ResponseEntity.ok(enderecoService.buscarTodos());
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable String cep){
        return ResponseEntity.ok(enderecoService.buscarPorCep(cep));
    }

    @PostMapping
    public ResponseEntity<Endereco> inserir(@RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.inserir(endereco));
    }

    @DeleteMapping("/{cep}")
    public void deletar(@PathVariable String cep) {
        enderecoService.deletar(cep);
    }

}
