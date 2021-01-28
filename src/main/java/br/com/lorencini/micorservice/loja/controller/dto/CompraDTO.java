package br.com.lorencini.micorservice.loja.controller.dto;

import java.util.List;

public class CompraDTO {
	
	private List<ItemDACompraDTO> itens;
	
	private EnderecoDTO endereco;

	public List<ItemDACompraDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemDACompraDTO> itens) {
		this.itens = itens;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
	

}
