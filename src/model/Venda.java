
package model;

import java.util.Date;

public class Venda {
    private int id;
    private Cliente cliente;
    private Produto produto;
    private Usuario usuario;
    private Date dataVenda;
    private int quantidade;
    private float valorTotal;

    // Construtor da classe Venda
    public Venda(int id, Cliente cliente, Produto produto, Usuario usuario, Date dataVenda, int quantidade, float valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.usuario = usuario;
        this.dataVenda = dataVenda;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}