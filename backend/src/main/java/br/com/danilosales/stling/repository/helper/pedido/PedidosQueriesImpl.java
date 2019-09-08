package br.com.danilosales.stling.repository.helper.pedido;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.danilosales.stling.dto.PedidoResumoDTO;

public class PedidosQueriesImpl implements PedidosQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<PedidoResumoDTO> buscarPedidos(String pedido) {
		
		String jpql = "SELECT new br.com.danilosales.stling.dto.PedidoResumoDTO("
				+ " p.id, p.cliente.nome, p.vendedor.nome,"
				+ " p.dtCadastro, p.valorTotal, size(p.itens),"
				+ " p.dtEmissao != null)"
				+ " from Pedido p"
				+ " where p.vendedor.nome like :pedido OR p.cliente.nome like :pedido"
				+ " group by p.id, p.cliente.nome, p.vendedor.nome, p.dtCadastro, p.valorTotal";
		
		List<PedidoResumoDTO> resultado = manager.createQuery(jpql, PedidoResumoDTO.class)
				.setParameter("pedido", "%" + pedido + "%")
				.getResultList();
		
		return resultado;
	}

}
