package br.com.lorencini.micorservice.loja.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lorencini.micorservice.loja.dto.InfoFornecedorDTO;

//IMPLEMENTACAO DO CLIENT

//o feing saber o ip/porta do fornecedor pq ele é integrado como ribbon, ou seja, 
//está intergrado com client loadbalance, com eureka que consegue se comunicar com o eureka service
//que pega todas as informaões dos microsserviços

@FeignClient("fornecedor")//id da aplicacao que quer acessar no caso fornecedor
public interface FornecedorClient {
	
	//implementar os metodos dos servicos que vamos acessar
	
	@RequestMapping("/info/{estado}")
	public InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);
	

}
