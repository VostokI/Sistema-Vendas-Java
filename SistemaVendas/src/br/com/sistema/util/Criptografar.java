/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Alunos
 */
public class Criptografar {
            
    public static void main(String args[]) throws Exception {
    String senha = "Minha senha secreta";
    System.out.println(gerarHash(senha));
    }

    public static String gerarHash(String senha) throws Exception {
    MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
    byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));

    StringBuilder texto = new StringBuilder();
    for (byte b : hash) {
      texto.append(String.format("%02X", 0xFF & b));
    }

    return texto.toString();
    }
        
    public String criptografarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();

        return senhahex;
    }
}
