/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author marce_000
 */
public class Doacao {
    private int idCompra;
  //  private double totalDoado; //este valor é para o total doado
    private int idDoador; // somente números 27326531000125 para poder verificar
    private int idObra;
    private int idOrganizacao;
    private LocalDate data_compra;
    private String status;
    private BigDecimal valor; // este valor é para o quanto será doado em cada compra individual feita

 

    public Doacao(int idDoador, int idObra, int organizacao, BigDecimal valor) {
    	this.idDoador = idDoador;
    	this.idObra = idObra;
    	this.idOrganizacao = organizacao;
    	this.valor = valor;
        data_compra = LocalDate.now();
        status = "pendente";
    }
    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int id) {
        this.idCompra = id;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(int idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public LocalDate getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDate data_compra) {
        this.data_compra = data_compra;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public BigDecimal getValor() {
 		return valor;
 	}

 	public void setValor(BigDecimal valor) {
 		this.valor = valor;
 	}
 	
}
