package br.com.alura.loja.util;



import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

public class CargaDeProdutos {
	
	public static void carregarProdutosBaseDeDados() {
		
		Categoria categoriaCelulares = new Categoria("CELULARES");
		Categoria categoriaNotebooks = new Categoria("NOTEBOOKS");
		
		Produto p1 = new Produto();
		p1.setCategoria(categoriaCelulares);
		p1.setNome("Galaxy S24");
		p1.setDescricao("Samsung Galaxy S24");
		p1.setPreco(new BigDecimal(4500));
		p1.setCategoria(categoriaCelulares);
		
		Produto p2 = new Produto();
		p2.setCategoria(categoriaCelulares);
		p2.setNome("iPhone 15");
		p2.setDescricao("Apple iPhone 15");
		p2.setPreco(new BigDecimal(7000));

		Produto p3 = new Produto();
		p3.setCategoria(categoriaCelulares);
		p3.setNome("Xiaomi 13 Pro");
		p3.setDescricao("Xiaomi 13 Pro");
		p3.setPreco(new BigDecimal(5500));

		Produto p4 = new Produto();
		p4.setCategoria(categoriaCelulares);
		p4.setNome("Motorola Edge 40");
		p4.setDescricao("Motorola Edge 40");
		p4.setPreco(new BigDecimal(4000));
		
		Produto p5 = new Produto();
		p5.setCategoria(categoriaNotebooks);
		p5.setNome("MacBook Pro 14");
		p5.setDescricao("Apple MacBook Pro 14 com chip M2");
		p5.setPreco(new BigDecimal(12000));

		Produto p6 = new Produto();
		p6.setCategoria(categoriaNotebooks);
		p6.setNome("Dell XPS 13");
		p6.setDescricao("Dell XPS 13 com Intel Core i7 e 16GB RAM");
		p6.setPreco(new BigDecimal(9000));

		Produto p7 = new Produto();
		p7.setCategoria(categoriaNotebooks);
		p7.setNome("Lenovo ThinkPad X1 Carbon");
		p7.setDescricao("Lenovo ThinkPad X1 Carbon, 16GB RAM, 1TB SSD");
		p7.setPreco(new BigDecimal(11000));

		Produto p8 = new Produto();
		p8.setCategoria(categoriaNotebooks);
		p8.setNome("Acer Aspire 5");
		p8.setDescricao("Acer Aspire 5 com AMD Ryzen 7 e 512GB SSD");
		p8.setPreco(new BigDecimal(4500));
		
		
		//cadastro na base
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(categoriaCelulares);
		categoriaDao.cadastrar(categoriaNotebooks);

		produtoDao.cadastrar(p1);
		produtoDao.cadastrar(p2);
		produtoDao.cadastrar(p3);
		produtoDao.cadastrar(p4);
		produtoDao.cadastrar(p5);
		produtoDao.cadastrar(p6);
		produtoDao.cadastrar(p7);
		produtoDao.cadastrar(p8);

		em.getTransaction().commit();
		em.close();		
	}

}
