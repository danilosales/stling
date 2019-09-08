package br.com.danilosales.stling.repository.helper.pedido;

import java.util.List;

import br.com.danilosales.stling.dto.PedidoResumoDTO;

public interface PedidosQueries {

	List<PedidoResumoDTO> buscarPedidos(String pedido);
	
}
