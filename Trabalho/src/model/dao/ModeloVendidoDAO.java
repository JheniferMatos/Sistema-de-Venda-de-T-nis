
package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.ModeloVendido;
import model.bean.Modelo;

public class ModeloVendidoDAO {
    public ArrayList<ModeloVendido> buscarModelosVendidos(int codigoVenda){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<ModeloVendido> modelos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM MVE_VENDA MVE WHERE MVE.MVE_VENDA = ?");
            stmt.setString(1, Integer.toString(codigoVenda));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ModeloDAO mDAO = new ModeloDAO();
                Modelo mod = mDAO.buscaModeloCod(rs.getInt("MVE_MODELO"));
                ModeloVendido mve = new ModeloVendido(mod, rs.getInt("MVE_QUANTIDADE"));
                
                modelos.add(mve);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return modelos;
    }
    
    public void inserirModeloVendido(int codigoVenda, int codigoModelo, int quantidade){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO mve_modelo_vendido (mve_venda, mve_modelo, mve_quantidade) VALUES (?, ?, ?)");
            stmt.setString(1, Integer.toString(codigoVenda));
            stmt.setString(2, Integer.toString(codigoModelo));
            stmt.setString(3, Integer.toString(quantidade));
            
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void alterarModeloVendido(int codigoVenda, int codigoModelo, int quantidade){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE MVE_MODELO_VENDIDO MVE SET MVE.MVE_QUANTIDADE = ? WHERE MVE.MVE_VENDA = ? AND MVE.MVE_MODELO = ? ");
            stmt.setString(1, Integer.toString(quantidade));
            stmt.setString(2, Integer.toString(codigoVenda));
            stmt.setString(3, Float.toString(codigoModelo));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void excluirModeloVendido(int codigoVenda, int codigoModelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM MVE_MODELO_VENDIDO MVE WHERE MVE.MVE_VENDA = ? AND MVE.MVE_MODELO = ? ");
            stmt.setString(1, Integer.toString(codigoVenda));
            stmt.setString(2, Float.toString(codigoModelo));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }        
    }
}
