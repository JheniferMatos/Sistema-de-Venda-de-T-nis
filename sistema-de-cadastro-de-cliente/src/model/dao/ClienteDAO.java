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
            stmt = con.prepareStatement("INSERT INTO cliente (nome, cpf, cidade, estado, logradoro, numero, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getCidade());
            stmt.setString(4, cliente.getEstado());
            stmt.setString(5, cliente.getLogradouro());
            stmt.setString(6, cliente.getNumero());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getTelefone());
            stmt.setString(9, cliente.getEmail());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> buscarCliente(String nome){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome=?");
            stmt.setString(1, nome);
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
    
    public void exluirCliente(Cliente cliente){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            stmt.setString(1, cliente.getId());
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
            stmt = con.prepareStatement("UPTADE cliente SET nome = ?, cpf = ?, cidade = ?, estado = ?, logradouro = ?, numero = ?, cep = ?, telefone = ?, email = ? WHERE idcliente = ?;");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getCidade());
            stmt.setString(4, cliente.getEstado());
            stmt.setString(5, cliente.getLogradouro());
            stmt.setString(6, cliente.getNumero());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getTelefone());
            stmt.setString(9, cliente.getEmail());
            stmt.setString(10, cliente.getId());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(null, "Erro na alteracao", ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
}
