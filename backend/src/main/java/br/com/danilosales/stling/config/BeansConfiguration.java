package br.com.danilosales.stling.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.danilosales.stling.dto.PedidoProdutoDTO;
import br.com.danilosales.stling.model.PedidoProduto;

@Component
public class BeansConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new PropertyMap<PedidoProdutoDTO, PedidoProduto>() {

			@Override
			protected void configure() {
				map().setId(null);
			}
			
		});
		return mapper;
	}
	
}
