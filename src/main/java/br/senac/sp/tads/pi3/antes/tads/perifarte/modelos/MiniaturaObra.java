/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.tads.pi3.antes.tads.perifarte.modelos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beatrizsato
 */

// pega os ids da tabela obra do bd para achar depois
public class MiniaturaObra {
    private int idObra;
    private int idArtista;

    public MiniaturaObra(int idObra, int idArtista) {
        this.idObra = idObra;
        this.idArtista = idArtista;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }
    
}
