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
public class Vendas {
    
    //Atributos
    private int id;
    private Clientes cliente;
    private String data_venda;
    private double total_venda;
    private String obs;
    private boolean cashback;
    
    //getters e setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public double getTotal_venda() {
        return total_venda;
    }

    public void setTotal_venda(double total_venda) {
        this.total_venda = total_venda;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    public boolean getCashback() {
        return this.cashback;
    }

    public void setCashback(boolean cashback) {
        this.cashback = cashback;
    }
    
    public void setClienteId(int id){
        this.cliente.setId(id);
    }
    
    public int getClienteId(){
        return this.cliente.getId();
    }
}
