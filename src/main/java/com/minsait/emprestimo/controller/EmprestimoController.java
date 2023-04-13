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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.emprestimo.entity.Emprestimo;
import com.minsait.emprestimo.exception.EmprestimoNaoEncontradoException;
import com.minsait.emprestimo.service.EmprestimoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes/{cpf}/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	public EmprestimoController(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Emprestimo cadastrarEmprestimo(@Valid @RequestBody Emprestimo emprestimo) throws EmprestimoNaoEncontradoException {
		Emprestimo emprestimoSalvo = this.emprestimoService.cadastrarEmprestimo(emprestimo);
		return emprestimoSalvo;
	}
	

	@GetMapping
	public List<Emprestimo> retornarTodosEmprestimos(){
		List<Emprestimo> retorno = this.emprestimoService.retornarTodosEmprestimos();
		return retorno;
	}
	
	@GetMapping("/{id}")
	public Emprestimo retornarEmprestimo(@PathVariable Long id) throws EmprestimoNaoEncontradoException{
		Emprestimo emprestimoRecuperado = this.emprestimoService.retornarEmprestimo(id);
		return emprestimoRecuperado;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void excluirEmprestimo(@PathVariable Long id) throws EmprestimoNaoEncontradoException{
		this.emprestimoService.excluirEmprestimo(id);
	}
	
}
