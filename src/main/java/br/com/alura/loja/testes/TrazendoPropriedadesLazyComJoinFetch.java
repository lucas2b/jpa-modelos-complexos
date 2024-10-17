package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.CargaDeProdutos;
import br.com.alura.loja.util.JPAUtil;

public class TrazendoPropriedadesLazyComJoinFetch {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		
		CargaDeProdutos.carregarProdutosBaseDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

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

		// ----REALIZANDO O MAPEAMENTO BIDIRECIONAL EM "PEDIDO"
		pedido1.adicionarItem(new ItemPedido(3, pedido1, produto1)); // meio de adição possível pelo "cascade type all"
		pedido1.adicionarItem(new ItemPedido(10, pedido1, produto2)); // dessa maneira, quando o pedido for salvo
		pedido1.adicionarItem(new ItemPedido(12, pedido1, produto3)); // salvará também os ítens de pedido
																		// sem precisar salvar os ítens pedido
																		// individualmente
		pedidoDao.cadastrar(pedido1);
		
		em.getTransaction().commit();
		em.close();
		
		//------------------ CONSULTA SEM CARREGAMENTO DE PROPRIEDADE LAZY ------------------
		
		if (false) {
			EntityManager em2 = JPAUtil.getEntityManager();
			PedidoDao pedidoDao2 = new PedidoDao(em2);

			// Trazendo o pedido sem a presença do Cliente carregado
			Pedido pedidoSemPresencaCliente = pedidoDao2.buscarPorId(1L);
			em2.close();
			System.out.println("Nome cliente no Pedido 1: " + pedidoSemPresencaCliente.getCliente().getNome()); // -->> ocorre erro
		}

		
		//------------------ CONSULTA UTILIZANDO COMANDO JOIN FETCH E CARREGANDO PROPRIEDADE LAZY ------------------
		
		if (true) {
			EntityManager em3 = JPAUtil.getEntityManager();
			PedidoDao pedidoDao3 = new PedidoDao(em3);
			Pedido pedidoComPresencaCliente = pedidoDao3.buscarPedidoComCliente(1L);
			em3.close();

			System.out.println("Nome cliente no Pedido 1: " + pedidoComPresencaCliente.getCliente().getNome()); // sucesso
		}

		Thread.sleep(9999);
	}

}
