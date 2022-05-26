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
import model.bean.Modelo;

public class ModeloDAO {
    public List<Marca> buscarModelo(){
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
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return marcas;
    }
    
    public Modelo buscaModeloCod(int Cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MarcaDAO mdao = new MarcaDAO();
        Modelo modelo = new Modelo();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mte_modelo_tenis WHERE mte_cod = ?");
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            
            while(rs.next()){           
                //Objeto cliente sendo preenchido pelo resultSet obtido
                modelo.setCod(rs.getInt("mte_cod"));
                modelo.setDesc(rs.getString("mte_descricao"));
                modelo.setMarca(mdao.buscaMarcaCod(rs.getInt("mte_marca")));
                modelo.setPreco(rs.getFloat("mte_preco"));
            }               
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return modelo;
    }
    
    public void inserirModelo(Modelo modelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO mte_modelo_tenis (mte_descricao, mte_marca, mte_preco) VALUES (?, ?, ?)");
            stmt.setString(1, modelo.getDesc());
            stmt.setString(2, Integer.toString(modelo.getMarca().getCod()));
            stmt.setString(3, Float.toString(modelo.getPreco()));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
}
