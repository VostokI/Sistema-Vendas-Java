/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.verifications;

import br.com.sistema.dao.FuncionariosDAO;
import br.com.sistema.model.Funcionarios;
import br.com.sistema.util.Criptografar;
import br.com.sistema.view.FrmFuncionarios;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class AcessoVerification {
    public boolean cpfValido(String entidade, String cpf){
        CpfVerification check = new CpfVerification();
        
        if( ! check.isCPF(cpf) || ! check.cpfExiste(entidade, cpf)){
            JOptionPane.showMessageDialog(null, check.getErro());
            return false;
        }
        
        return true;
    }
    
    public boolean confereSenhaAdm(String senhaDigitada){
        FuncionariosDAO dao = new FuncionariosDAO();
        Criptografar criptografar = new Criptografar();
        
        List<Funcionarios> funcionarios =  dao.findByCargo("Administrador");
        
        String senhaCriptografado = "";
        try{
            senhaCriptografado = criptografar.criptografarSenha(senhaDigitada);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Funcionarios funcionario: funcionarios){
            
            System.out.println("nome: " + funcionario.getNome());
            System.out.println("senha: " + funcionario.getSenha());
            System.out.println("senha digitada: " + senhaCriptografado);
            if(funcionario.getSenha().equals(senhaCriptografado)){
                System.out.println("deu certo");
                return true;
            }
        }
        
        JOptionPane.showMessageDialog(null, "A senha de Administrador está incorreta!");
        return false;
    }
    
    public boolean confereSenhaCPF(String senhaDigitada, String cpf){
        FuncionariosDAO dao = new FuncionariosDAO();
        Criptografar criptografar = new Criptografar();
        
        Funcionarios funcionario =  dao.findByCpf(cpf);
        
        String senhaCriptografado = "";
        try{
            senhaCriptografado = criptografar.criptografarSenha(senhaDigitada);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FrmFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrmFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrmFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        System.out.println("nome: " + funcionario.getNome());
        System.out.println("senha: " + funcionario.getSenha());
        System.out.println("senha digitada: " + senhaCriptografado);
        if(funcionario.getSenha().equals(senhaCriptografado)){
            System.out.println("deu certo");
            return true;
        }
        
        JOptionPane.showMessageDialog(null, "A senha de Funcionário está incorreta!");
        return false;
    }
    
    public boolean senhaValido(String campo, String senha){
        if(senha.equals("")){
            JOptionPane.showMessageDialog(null, "O campo " + campo + " deve ser preenchido");
            return false;
        }
        
        return true;
    }
    
    
}
