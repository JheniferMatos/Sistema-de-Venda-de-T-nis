package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Cliente;

public class ClienteDAO {
    public Cliente buscaClienteCod(int Cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cli_cliente WHERE cli_cod = ?");
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            
            while(rs.next()){           
                //Objeto cliente sendo preenchido pelo resultSet obtido
                cliente.setCod(rs.getInt("cli_cod"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setLogradouro(rs.getString("cli_logradouro"));
                cliente.setNumero(rs.getInt("cli_numero"));
                cliente.setCidade(rs.getString("cli_cidade"));
                cliente.setEstado(rs.getString("cli_estado"));
                cliente.setBairro(rs.getString("cli_bairro"));
                cliente.setCep(rs.getString("cli_cep"));
                cliente.setEmail(rs.getString("cli_email"));
            }               
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return cliente;
    }
    public Cliente buscaClienteCpf(String cpf){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cli_cliente WHERE cli_cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            
            
            while(rs.next()){           
                //Objeto cliente sendo preenchido pelo resultSet obtido
                cliente.setCod(rs.getInt("cli_cod"));
                cliente.setNome(rs.getString("cli_nome"));
                cliente.setCpf(rs.getString("cli_cpf"));
                cliente.setLogradouro(rs.getString("cli_logradouro"));
                cliente.setNumero(rs.getInt("cli_numero"));
                cliente.setCidade(rs.getString("cli_cidade"));
                cliente.setEstado(rs.getString("cli_estado"));
                cliente.setBairro(rs.getString("cli_bairro"));
                cliente.setCep(rs.getString("cli_cep"));
                cliente.setEmail(rs.getString("cli_email"));
            }               
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return cliente;
    }
}
