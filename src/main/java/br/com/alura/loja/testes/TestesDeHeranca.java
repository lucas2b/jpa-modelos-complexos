package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.h2.tools.Server;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.InformaticaDao;
import br.com.alura.loja.dao.LivroDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Informatica;
import br.com.alura.loja.modelo.Livro;
import br.com.alura.loja.util.JPAUtil;

public class TestesDeHeranca {

	public static void main(String[] args) throws SQLException, InterruptedException {
		Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		CategoriaDao categoriaDao = new CategoriaDao(em);
		LivroDao livroDao = new LivroDao(em);
		InformaticaDao informaticaDao = new InformaticaDao(em);
		
		Categoria categoriaLivro = new Categoria("LIVROS");
		categoriaDao.cadastrar(categoriaLivro);
		
		Livro livro = new Livro();
		livro.setCategoria(categoriaLivro);
		livro.setNome("Harry Potter");
		livro.setDescricao("A pedra filosofal");
		livro.setNomeAutor("JK Rowling");
		livro.setNumeroDePaginas(350);
		livro.setPreco(new BigDecimal(100));
		livroDao.cadastrar(livro);
		
		Categoria categoriaInformatica = new Categoria("INFORMÁTICA");
		categoriaDao.cadastrar(categoriaInformatica);
		
		Informatica produtoInformatico = new Informatica();
		produtoInformatico.setCategoria(categoriaInformatica);
		produtoInformatico.setNome("Notebook");
		produtoInformatico.setModelo("V14");
		produtoInformatico.setMarca("Lenovo");
		produtoInformatico.setDescricao("Notebook empresarial");
		produtoInformatico.setPreco(new BigDecimal(4000));
		informaticaDao.cadastrar(produtoInformatico);
		
		em.getTransaction().commit();
		
		Thread.sleep(9999);
	}

}
