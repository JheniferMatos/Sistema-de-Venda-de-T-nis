
package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /*public Modelo buscaTelefoneCod(int Cod){
        return new Telefone(0, "", "");
    }
    
    public void inserirTelefone(Telefone telefone){
    }
    
    public void alterarTelefone(Telefone telefone){

    }
    
    public void excluirTelefone(int ven_cod){
    }*/
}
