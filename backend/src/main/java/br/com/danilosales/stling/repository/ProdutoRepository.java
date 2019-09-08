package br.com.danilosales.stling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danilosales.stling.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	public List<Produto> findByIdOrDescricaoContains(Integer id, String descricao);
}
