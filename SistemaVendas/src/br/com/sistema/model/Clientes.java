/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;

/**
 *
 * @author Tampelini
 */
public class Clientes extends Pessoa{
    
    int carteira_id;
    
    public Clientes(){
    }

    public int getCarteiraId() {
        return carteira_id;
    }

    public void setCarteiraId(int carteira_id) {
        this.carteira_id = carteira_id;
    }
}
