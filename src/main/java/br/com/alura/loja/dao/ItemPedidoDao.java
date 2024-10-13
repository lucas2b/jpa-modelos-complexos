package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.ItemPedido;

public class ItemPedidoDao {

	EntityManager em;

	public ItemPedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(ItemPedido itemPedido) {
		this.em.persist(itemPedido);
	}

}
