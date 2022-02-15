package farias.eugene.patrick.butecoonline.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Componente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private BigDecimal quantidade;
	
	@JoinColumn(nullable = true)
	private String descricao;
	
	@Column(nullable = true)
	private BigDecimal precoTotal;
	
	//@JsonIgnore
	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ingrediente_id", nullable = false)
	private Ingrediente ingrediente;
	
	
	public void calcularPrecoTotal() {
		BigDecimal precoUnitario = this.getIngrediente().getPrecoUnitario();
		BigDecimal quantidade = this.getQuantidade();

		if (precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}

		if (quantidade == null) {
			quantidade = BigDecimal.ZERO;
		}

		this.setPrecoTotal(precoUnitario.multiply(quantidade));
	}
	
	public BigDecimal calcularQtdIngredientePedido(BigDecimal quantidadeProdutoSolicitada) {
		
		BigDecimal quantidadePorProduto = this.getQuantidade();

		if (quantidadePorProduto == null) {
			quantidadePorProduto = BigDecimal.ZERO;
		}
		
		BigDecimal quantidadePrevista = quantidadeProdutoSolicitada.multiply(quantidadePorProduto);

		if (quantidadePrevista == null) {
			quantidadePrevista = BigDecimal.ZERO;
		}

		return quantidadePrevista;
	}

}