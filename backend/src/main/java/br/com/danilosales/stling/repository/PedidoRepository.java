package br.com.danilosales.stling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danilosales.stling.model.Pedido;
import br.com.danilosales.stling.repository.helper.pedido.PedidosQueries;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>, PedidosQueries{

	@EntityGraph(attributePaths = {"itens"})
	Optional<Pedido> findById(Integer id);
	
}
