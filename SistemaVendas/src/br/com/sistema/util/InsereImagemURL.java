/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class InsereImagemURL {

    public void emLabel(JLabel label, String link){
        if(link != null){
            Image image = null;

            try{
                URL url = new URL(link);
                         
                BufferedImage buffImg = ImageIO.read(url);
                image = buffImg.getScaledInstance(150, 200, Image.SCALE_SMOOTH);

                label.setIcon(new ImageIcon(image));
            }catch (IOException e){
                JOptionPane.showMessageDialog(null, "Não foi possível carregar a imagem!");
                System.err.println("Erro: " + e);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "NULL - Não foi possível carregar a imagem!");
                System.err.println("Erro: " + e);
            }
            
        }else{
            label.setIcon(null);
        }
    }
}
