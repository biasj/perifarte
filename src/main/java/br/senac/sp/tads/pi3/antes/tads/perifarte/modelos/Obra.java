/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 *
 * @author marce_000
 */
public class Obra {
    private int numeroObra;
    private String titulo; 
    private String descricao;
    private Organizacao organizacao;
    private BigDecimal preco;
    private InputStream conteudoArquivo;
    private byte[] imageBytes;
    double totalArrecadado;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Obra(String pTitulo, String pDescricao, BigDecimal pPreco, InputStream pconteudoArquivo) {
        this.titulo = pTitulo;
        this.descricao = pDescricao;
        this.preco = pPreco; //começa com o valor zerado.
        this.conteudoArquivo = pconteudoArquivo;
        imageBytes = null;
    }

    public double getTotalArrecadado() {
        return totalArrecadado;
    }

    public void setTotalArrecadado(double totalArrecadado) {
        this.totalArrecadado = totalArrecadado;
    }

    public InputStream getConteudoArquivo() {
        return conteudoArquivo;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
    
    public int getNumeroObra() {
        return numeroObra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }
      
    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

 }
