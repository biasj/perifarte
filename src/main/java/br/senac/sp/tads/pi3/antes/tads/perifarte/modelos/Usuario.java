/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author beatrizsato
 */
public abstract class Usuario {
    private static int contas = 10000;
    protected int numeroConta;
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario(String nome, String email, String senhaAberta) {
        this.nome = nome;
        this.email = email;
        setSenha(senhaAberta);
        
        numeroConta = contas++;
    }
    
    public Usuario(String email, String senhaHash) {
        this.email = email;
        senha = senhaHash;
    } 
        
    public int getNumeroConta() {
        return numeroConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
    public final void setSenha(String senhaAberta) {
        this.senha = BCrypt.hashpw(senhaAberta, BCrypt.gensalt());
        //this.hashSenha = senhaAberta;
    }
    
    public boolean validarSenha(String senhaAberta) {
        return BCrypt.checkpw(senhaAberta, senha);
        //return senhaAberta.equals(hashSenha);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // redefine a senha
    public void redefinirSenha(String novaSenha) {
        senha = novaSenha;
    }
}
