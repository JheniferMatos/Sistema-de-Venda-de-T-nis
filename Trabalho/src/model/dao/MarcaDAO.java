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
import model.bean.Marca;

public class MarcaDAO {
    public List<Marca> buscarMarca(){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Marca> marcas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mar_marca");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Marca m = new Marca();
                
                m.setCod(rs.getInt("mar_cod"));
                m.setNome(rs.getString("mar_nome"));

                
                marcas.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return marcas;
    }
    
    public Marca buscaMarcaCod(int Cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Marca marca = new Marca();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mar_marca WHERE mar_cod = ?");
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            
            while(rs.next()){           
                //Objeto cliente sendo preenchido pelo resultSet obtido
                marca.setCod(rs.getInt("mar_cod"));
                marca.setNome(rs.getString("mar_nome"));
            }               
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return marca;
    }
    
    public void inserirMarca(Marca marca){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO mar_marca (mar_nome) VALUES (?)");
            stmt.setString(1, marca.getNome());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void alterarMarca(Marca marca){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE loja_tenis.mar_marca SET mar_nome = ? WHERE (mar_cod = ?);");
            stmt.setString(1, marca.getNome());
            stmt.setString(2, Integer.toString(marca.getCod()));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(null, "Erro na alteracao", ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void excluirMarca(int mar_cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM mar_marca WHERE mar_cod = ?");
            stmt.setInt(1, mar_cod);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
}
