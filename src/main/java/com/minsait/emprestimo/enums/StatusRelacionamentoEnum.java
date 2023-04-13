package com.minsait.emprestimo.enums;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.minsait.emprestimo.entity.Emprestimo;

public enum StatusRelacionamentoEnum {
	
	BRONZE {
		public BigDecimal calcularValorFinal(Emprestimo emprestimo) {
			return emprestimo.getValorInicial().multiply(new BigDecimal("1.8"), MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
		}
	},

	PRATA {
		public BigDecimal calcularValorFinal(Emprestimo emprestimo) {
			BigDecimal valorInicial = emprestimo.getValorInicial();
			if (valorInicial.compareTo(new BigDecimal("5000")) > 0) {
				return valorInicial.multiply(new BigDecimal("1.4"), MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
			} else {
				return valorInicial.multiply(new BigDecimal("1.6"), MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
			}
		}
	},

	
	OURO {
		public BigDecimal calcularValorFinal(Emprestimo emprestimo) {
			BigDecimal valorInicial = emprestimo.getValorInicial();
			List<Emprestimo> emprestimos = emprestimo.getCpfCliente().getEmprestimos();
			int numEmprestimos = (int) emprestimos.stream().filter(e -> !e.getId().equals(emprestimo.getId())).count();
			if (numEmprestimos <= 1) {
				return valorInicial.multiply(new BigDecimal("1.2"), MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
			} else {
				return valorInicial.multiply(new BigDecimal("1.3"), MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
			}
		}
	};
	
	
	public abstract BigDecimal calcularValorFinal(Emprestimo emprestimo);
	
}
