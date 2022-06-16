package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Modelo;
import model.bean.ModeloVendido;

public class ModeloVendidoDAO {
    public ArrayList<ModeloVendido> buscarModelosVendidos(int codigoVenda){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<ModeloVendido> modelos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM MVE_MODELO_VENDIDO WHERE MVE_VENDA = ?");
            stmt.setString(1, Integer.toString(codigoVenda));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ModeloVendido mve = new ModeloVendido();
                ModeloDAO mDAO = new ModeloDAO();
                VendaDAO vDAO = new VendaDAO();
                
                mve.setCod(rs.getInt("MVE_COD"));
                mve.setVenda(vDAO.buscaVenda(rs.getInt("MVE_VENDA")));
                mve.setModelo(mDAO.buscaModeloCod(rs.getInt("MVE_MODELO")));
                
                modelos.add(mve);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return modelos;
    }
    public ModeloVendido buscaModeloVendido(int codigoModeloVendido){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ModeloVendido modelo = new ModeloVendido();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM MVE_MODELO_VENDIDO WHERE MVE_COD = ?");
            stmt.setString(1, Integer.toString(codigoModeloVendido));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ModeloDAO mDAO = new ModeloDAO();
                VendaDAO vDAO = new VendaDAO();
                
                modelo.setCod(rs.getInt("MVE_COD"));
                modelo.setVenda(vDAO.buscaVenda(rs.getInt("MVE_VENDA")));
                modelo.setModelo(mDAO.buscaModeloCod(rs.getInt("MVE_MODELO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return modelo;
    }
}
