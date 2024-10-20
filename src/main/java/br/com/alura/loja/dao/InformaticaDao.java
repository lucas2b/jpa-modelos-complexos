package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Informatica;

public class InformaticaDao {

	EntityManager em;

	public InformaticaDao(EntityManager em) {
		super();
		this.em = em;
	}

	public InformaticaDao() {

	}

	public void cadastrar(Informatica produtoInformatico) {
		this.em.persist(produtoInformatico);
	}

}
