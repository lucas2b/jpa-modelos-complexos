package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Item_Pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal precoUnitario;
	private int quantidade;

	@ManyToOne
	private Pedido pedido;

	@ManyToOne
	private Produto produto;

	public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
	}

	public ItemPedido() {

	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}