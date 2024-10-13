package br.com.alura.loja.testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.h2.tools.Server;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.dao.RelacionamentoPedidoProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.RelacionamentoPedidoProduto;
import br.com.alura.loja.util.CargaDeProdutos;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) throws SQLException, InterruptedException {

		CargaDeProdutos.carregarProdutosBaseDeDados();
		Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();

		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		// Daos
		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		RelacionamentoPedidoProdutoDao relacionamentoPedidoProdutoDao = new RelacionamentoPedidoProdutoDao(em);
		// Fim Daos

		Cliente cliente1 = new Cliente();
		cliente1.setNome("Lucas");
		cliente1.setCpf("01127022016");
		clienteDao.cadastrar(cliente1);

		Pedido pedido1 = new Pedido();
		pedido1.setCliente(cliente1);
		pedido1.setDescricao("pedido inicial do lucas");
		pedido1.setNome("pedido 1");
		pedidoDao.cadastrar(pedido1);

		// pegando uma lista de produtos da base
		Produto produto1 = produtoDao.buscarPorId(1L);
		Produto produto2 = produtoDao.buscarPorId(2L);
		Produto produto3 = produtoDao.buscarPorId(3L);
		
		RelacionamentoPedidoProduto rel1 = new RelacionamentoPedidoProduto(3, pedido1, produto1);
		RelacionamentoPedidoProduto rel2 = new RelacionamentoPedidoProduto(10, pedido1, produto2);
		RelacionamentoPedidoProduto rel3 = new RelacionamentoPedidoProduto(12, pedido1, produto3);
		relacionamentoPedidoProdutoDao.cadastrar(rel1);
		relacionamentoPedidoProdutoDao.cadastrar(rel2);
		relacionamentoPedidoProdutoDao.cadastrar(rel3);
		
		//mapeamento bidirecional
		pedido1.adicionarItem(rel1);
		pedido1.adicionarItem(rel2);
		pedido1.adicionarItem(rel3);
		
		em.getTransaction().commit();
		em.close();
		
		//------------------------------------------------------------------
		
		EntityManager em2 = JPAUtil.getEntityManager();
		PedidoDao pedidoDao2 = new PedidoDao(em2);
		
		//Trazendo o pedido 1
		Pedido pedido1RecuperadoBancoDados = pedidoDao2.buscarPorId(1L);
		
		//Traz a lista do relacionamento inverso carregada
		List<RelacionamentoPedidoProduto> notaFiscal = pedido1RecuperadoBancoDados.getRelacionamentoPedidoProduto();
		

		Thread.sleep(99999);
	}

}
