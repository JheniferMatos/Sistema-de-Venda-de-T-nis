package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {
    public void inserirCliente(Cliente cliente){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> buscarCliente(String cpf){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE cpf='"+cpf+"'");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente c = new Cliente();
                
                c.setId(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                
                clientes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    
    public void exluirCliente(String cpf){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE cpf='"+cpf+"'");
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void alterarCliente(Cliente cliente){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPTADE cliente SET ");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(null, "Erro na alteracao", ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
}
