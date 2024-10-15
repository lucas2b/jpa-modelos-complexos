package br.com.alura.loja.testes;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.util.JPAUtil;

public class ConsultasAvancadasJPQL {

	public static void main(String[] args) throws SQLException, InterruptedException {
		
		CadastroDePedidoComRelacionamentoBidirecional.main(args);
		EntityManager em = JPAUtil.getEntityManager();		
		PedidoDao pedidoDao = new PedidoDao(em);

		
		System.out.println("Valor Total de Todos pedidos realizados: " + pedidoDao.calcularValorTotalDeTodosPedidos());
		
		List<Object[]> relatorioVendas = pedidoDao.relatorioDeVendas();
		
		for(Object[] item : relatorioVendas) {
			System.out.println("Produto: " + item[0] + " | Quantidade: " + item[1] + " | Última data de venda: " + item[2]);
		}
		
		System.out.println();

	}

}
