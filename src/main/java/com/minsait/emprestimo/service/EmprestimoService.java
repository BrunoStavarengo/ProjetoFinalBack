package com.minsait.emprestimo.service;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.emprestimo.entity.Cliente;
import com.minsait.emprestimo.entity.Emprestimo;
import com.minsait.emprestimo.exception.EmprestimoNaoEncontradoException;
import com.minsait.emprestimo.repository.ClienteRepository;
import com.minsait.emprestimo.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

	private final EmprestimoRepository emprestimoRepository;
	private final ClienteRepository clienteRepository;

	@Autowired
	public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteRepository clienteRepository) {
		this.emprestimoRepository = emprestimoRepository;
		this.clienteRepository = clienteRepository;
	}

	
	public Emprestimo cadastrarEmprestimo( Emprestimo emprestimo){	
	
			Cliente cliente = clienteRepository.findById(emprestimo.getCpfCliente().getCpf()).get();
			emprestimo.setCpfCliente(cliente);
			if(validarLimiteEmprestimo(emprestimo)) {
			emprestimo.getNumeroEmprestimos();
			emprestimo.calcularValorFinal();
			Emprestimo emprestimoSalvo = this.emprestimoRepository.save(emprestimo);
			
			return emprestimoSalvo;
			}
			
			 throw new IllegalArgumentException("O cliente já atingiu o limite de empréstimos permitido");
			
		}
			

	public void validarEmprestimo(Emprestimo emprestimo) {
		Long id = emprestimo.getId();
		if (id != null && emprestimoRepository.existsById(id)) {
			throw new IllegalArgumentException("Emprestimo já cadastrado");
		}
	}
	
	public boolean validarLimiteEmprestimo(Emprestimo emprestimo) {
		BigDecimal valorTotalEmprestimos = BigDecimal.ZERO;
		for (Emprestimo emprestimoExistente : emprestimo.getCpfCliente().getEmprestimos()) {
			valorTotalEmprestimos = valorTotalEmprestimos.add(emprestimoExistente.getValorInicial());
		}
		valorTotalEmprestimos = valorTotalEmprestimos.add(emprestimo.getValorInicial());
		BigDecimal limiteEmprestimo = emprestimo.getCpfCliente().getRendimentoMensal() != null ? emprestimo.getCpfCliente().getRendimentoMensal().multiply(BigDecimal.TEN) : BigDecimal.ZERO;
		return (limiteEmprestimo.compareTo(valorTotalEmprestimos) >= 0);
	}
	
	public List<Emprestimo> retornarTodosEmprestimos(){
		return this.emprestimoRepository.findAll();
	}
	

	public Emprestimo retornarEmprestimo(Long id) throws EmprestimoNaoEncontradoException {
		if(this.emprestimoRepository.existsById(id)) {
			return this.emprestimoRepository.findById(id).get();
		}
		throw new EmprestimoNaoEncontradoException(id);
	}
	
	

	public MensagemDeSucesso excluirEmprestimo(Long id) throws EmprestimoNaoEncontradoException{
		if(this.emprestimoRepository.existsById(id)) {
			this.emprestimoRepository.deleteById(id);
			MensagemDeSucesso mensagem = new MensagemDeSucesso();
			mensagem.setMensagem("Deletado com sucesso");
			return mensagem;
		}
		throw new EmprestimoNaoEncontradoException(id);
	}
	
}
