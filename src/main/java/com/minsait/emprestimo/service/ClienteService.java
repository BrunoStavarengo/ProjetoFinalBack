package com.minsait.emprestimo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.emprestimo.entity.Cliente; 
import com.minsait.emprestimo.exception.ClienteNaoEncontradoException;
import com.minsait.emprestimo.repository.ClienteRepository;

@Service
public class ClienteService {


	private final ClienteRepository clienteRepository;
	

	@Autowired 
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository; 
	}

	public Cliente cadastrarCliente(Cliente cliente) { 
		Cliente clienteRetorno = this.clienteRepository.save(cliente); 
		return clienteRetorno; 
	}

	public List<Cliente> retornarTodosClientes(){ 
		return this.clienteRepository.findAll(); 
	}

	public Cliente retornarCliente(Long cpf) throws ClienteNaoEncontradoException{ 
		if(this.clienteRepository.existsById(cpf)) {
			return this.clienteRepository.findById(cpf).get();
		} 
		throw new ClienteNaoEncontradoException(cpf); 
	}

	public Cliente atualizarCliente(Long cpf, Cliente cliente) throws ClienteNaoEncontradoException{ 
		if(this.clienteRepository.existsById(cpf)) {
			Cliente clienteASerAtualizado = this.clienteRepository.findById(cpf).get();
			cliente.setCpf(cpf); 
			
			if(cliente.getNome() == null) {
				cliente.setNome(clienteASerAtualizado.getNome());
			}
			
			if(cliente.getTelefone() == null) {
				cliente.setTelefone(clienteASerAtualizado.getTelefone());
			}
			
			if(cliente.getRendimentoMensal() == null) {
				cliente.setRendimentoMensal(clienteASerAtualizado.getRendimentoMensal());
			}
			
			if(cliente.getEndereco() == null) {
				cliente.setEndereco(clienteASerAtualizado.getEndereco());
			}
			
			if(cliente.getNumero() == null) {
				cliente.setNumero(clienteASerAtualizado.getNumero());
			}
			
			if(cliente.getCep() == null) {
				cliente.setNumero(clienteASerAtualizado.getCep());
			}
			
			return this.clienteRepository.save(cliente);
		} 
		throw new ClienteNaoEncontradoException(cpf); 
	}

	public MensagemDeSucesso excluirCliente(Long cpf) throws ClienteNaoEncontradoException{ 
		if(this.clienteRepository.existsById(cpf)) {
			this.clienteRepository.deleteById(cpf); 
			MensagemDeSucesso mensagem = new MensagemDeSucesso(); 
			mensagem.setMensagem("Deletado com sucesso");
			return mensagem; 
			} 
		throw new ClienteNaoEncontradoException(cpf); 
	}

}
  
