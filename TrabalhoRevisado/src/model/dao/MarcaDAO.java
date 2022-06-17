package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Marca;
import java.util.ArrayList;

public class MarcaDAO {
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
                //Objeto marca sendo preenchido pelo resultSet obtido
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
    
    public ArrayList<Marca> buscaMarcas(){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Marca> marcas = new ArrayList<Marca>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mar_marca");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Marca marca = new Marca();
                marca.setCod(rs.getInt("mar_cod"));
                marca.setNome(rs.getString("mar_nome"));
                
                marcas.add(marca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return marcas;
    }
    
    public void InserirMarca(Marca marca){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO mar_marca (mar_nome) VALUES (?)");
            stmt.setString(1, marca.getNome());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
            
        }
    }
    
    public void AlterarMarca(Marca marca){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE loja_tenis.mar_marca SET mar_nome = ? WHERE (mar_cod = ?);");
            stmt.setString(1, marca.getNome());
            stmt.setString(2, Integer.toString(marca.getCod()));
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
            
        }
    }
}
