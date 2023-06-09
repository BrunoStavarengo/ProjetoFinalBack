package com.minsait.emprestimo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.emprestimo.entity.Cliente;
import com.minsait.emprestimo.exception.ClienteNaoEncontradoException;
import com.minsait.emprestimo.service.ClienteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clienteSalvo = this.clienteService.cadastrarCliente(cliente);
		return clienteSalvo;
	}
	
	@GetMapping
	public List<Cliente> retornarTodosClientes(){
		List<Cliente> retorno = this.clienteService.retornarTodosClientes();
		return retorno;
	}
	
	@GetMapping("/{cpf}")
	public Cliente retornarCliente(@PathVariable Long cpf) throws ClienteNaoEncontradoException{
		Cliente clienteRecuperado = this.clienteService.retornarCliente(cpf);
		return clienteRecuperado;
	}
	
	@PutMapping("/{cpf}")
	public Cliente atualizarCliente(@PathVariable Long cpf, @Valid @RequestBody Cliente cliente) throws ClienteNaoEncontradoException{
		Cliente clienteAtualizado = this.clienteService.atualizarCliente(cpf, cliente);
		return clienteAtualizado;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cpf}")
	public void excluirCliente(@PathVariable Long cpf) throws ClienteNaoEncontradoException{
		this.clienteService.excluirCliente(cpf);
	}

}
