package br.com.danilosales.stling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danilosales.stling.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
