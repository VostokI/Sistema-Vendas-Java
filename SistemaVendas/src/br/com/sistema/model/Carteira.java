/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;

import br.com.sistema.dao.VendasDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Alunos
 */
public class Carteira {
    private int id;
    private int cliente_id;
    private double saldo;
    
    public Carteira(){
    }
    
    public boolean creditar(double valor){
        if (valor <= 0){
            JOptionPane.showMessageDialog(null, "Valor inválido!");
            return false;
        }
        
        this.saldo += valor;
        return true;
    }
    
    public boolean debitar(double valor){
        if (valor <= 0){
            return false;
        }
        if (this.saldo < valor)
        {
            JOptionPane.showMessageDialog(null, "Não há saldo suficiente na conta");
            return false;
        }
        
        this.saldo -= valor;
        return true;
    }
       
    public double fatorDevolucao(double venda){
        if(venda >= 500){
            return 0.8;
            
        }else if(venda >250 && venda <499){
            return 0.5;
            
        }else {
            return 0.3;
        }
    }
    
    public void cashback (int vendaId){
        VendasDAO vendasDao = new VendasDAO();
        
        Vendas venda = vendasDao.findVendasById(vendaId);
        
        double cashback = (venda.getTotal_venda() * fatorDevolucao(venda.getTotal_venda()));
        
        this.saldo += cashback;
    }
                  
    public double getSaldo(){
        return saldo;
    }
    
    public void setSaldo(double valor){
        this.saldo = valor;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getClienteId() {
        return cliente_id;
    }

    public void setClienteId(int cliente_id) {
        this.cliente_id = cliente_id;
    }
}
