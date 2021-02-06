package br.com.lorencini.micorservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lorencini.micorservice.loja.client.FornecedorClient;
import br.com.lorencini.micorservice.loja.controller.dto.CompraDTO;
import br.com.lorencini.micorservice.loja.dto.InfoFornecedorDTO;
import br.com.lorencini.micorservice.loja.dto.InfoPedidoDTO;
import br.com.lorencini.micorservice.loja.model.Compra;

@Service
public class CompraService {
	
	//TEXTOS COMENTADOS É UM EXEMPLO DE IMPLEMENTACAO DA TECNOLOGIA RIBBON
	//VAMOS IMPLEMENTAR O FEING QUE JÁ TEM EMBUTIBO DO RIBBON
	
	//resolve o ip/porta do servico que queremos acessar "http://fornecedor/info/" tbm
	//tem a funcionalidade de loadBalance(ribbon) que faz requisição para instancias diferentes 
	//@Autowired
	//private RestTemplate client;
	
	//fornece as intancias disponiveis do fornecedor registradas no eureka
	//
	//@Autowired
	//private DiscoveryClient eurekaClient;
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compra) {
		
		//faz a requis para o fornecedor
		InfoFornecedorDTO infoPorEstado = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		//passar os dados da compra http://localhost:8080/compra
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		System.out.println(infoPorEstado.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;

		/*
		 * ResponseEntity<InfoFornecedorDTO> exchange =
		 * client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(),
		 * HttpMethod.GET, null, InfoFornecedorDTO.class);
		 */
		
		
		//clienteSide loadbalance implementado pelo ribbon faz: pega as inf do eurekaclient subst. pelo ip/porta da instancia e 
		//tbm seleciona qual a instancia a requisicao vai bater (loadbalance)
		//imprime localhost e portas de todos as instanicas de fornecedores disponiveis no eureka
		// a cada requisição, o Ribbon rotaciona para uma instância diferente. Todavia, é possível customizar o algoritmo de load balancing
		/*
		 * eurekaClient.getInstances("fornecedor").stream() .forEach(fornecedor -> {
		 * System.out.println("localhost: "+fornecedor.getPort()); });
		 */
	}

}
