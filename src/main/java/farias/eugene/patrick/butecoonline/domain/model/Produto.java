package farias.eugene.patrick.butecoonline.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private BigDecimal preco;

	@Column(nullable = true)
	private BigDecimal custoTotal;

	@ManyToMany
	private List<Componente> componentes = new ArrayList<>();

	public void calcularValorTotal() {
		getComponentes().forEach(Componente::calcularPrecoTotal);

		this.custoTotal = getComponentes().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

	}

	/*
	 * Valida se o produto tem ingrediente suficiente
	 */
	public void validaDisponibilidade(BigDecimal quantidade) {
		getComponentes().forEach(Componente::getQuantidade);

		getComponentes().stream().map(item -> item.getQuantidade().multiply(quantidade)).collect(Collectors.toList());

		// this.custoTotal = getComponentes().stream().map(item ->
		// item.getPrecoTotal()).reduce(BigDecimal.ZERO,
		// BigDecimal::add);

	}

}