/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConnectionFactory;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tampelini
 */
public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Cadastrar Venda
    public void cadastrarVenda(Vendas obj) {
        try {
            String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda, observacoes, cashback) values (?, ?, ?, ?, FALSE)";
            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro : " + erro);

        }

    }

    //Retorna a ultima venda
    public int retornaUltimaVenda() {
        try {
            int idvenda = 0;

            String sql = "select max(id) id from tb_vendas";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();

                p.setId(rs.getInt("id"));
                idvenda = p.getId();
            }

            return idvenda;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Metodo que filtra Vendas por Datas
    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim) {
        try {

            //1 passo criar a lista
            List<Vendas> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select v.id ,  date_format(v.data_venda,'%d/%m/%Y') as data_formatada , c.nome, v.total_venda, v.observacoes, v.cashback  from tb_vendas as v  "
                    + " inner join tb_clientes as c on(v.cliente_id = c.id) where v.data_venda BETWEEN ? AND ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));
                obj.setCashback(rs.getBoolean("v.cashback"));

                obj.setCliente(c);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }

    //Metodo que calcula total da venda por data
    public double retornaTotalVendaPorData(LocalDate data_venda) {
        try {

            double totalvenda = 0;

            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data_venda.toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalvenda = rs.getDouble("total");
            }

            return totalvenda;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Vendas findVendasById(int id){
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from tb_vendas where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(id));

            ResultSet rs = stmt.executeQuery();
            Vendas obj = new Vendas();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setData_venda(rs.getString("data_venda"));
                obj.setObs(rs.getString("observacoes")); 
                obj.setTotal_venda(rs.getDouble("total_venda"));
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("cliente_id"));
                obj.setCliente(cliente);
                obj.setCashback(rs.getBoolean("cashback"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Venda não encontrada!");
            return null;
        }
    }

    public List<Vendas> findVendasByClientId(int id){
        try {
            List<Vendas> lista = new ArrayList<>();
            
            //1 passo - criar o sql , organizar e executar.
            String sql = "SELECT * FROM tb_vendas WHERE cliente_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(id));

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Vendas obj = new Vendas();
                
                
                obj.setId(rs.getInt("id"));
                
                obj.setData_venda(rs.getString("data_venda"));
                obj.setObs(rs.getString("observacoes"));
                obj.setTotal_venda(rs.getDouble("total_venda"));
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("cliente_id"));
                obj.setCliente(cliente);
                obj.setCashback(rs.getBoolean("cashback"));
                
                lista.add(obj);
            }

            return lista;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
    
    public void executaCashback(int id){
        try{
            String sql = "UPDATE tb_vendas SET cashback = true WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, Integer.toString(id));
            
            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {

        JOptionPane.showMessageDialog(null, "Erro :" + erro);
        }
    }
}
