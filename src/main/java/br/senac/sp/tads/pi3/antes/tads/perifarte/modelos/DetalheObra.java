/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

/**
 *
 * @author beatrizsato
 */

// junta a obra e o artista em um objeto só
public class DetalheObra {
    private Obra obra;
    private Artista artista;

    public DetalheObra(Obra obra, Artista artista) {
        this.obra = obra;
        this.artista = artista;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
    
}
