package com.minsait.emprestimo.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.minsait.emprestimo.enums.StatusRelacionamentoEnum;

import lombok.Data;

@Entity
@Data
public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "cpfCliente_id")
	private Cliente cpfCliente;
	
	@NotNull
	private BigDecimal valorInicial;
	
	
	private BigDecimal valorFinal;
	
	@NotNull
	@NotEmpty
	@Size( min = 10, max = 10, message = "Digite a data no formato DD/MM/AAAA")
	private String dataInicial;
	
	@NotNull
	@NotEmpty
	@Size( min = 10, max = 10, message = "Digite a data no formato DD/MM/AAAA")
	private String dataFinal;

	@Enumerated(EnumType.STRING)
	private StatusRelacionamentoEnum relacionamento;
	

	public Long getId() {
		return id;
	}
	
	public Cliente getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(Cliente cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public String getDataInicial() {
		
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public StatusRelacionamentoEnum getRelacionamento() {
		return relacionamento;
	}
	
	public void setRelacionamento(StatusRelacionamentoEnum relacionamento) {
		this.relacionamento = relacionamento;
	}
	
	public void calcularValorFinal() {
		this.valorFinal = this.relacionamento.calcularValorFinal(this);
	}

	public int getNumeroEmprestimos() {
		Cliente cliente = this.getCpfCliente();
		List<Emprestimo> emprestimos = cliente.getEmprestimos();
		return emprestimos.size();
	}
	
	


	}
		


