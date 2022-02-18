/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.verifications;

import br.com.sistema.model.Endereco;
import br.com.sistema.util.WebServiceCep;

/**
 *
 * @author Fábio Luis Boni
 */
public class CepVerification {
    
    private String erro;

    public String getErro() {
        return erro;
    }
    
    public boolean isCEP(String cep){
        if(cep.equals("     -   ")){
            this.erro = "O CEP não deve estar vazio!";
            return false;
        }
        
        for(int i = 0; i < 9; i++){
            if(i==5){
                if(cep.charAt(i) != '-'){
                    this.erro = "O separador dos dígitos dever ser um caracter '-'";
                    return false;
                }
                
                continue;
            }
            
            if(Character.isDigit(cep.charAt(i)) == false){
                this.erro = "O CEP deve conter apenas caracteres numéricos!";
                return false;
            }
        }
        
        return true;
    }
    
    public Endereco buscaCepWS(String cep){
        WebServiceCep wsCEP = WebServiceCep.searchCep(new StringBuffer(cep).deleteCharAt(5).toString());
        
        
        Endereco endereco = new Endereco();
        
        if(wsCEP.wasSuccessful()){
            endereco.setUf(wsCEP.getUf());
            endereco.setCidade(wsCEP.getCidade());
            endereco.setBairro(wsCEP.getBairro());
            endereco.setLogradouro(wsCEP.getLogradouroFull());
            
            return endereco;
        }else{
            this.erro = "Erro numero: " + wsCEP.getResulCode() + "\nDescrição do erro: " + wsCEP.getResultText();
            return null;
        }
    }
}
