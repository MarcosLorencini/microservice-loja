package br.com.lorencini.micorservice.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.lorencini.micorservice.loja.controller.dto.ItemDACompraDTO;
import br.com.lorencini.micorservice.loja.dto.InfoFornecedorDTO;
import br.com.lorencini.micorservice.loja.dto.InfoPedidoDTO;

//IMPLEMENTACAO DO CLIENT

//o feing saber o ip/porta do fornecedor pq ele é integrado como ribbon, ou seja, 
//está intergrado com client loadbalance, com eureka que consegue se comunicar com o eureka service
//que pega todas as informaões dos microsserviços

@FeignClient("fornecedor")//id da aplicacao que quer acessar no caso fornecedor
public interface FornecedorClient {
	
	//implementar os metodos dos servicos que vamos acessar
	
	@RequestMapping("/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);
	
	@RequestMapping(method = RequestMethod.POST, value = "/pedido")
	InfoPedidoDTO realizaPedido(List<ItemDACompraDTO> itens);
	

}
