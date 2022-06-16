package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Devolucao;
import model.bean.ModeloVendido;

public class DevolucaoDAO {
    public void inserirDevolucao(Devolucao devolucao){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO dev_devolucao (dev_motivo, dev_modelo_vendido) VALUES (?, ?)");
            stmt.setString(1, devolucao.getMotivo());
            stmt.setString(2, Integer.toString(devolucao.getModeloVendido().getCod()));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Devolução realizada!");
        } catch (SQLException ex) {
            Logger.getLogger(DevolucaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    public boolean verificaModeloVendido(ModeloVendido modeloVen){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM dev_devolucao WHERE dev_modelo_vendido = ?");
            stmt.setString(1, Integer.toString(modeloVen.getCod()));
            rs = stmt.executeQuery();
            
            if(rs.next()){
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return false;
    }
}
