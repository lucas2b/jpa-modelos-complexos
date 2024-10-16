package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.beans.RelatorioDeVendasBean;
import br.com.alura.loja.modelo.Pedido;

public class PedidoDao {

	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return this.em.find(Pedido.class, id);
	}
	
	public BigDecimal calcularValorTotalDeTodosPedidos() {
		String jpql = "Select sum(p.valorTotal) from Pedido p";
		return this.em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<Object[]> relatorioDeVendasObject() {
		String jpql = "SELECT produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.listaItemPedido item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return this.em.createQuery(jpql, Object[].class).getResultList();
	}
	
	public List<RelatorioDeVendasBean> relatorioDeVendasBean() {
		String jpql = "SELECT new br.com.alura.loja.beans.RelatorioDeVendasBean(produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.listaItemPedido item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return this.em.createQuery(jpql, RelatorioDeVendasBean.class).getResultList();
	}

}
