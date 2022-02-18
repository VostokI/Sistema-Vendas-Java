/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.verifications;

import br.com.sistema.dao.ClientesDAO;
import br.com.sistema.dao.FuncionariosDAO;
import static java.lang.Character.isDigit;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Fabio Luis Boni
 */
public class CpfVerification {
    
    private String erro;

    public String getErro() {
        return erro;
    }
    
    public boolean isCPF(String cpf){  
        for(int i = 0; i < 13 ;i++){
            
            if(i==3 || i==7 || i==11){
                continue;
            }
            
            if(!isDigit(cpf.charAt(i))){
                this.erro = "O CPF deve conter apenas números!";
                return false;
            }
        }
        
        for(int i = 1; i < 14; i++){
            if(i==3 || i==7 || i==11){
                continue;
            }
            
            if(cpf.charAt(0) != cpf.charAt(i)){
                break;
            }
            
            if(i == 13){
                this.erro = "O CPF não pode conter 11 dígitos repetidos!";
                return false;
            }
        }

        int fator = 0;
        int j = 0;
        for(int i = 0; i < 11; i++){
            if(i==3 || i==7){
                continue;
            }
            
            fator += (10-j) * Character.getNumericValue(cpf.charAt(i));
            j++;
        }
        fator *= 10;
        if(fator % 11 != Character.getNumericValue(cpf.charAt(12))){
            this.erro = "CPF inválido!";
            return false;
        }
        
        j=0;
        fator = 0;
        for(int i = 0; i < 13; i++){
            if(i==3 || i==7 || i==11){
                continue;
            }
            
            fator += (11-j) * Character.getNumericValue(cpf.charAt(i));
            j++;
        }
        fator *= 10;
        if(fator % 11 != Character.getNumericValue(cpf.charAt(13))){
            this.erro = "CPF inválido!";
            return false;
        }
        
        return true;
    }
    
    public boolean cpfExiste(String modo, String cpf){
        String cpfBanco;
                
        if(modo.equals("funcionario")){
            FuncionariosDAO dao = new FuncionariosDAO();
            
            cpfBanco = dao.findCpfFuncionario(cpf);

        }else if(modo.equals("cliente")){
            ClientesDAO dao = new ClientesDAO();
            
            cpfBanco = dao.findCpfCliente(cpf);
            
        }else{
            this.erro = "Modo não identificado!";
            return false;
        }
        
        if(cpfBanco.equals(cpf)){
            this.erro = "CPF já está cadastrado!";
            return true;

        }else if(cpfBanco.equals("")){
            this.erro = "CPF não está cadastrado!";
            return false;
        }else{
            this.erro = "Algo estranho aconteceu!";
            return false;
        }
    }
    
    public boolean isCPFEdicao(String modo, String id, String cpf){
        try{
            String cpfBanco;
            
            if(modo.equals("funcionario")){
                FuncionariosDAO dao = new FuncionariosDAO();

                cpfBanco = dao.findCpfById(id);

            }else if(modo.equals("cliente")){
                ClientesDAO dao = new ClientesDAO();

                cpfBanco = dao.findCpfById(id);

            }else{
                this.erro = "Modo não identificado!";
                return false;
            }

            if(cpfBanco.equals(cpf)){
                this.erro = "CPF já está cadastrado!";
                return true;

            }else if(cpfBanco.equals("")){
                this.erro = "CPF não está cadastrado!";
                return false;
                
            }else{
                this.erro = "Algo estranho aconteceu!";
                return false;
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no banco de dados!");
            return false;
        }
    }
}
