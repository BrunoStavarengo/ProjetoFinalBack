package com.minsait.emprestimo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
	private Long cpf;
	
	
	@NotEmpty(message = "Nome não pode ser nulo.")
	private String nome;
	@NotEmpty(message = "telefone não pode ser nulo.")
	private String telefone;
	@NotNull(message = "Rendimento mensal não pode ser nulo.")
	private BigDecimal rendimentoMensal;
	@NotEmpty(message = "Endereço não pode ser nulo.")
	private String endereco;
	@NotEmpty(message = "Número não pode ser nulo.")
	private String numero;
	@NotEmpty(message = "Cep não pode ser nulo.")
	private String cep;	
	
	@OneToMany(mappedBy = "cpfCliente", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Emprestimo> emprestimos = new ArrayList<>();
	
	
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	
	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setRendimentoMensal(BigDecimal rendimentoMensal) {
		this.rendimentoMensal = rendimentoMensal;
	}

	public BigDecimal getRendimentoMensal() {
		return rendimentoMensal;
	}
	


	public BigDecimal calculaTotalEmprestimo(String cpf) {
		BigDecimal valorTotalEmprestimos = BigDecimal.ZERO;
		for (Emprestimo emprestimoExistente : this.getEmprestimos()) {
			valorTotalEmprestimos = valorTotalEmprestimos.add(emprestimoExistente.getValorInicial());
		}

		return valorTotalEmprestimos;
	}

	
	
	
}
