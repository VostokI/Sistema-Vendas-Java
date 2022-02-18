/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.verifications;
import br.com.sistema.dao.FornecedoresDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuário
 */
public class CnpjVerification {
    private String erro;

    public String getErro() {
        return erro;
    }
        
        
    //public class CnpjVerification {
    public boolean isCNPJ(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00.000.000/0000-00") || CNPJ.equals("11.111.111/1111-11") ||
            CNPJ.equals("22.222.222/2222-22") || CNPJ.equals("33.333.333/3333-33") ||
            CNPJ.equals("44.444.444/4444-44") || CNPJ.equals("55.555.555/5555-55") ||
            CNPJ.equals("66.666.666/6666-66") || CNPJ.equals("77.777.777/7777-77") ||
            CNPJ.equals("88.888.888/8888-88") || CNPJ.equals("99.999.999/9999-99") ||
           (CNPJ.length() != 18)){
            
            this.erro = "CNPJ não pode ter todos os digitos repetidos!";
            return(false);
        }
           

        char dig17, dig18;
        int sm, i, r, num, peso;

    // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
    // 1° Digito Verificador
            sm = 0;
            peso = 2;
            for (i=14; i>=0; i--) {

            if(i==2 || i==6 || i==10){
                continue;
            }
    // converte o i-ésimo caractere do CNPJ em um número:
    // por exemplo, transforma o caractere '0' no inteiro 0
            num = (int)(CNPJ.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig17 = '0';
            else dig17 = (char)((11-r) + 48);

    // 2° Digito Verificador
            sm = 0;
            peso = 2;
            for (i=16; i>=0; i--) {
                
                if(i==2 || i==6 || i==10 || i==15){
                    continue;
                }
                
                num = (int)(CNPJ.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig18 = '0';
            else dig18 = (char)((11-r) + 48);

    // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig17 == CNPJ.charAt(16)) && (dig18 == CNPJ.charAt(17)))
                return(true);
            else{
                this.erro = "CNPJ inválido!";
                return(false);
            }
            
        } catch (InputMismatchException erro) {
            this.erro = "CNPJ inválido!";
            return(false);
        }
    }

    
    public boolean cnpjExiste(String cnpj){
        String cnpjBanco;
        
        FornecedoresDAO dao = new FornecedoresDAO();
                
        cnpjBanco = dao.findCnpjFornecedor(cnpj);
                
        if(cnpjBanco.equals(cnpj)){
            this.erro = "CNPJ já existe no banco!";
            return true;

        }else if(cnpjBanco.equals("")){
            return false;
        }else{
            this.erro = "Algo estranho aconteceu!";
            return true;
        }
    }
    
    public boolean isCNPJEdicao(String id, String cnpj){
        try{
            FornecedoresDAO dao = new FornecedoresDAO();


            String cnpjBanco = dao.findCnpjById(id);

            if(cnpjBanco.equals(cnpj)){
                return true;
            }

            return false;
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro no banco de dados!");
            return true;
        }
    }
}
