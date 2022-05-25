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
    public void inserirModelo(Modelo modelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO mte_modelo_tenis (mte_descricao, mte_marca, mte_preco) VALUES (?, ?, ?)");
            stmt.setString(1, modelo.getDesc());
            stmt.setString(2, Integer.toString(modelo.getCodMarca()));
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
