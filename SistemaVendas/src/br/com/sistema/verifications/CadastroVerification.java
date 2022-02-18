/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.verifications;

import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class CadastroVerification {
    
    public boolean nomeValido(String nome){
        if(nome.equals("")){
            JOptionPane.showMessageDialog(null, "Preencha o nome!");
            return false;
        }
        
        return true;
    }
    
    public boolean rgValido(String rg){
        if(rg.equals("  .   .   -  ")){
            JOptionPane.showMessageDialog(null, "Preencha o RG!");
            return false;
        }
        
        return true;
    }
    
    public boolean cpfValido(String entidade, String cpf){
        CpfVerification check = new CpfVerification();
        
        if( ! check.isCPF(cpf) || check.cpfExiste(entidade, cpf)){
            JOptionPane.showMessageDialog(null, check.getErro());
            return false;
        }
        
        return true;
    }
    
    public boolean cpfEdicaoValido(String entidade, String id, String cpf){
        CpfVerification check = new CpfVerification();
        
        if(check.isCPFEdicao(entidade, id, cpf)){
            return true;
        }
        
        if( ! check.isCPF(cpf) || check.cpfExiste(entidade, cpf)){
            JOptionPane.showMessageDialog(null, check.getErro());
            return false;
        }
        
        return true;
    }
    
    public boolean emailValido(String email){
        if(email.equals("")){
            JOptionPane.showMessageDialog(null, "Preencha o email!");
            return false;
        }
        
        return true;
    }
    
    public boolean senhaValido(String senha){
        if(senha.equals("")){
            JOptionPane.showMessageDialog(null, "Preencha a senha!");
            return false;
        }
        
        return true;
    }
    
    public boolean cargoValido(String cargo){
        if(cargo.equals("")){
            JOptionPane.showMessageDialog(null, "Preencha o cargo!");
            return false;
        }
        
        return true;
    }
    
    public boolean contatoValido(String telefone, String celular){
        if(telefone.equals("(  )      -     ") && celular.equals("(  )        -     ")){
            JOptionPane.showMessageDialog(null, "Preencha um celular ou telefone fixo!");
            return false;
        }
        
        return true;
    }
    
    public boolean cepValido(String cep){
        CepVerification check = new CepVerification();
        
        if( ! check.isCEP(cep)){
            JOptionPane.showMessageDialog(null, check.getErro());
            return false;
        }
        
        return true;
    }
    
    public boolean cidadeValido(String cidade){
        if(cidade.equals("")){
            JOptionPane.showMessageDialog(null, "Cidade faz parte do endereço e deve ser preenchido!");
            return false;
        }
        
        return true;
    }
    
    public boolean bairroValido(String bairro){
        if(bairro.equals("")){
            JOptionPane.showMessageDialog(null, "Bairro faz parte do endereço e deve ser preenchido!");
            return false;
        }
        
        return true;
    }
    
    public boolean enderecoValido(String endereco){
        if(endereco.equals("")){
            JOptionPane.showMessageDialog(null, "Preencha o endereco!");
            return false;
        }
        
        return true;
    }
    
    public boolean numeroValido(String numero){
        if(numero.equals("")){
            JOptionPane.showMessageDialog(null, "Número faz parte do endereço e deve ser preenchido!");
            return false;
        }
        
        return true;
    }
    
    public boolean cnpjValido(String cnpj){
        CnpjVerification check = new CnpjVerification();
        
        if( ! check.isCNPJ(cnpj) || check.cnpjExiste(cnpj)){
            JOptionPane.showMessageDialog(null, check.getErro());
            return false;
        }
        
        return true;
    }
    
    public boolean cnpjEdicaoValido(String id, String cnpj){
        CnpjVerification check = new CnpjVerification();
        
        if(check.isCNPJEdicao(id, cnpj)){
            return true;
        }
        
        if( ! check.isCNPJ(cnpj) || check.cnpjExiste(cnpj)){
            JOptionPane.showMessageDialog(null, check.getErro());
            return false;
        }
        
        return true;
    }
    
    public boolean descricaoValido(String descricao){
        if(descricao.equals("")){
            JOptionPane.showMessageDialog(null, "É necessário que a descrição seja preenchida!");
            return false;
        }
        
        return true;
    }
    
    public boolean precoValido(String preco){
        if(preco.equals("")){
            JOptionPane.showMessageDialog(null, "É necessário que o produto tenha um preço!");
            return false;
        }
        
        int dot = 0;
        for(int i = 0; i < preco.length(); i++){
            System.out.println(preco.charAt(i));
            if( ! Character.isDigit(preco.charAt(i))){
                
                if(preco.charAt(i) == '.'){
                    dot++;
                }
                
                if(preco.charAt(i) != '.' || dot >= 2){
                    JOptionPane.showMessageDialog(null, "Apenas serão aceitos caracteres numéricos no preço!");
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean quantidadeValido(String quantidade){
        if(quantidade.equals("")){
            JOptionPane.showMessageDialog(null, "É necessário que o produto tenha uma quantidade!");
            return false;
        }
        
        for(int i = 0; i < quantidade.length(); i++){
            if( ! Character.isDigit(quantidade.charAt(i))){

                JOptionPane.showMessageDialog(null, "Apenas serão aceitos caracteres numéricos no preço!");
                return false;
            }
        }
        
        return true;
    }
}
