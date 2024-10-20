package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto {

	private String nomeAutor;
	private Integer numeroDePaginas;

	public Livro() {

	}

	public Livro(String nomeAutor, Integer numeroDePaginas) {
		super();
		this.nomeAutor = nomeAutor;
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

}
