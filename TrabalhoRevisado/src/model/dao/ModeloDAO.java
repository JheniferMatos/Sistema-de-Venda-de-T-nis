package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Modelo;
import model.bean.Marca;
import java.util.ArrayList;

public class ModeloDAO {
    public ArrayList<Modelo> buscaModelos(int codModelo, Marca marca, String desc){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Modelo> modelos = new ArrayList<>();
        
        //Montar string SQL
        String strSql, condicoes, vazio;
        //SELECT MTE.MTE_COD, MTE.MTE_DESCRICAO, MTE.MTE_PRECO, MAR.MAR_COD, MAR.MAR_NOME FROM MTE_MODELO_TENIS MTE INNER JOIN MAR_MARCA MAR ON MTE.MTE_MARCA = MAR.MAR_COD 
        strSql = "SELECT MTE.MTE_COD, MTE.MTE_DESCRICAO, MTE.MTE_PRECO, MAR.MAR_COD, MAR.MAR_NOME ";
        strSql += "FROM MTE_MODELO_TENIS MTE INNER JOIN MAR_MARCA MAR ON MTE.MTE_MARCA = MAR.MAR_COD ";
        vazio = "";
        condicoes = "";
        
        boolean pesquisaPorCodigo, pesquisaPorMarca, pesquisaPorDescricao;
        pesquisaPorCodigo = codModelo != 0;
        pesquisaPorMarca = marca != null;
        pesquisaPorDescricao = desc.compareTo(vazio) != 0;
        
        if (pesquisaPorCodigo){
            condicoes += "MTE.MTE_COD = " + Integer.toString(codModelo) + " ";
        } 

        if (pesquisaPorMarca){
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            }
            condicoes += "MAR.MAR_COD = " + Integer.toString(marca.getCod()) + " ";
        }

        if (pesquisaPorDescricao){
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            } 
            condicoes += "MTE.MTE_DESCRICAO LIKE '%" + desc + "%' ";
        }
        
        if (condicoes.compareTo(vazio) != 0){
            strSql += " WHERE " + condicoes;
        } 
        
        
        
        try {
            stmt = con.prepareStatement(strSql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Marca ma = new Marca();
                Modelo mo = new Modelo();
                
                ma.setCod(rs.getInt("mar_cod"));
                ma.setNome(rs.getString("mar_nome"));
                
                mo.setCod(rs.getInt("mte_cod"));
                mo.setDesc(rs.getString("mte_descricao"));
                mo.setPreco(rs.getFloat("mte_preco"));
                mo.setMarca(ma);
                
                modelos.add(mo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        return modelos;
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
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return modelo;
    }
  
    public boolean InserirModelo(Modelo modelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        boolean result;
        
        try {
            stmt = con.prepareStatement("INSERT INTO mte_modelo_tenis (mte_descricao, mte_marca, mte_preco) VALUES (?, ?, ?)");
            stmt.setString(1, modelo.getDesc());
            stmt.setString(2, Integer.toString(modelo.getMarca().getCod()));
            stmt.setString(3, Float.toString(modelo.getPreco()));
            
            stmt.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
        return result;
    }
    
    public boolean AlterarModelo(Modelo modelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        boolean result;
        
        try {
            stmt = con.prepareStatement("UPDATE MTE_MODELO_TENIS MTE SET MTE_DESCRICAO = ?, MTE_MARCA = ?, MTE_PRECO = ? WHERE MTE_COD = ?");
            stmt.setString(1, modelo.getDesc());
            stmt.setString(2, Integer.toString(modelo.getMarca().getCod()));
            stmt.setString(3, Float.toString(modelo.getPreco()));
            stmt.setString(4, Integer.toString(modelo.getCod()));
            
            stmt.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
        return result;
    }
    
    public boolean ExcluirModelo(Modelo modelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        boolean result;
        
        try {
            stmt = con.prepareStatement("DELETE FROM mte_modelo_tenis WHERE mte_cod = ?");
            stmt.setInt(1, modelo.getCod());
            stmt.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
        return result;
    }
    
    
}
