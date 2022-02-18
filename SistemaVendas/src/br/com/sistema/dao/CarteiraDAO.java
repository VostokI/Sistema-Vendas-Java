package br.com.sistema.dao;

import br.com.sistema.jdbc.ConnectionFactory;
import br.com.sistema.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import br.com.sistema.model.Carteira;

public class CarteiraDAO {

    private Connection con;

    public CarteiraDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public Carteira findCarteiraById(int id){
        try{
            Carteira carteira = new Carteira();

            String sql = "SELECT id, saldo, cliente_id from tb_carteira where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(id));
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                carteira.setClienteId(rs.getInt("cliente_id"));
                carteira.setId(id);
                carteira.setSaldo(rs.getDouble("saldo"));
            }
            
            return carteira;
            
        } catch (SQLException erro) {

        JOptionPane.showMessageDialog(null, "Erro :" + erro);
        return null;
        }
    }
    
    public Carteira findCarteiraByClientId(int client_id){
        try{
            Carteira carteira = new Carteira();

            String sql = "SELECT id, saldo, cliente_id from tb_carteira where cliente_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(client_id));
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                carteira.setClienteId(client_id);
                carteira.setId(rs.getInt("id"));
                carteira.setSaldo(rs.getDouble("saldo"));
            }
            
            return carteira;
            
        } catch (SQLException erro) {

        JOptionPane.showMessageDialog(null, "Erro :" + erro);
        return null;
        }
    }
    
    public void AlterarSaldo(int id, double saldo){
        try{
            Carteira carteira = new Carteira();

            String sql = "UPDATE tb_carteira SET saldo = ? WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Double.toString(saldo));
            stmt.setString(2, Integer.toString(id));
            
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {

        JOptionPane.showMessageDialog(null, "Erro :" + erro);
        }
    }
    
    public List<Vendas> listarTransacoesPorCliente(int cliente_id) {

        try {
            //1 passo criar a lista
            List<Vendas> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            //esse é o comando sql de listar vendas por período! tem que mudar depois!"
            String sql = "select v.id , v.data_venda, v.total_venda, v.observacoes  from tb_vendas as v"
                            + " where v.cliente_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(cliente_id));
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Vendas venda = new Vendas();
                
                //Aqui não vai funcionar até criar carteira no banco de dados
                //esses ~v eram da tabela Vendas, tem que mudar
               
                venda.setData_venda(rs.getString("data_venda"));
                venda.setTotal_venda(rs.getDouble("v.total_venda"));
                venda.setObs(rs.getString("v.observacao"));
                venda.setId(rs.getInt("v.id"));


                lista.add(venda);
                //fazer o return da lista
            }
            
            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
}