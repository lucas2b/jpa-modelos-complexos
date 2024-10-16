package br.com.alura.loja.testes;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.beans.RelatorioDeVendasBean;
import br.com.alura.loja.dao.ItemPedidoDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.util.JPAUtil;

public class ConsultasAvancadasJPQL {

	public static void main(String[] args) throws SQLException, InterruptedException {
		
		CadastroDePedidoComRelacionamentoBidirecional.main(args);
		EntityManager em = JPAUtil.getEntityManager();		
		PedidoDao pedidoDao = new PedidoDao(em);
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(em);

		System.out.println("---->>> Valor Total de Todos pedidos realizados: " + pedidoDao.calcularValorTotalDeTodosPedidos());
		System.out.println();
		
		System.out.println("---->>> Relatório de vendas por produto utilizando Object:");
		for(Object[] item : pedidoDao.relatorioDeVendasObject()) {
			System.out.println("Produto: " + item[0] + " | Quantidade: " + item[1] + " | Última data de venda: " + item[2]);
		}
		System.out.println();
		
		
		System.out.println("---->>> Relatório de vendas por produto utilizando Bean:");
		pedidoDao.relatorioDeVendasBean().forEach(System.out::println);
		
		//teste para trazer todos "ItemPedido" e seus atributos Eager
		List<ItemPedido> listaTodosItensPedidos = itemPedidoDao.buscarTodosItensPedidos();

		System.out.println();
	}

}
