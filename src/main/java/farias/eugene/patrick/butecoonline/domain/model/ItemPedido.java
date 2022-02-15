package farias.eugene.patrick.butecoonline.domain.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data

public class ItemPedido {

	@NotNull
	private Long idProduto;

	@NotNull
	private BigDecimal quantidade;

}
