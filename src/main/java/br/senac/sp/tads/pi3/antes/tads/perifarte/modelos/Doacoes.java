/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author marce_000
 */
public class Doacoes {
    private static int numeroDoacao = 1000;
    private int idCompra;
  //  private double totalDoado; //este valor é para o total doado
    private BigDecimal totalDoado; //este valor é para o total doado
    private String doador; // somente números 27326531000125 para poder verificar
    private String nome;
    private String organizacao;
    private Date data_compra;
    private String status;
    private double valor; // este valor é para o quanto será doado em cada compra individual feita

 

    public Doacoes(String doador, String nome, String organizacao, double valor) {
    	this.doador = doador;
    	this.nome = nome;
    	this.organizacao = organizacao;
    	this.valor = valor;
    	this.totalDoado = new BigDecimal(0);
    }
    
    
/**	public Doacoes(int idCompra, double total_doado, String doador, String nome, String organizacao, Double valor) {
        this.doador = doador;
        this.nome = nome;
        this.organizacao = organizacao;
        totalDoado = 0;
        idCompra = numeroDoacao++;
        this.valor = valor;
    }
 */   
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int id) {
        this.idCompra = id;
    }
	
    public String getDoador() {
        return doador;
    }

    public void setDoador(String doador) {
        this.doador = doador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public BigDecimal getTotalDoado() {
        return totalDoado;
    }

    public void setTotalDoado(BigDecimal totalDoado) {
        this.totalDoado = totalDoado;
    }
 
    public String setStatus (String status) {
    	return status;
    }
    
    public String getStatus (String status) {
    	return status;
    }
    
    public double getValor() {
 		return valor;
 	}

 	public void setValor(double valor) {
 		this.valor = valor;
 	}
 	
}
