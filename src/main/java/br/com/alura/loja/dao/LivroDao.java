package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Livro;

public class LivroDao {
	
	EntityManager em;

	public LivroDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Livro livro) {
		em.persist(livro);
	}
	
	public Livro buscarPorId(Integer id) {
		return this.em.find(Livro.class, id);
	}
	
	

}
