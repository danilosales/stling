package br.com.danilosales.stling.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilosales.stling.model.Produto;
import br.com.danilosales.stling.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Produtos")
@RestController
@RequestMapping({ "/api/produtos" })
@CrossOrigin(origins = { "http://localhost:4200", "http://stling-frontend:4200" })
public class ProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@ApiOperation("Busca um produto por id ou descrição")
	@GetMapping
	public List<Produto> buscarProdutosPorIdOuDescricao(@RequestParam("q")String query) {
		Integer codigo = null;
		if(StringUtils.isNumeric(query)) {
			codigo = Integer.parseInt(query);
		}
		return this.produtoRepository.findByIdOrDescricaoContains(codigo, query);
	}
	
}
