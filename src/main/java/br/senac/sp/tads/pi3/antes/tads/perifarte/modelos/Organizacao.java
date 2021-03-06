/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beatrizsato
 */
public class Organizacao extends Usuario {
    private int id;
    
    private String cnpj; // somente números 27326531000125 para poder verificar
    private String telefone;
    private String descricao;
    private String justificativa;
    
    private String status;
    private Double totalRecebido;
    
    private ArrayList<Obra> obraDoada;
    
    public Organizacao(String nome, String email, String senha, String cnpj, String telefone) {
        super(nome, email, senha);
        this.telefone = telefone;
        this.cnpj = cnpj;
        descricao = null;
        justificativa = null;
        // já começa como pendente e só depois vai ser aprovado
        status = "pendente";
        totalRecebido = 0.0;
    }
    
    public Organizacao(String email, String hashSenha) {
        super(email, hashSenha);
    }
    
    public void setInfo(int id, String nome, String cnpj, String telefone, 
            String descricao, String justificativa, String status) {
        setId(id);
        setNome(nome);
        this.cnpj = cnpj;
        setTelefone(telefone);
        setDescricao(descricao);
        setJustificativa(justificativa);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public String getJustificativa() {
        return justificativa;
    }
    
    public String getDescricao() {
        return descricao;
    }    

    public Double getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(Double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    public String getStatus() {
        return status;
    }
    
    public ArrayList<Obra> getObraDoada() {
        return obraDoada;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setStatus(String statusCadastral) {
        this.status = statusCadastral;
    }
    
    public void setObraDoada(ArrayList<Obra> obraDoada) {
        this.obraDoada = obraDoada;
    }
    
    // a ideia é que valide o cnpj antes de mandar construir
    private boolean validarCnpj(String cnpj) {
        String[] cnpjNumero = cnpj.split("");
        int[] digitoVerificador = new int[2];
        
        // código do wikipedia para verificar o cnpj
        digitoVerificador[0] = 5*Integer.parseInt(cnpjNumero[0]) + 4*Integer.parseInt(cnpjNumero[1]) + 3*Integer.parseInt(cnpjNumero[2]) + 2*Integer.parseInt(cnpjNumero[3]);
        digitoVerificador[0] += 9*Integer.parseInt(cnpjNumero[4]) + 8*Integer.parseInt(cnpjNumero[5]) + 7*Integer.parseInt(cnpjNumero[6]) + 6*Integer.parseInt(cnpjNumero[7]);
        digitoVerificador[0] += 5*Integer.parseInt(cnpjNumero[8]) + 4*Integer.parseInt(cnpjNumero[9]) + 3*Integer.parseInt(cnpjNumero[10]) + 2*Integer.parseInt(cnpjNumero[11]);
        digitoVerificador[0] = 11 - digitoVerificador[0]%11;
        
        if(digitoVerificador[0] >= 10) {
            digitoVerificador[0] = 0;
        }
        
        digitoVerificador[1] = 6*Integer.parseInt(cnpjNumero[0]) + 5*Integer.parseInt(cnpjNumero[1]) + 4*Integer.parseInt(cnpjNumero[2]) + 3*Integer.parseInt(cnpjNumero[3]);
        digitoVerificador[1] += 2*Integer.parseInt(cnpjNumero[4]) + 9*Integer.parseInt(cnpjNumero[5]) + 8*Integer.parseInt(cnpjNumero[6]) + 7*Integer.parseInt(cnpjNumero[7]);
        digitoVerificador[1] += 6*Integer.parseInt(cnpjNumero[8]) + 5*Integer.parseInt(cnpjNumero[9]) + 4*Integer.parseInt(cnpjNumero[10]) + 3*Integer.parseInt(cnpjNumero[11]);
        digitoVerificador[1] += 2*Integer.parseInt(cnpjNumero[12]);
        digitoVerificador[1] = 11 - digitoVerificador[1]%11;
        
        if(digitoVerificador[1] >= 10) {
            digitoVerificador[1] = 0;
        }
        
        return Integer.parseInt(cnpjNumero[12]) == digitoVerificador[0] && Integer.parseInt(cnpjNumero[13]) == digitoVerificador[1];
    }  
}
