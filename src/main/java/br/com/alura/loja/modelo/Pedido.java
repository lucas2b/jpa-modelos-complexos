package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valorTotal;
	private LocalDate data = LocalDate.now();

	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido") //mappedBy a evita criação de uma nova tabela
	private List<RelacionamentoPedidoProduto> relacionamentoPedidoProduto = new ArrayList<RelacionamentoPedidoProduto>();

	public List<RelacionamentoPedidoProduto> getRelacionamentoPedidoProduto() {
		return relacionamentoPedidoProduto;
	}
	
	//maneira de realizar o mapeamento bidirecional
	public void adicionarItem(RelacionamentoPedidoProduto relacionamentoPedidoProduto) {
		relacionamentoPedidoProduto.setPedido(this);
		this.relacionamentoPedidoProduto.add(relacionamentoPedidoProduto);
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
//	@ManyToMany //utilizado quando não há mais atributos além dos Ids
//	private List<Produto> listaProdutosPedido;

//	public List<Produto> getListaProdutosPedido() {
//		return listaProdutosPedido;
//	}
//
//	public void setListaProdutosPedido(List<Produto> listaProdutosPedido) {
//		this.valorTotal = listaProdutosPedido.stream()
//	            .map(Produto::getPreco)
//	            .reduce(BigDecimal.ZERO, BigDecimal::add);
//		this.listaProdutosPedido = listaProdutosPedido;
//	}

}
