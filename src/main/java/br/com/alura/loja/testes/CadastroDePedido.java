package br.com.alura.loja.testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.h2.tools.Server;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
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
		// Fim Daos

		Cliente cliente1 = new Cliente();
		cliente1.setNome("Lucas");
		cliente1.setCpf("01127022016");
		clienteDao.cadastrar(cliente1);

		Pedido pedido1 = new Pedido();
		pedido1.setCliente(cliente1);
		pedido1.setDescricao("pedido inicial do lucas");
		pedido1.setNome("pedido 1");

		// pegando uma lista de produtos da base
		Produto produto1 = produtoDao.buscarPorId(1L);
		Produto produto2 = produtoDao.buscarPorId(2L);
		Produto produto3 = produtoDao.buscarPorId(3L);
		
		List<Produto> listaProdutos = new ArrayList<Produto>();
		listaProdutos.add(produto1);
		listaProdutos.add(produto2);
		listaProdutos.add(produto3);
		
		pedido1.setListaProdutosPedido(listaProdutos);
		
		pedidoDao.cadastrar(pedido1);
		
		em.getTransaction().commit();
		

		Thread.sleep(99999);
	}

}
