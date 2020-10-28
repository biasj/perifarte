/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.classes;

/**
 *
 * @author marce_000
 */
public class Obra {
    private static int contasObra = 1000;
    private int numeroObra;
    
    private String titulo; 
    private String descricao;
    private String organizacao;
    private Double preco;
    
    private int id;

    public void setId(int id) {
        this.id = id;
    }
    
    public Obra(String titulo, String descricao, double preco) {
        titulo = null;
        descricao = null;
        preco = 0; //começa com o valor zerado.
        numeroObra = contasObra++;
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

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

   
 }
