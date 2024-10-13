package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.RelacionamentoPedidoProduto;

public class RelacionamentoPedidoProdutoDao {

	EntityManager em;

	public RelacionamentoPedidoProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(RelacionamentoPedidoProduto rel) {
		this.em.persist(rel);
	}

}
