
package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Telefone;

public class TelefoneDAO {
    public ArrayList<Telefone> buscarTelefonesClientes(int codigoCliente){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Telefone> telefones = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM TCL_TELEFONES_CLIENTES TCL WHERE TCL.TCL_CLIENTE = ?");
            stmt.setString(1, Integer.toString(codigoCliente));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int nCodigo = rs.getInt("TCL_COD");
                String nNumero = rs.getString("TCL_NUMERO");
                String nDescricao = rs.getString("TCL_DESCRICAO");
                Telefone t = new Telefone(nCodigo, nNumero, nDescricao);
        
                telefones.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return telefones;
    }
    
    public ArrayList<Telefone> buscarTelefonesFuncionarios(int codigoFuncionario){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Telefone> telefones = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM TFU_TELEFONES_FUNCIONARIOS TFU WHERE TFU.TFU_FUNCIONARIO = ?");
            stmt.setString(1, Integer.toString(codigoFuncionario));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int nCodigo = rs.getInt("TFU_COD");
                String nNumero = rs.getString("TFU_NUMERO");
                String nDescricao = rs.getString("TFU_DESCRICAO");
                Telefone t = new Telefone(nCodigo, nNumero, nDescricao);
        
                telefones.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return telefones;
    }
    
    public Telefone buscaTelefoneCod(int Cod){
        return new Telefone(0, "", "");
    }
    
    public void inserirTelefone(Telefone telefone){
    }
    
    public void alterarTelefone(Telefone telefone){

    }
    
    public void excluirTelefone(int ven_cod){
    }
}
