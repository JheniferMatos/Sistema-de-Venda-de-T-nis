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
    public List<Modelo> buscarModelos(int codigoModelo, int codigoMarca, String descricao){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Modelo> modelos = new ArrayList<>();
        
        //Montar string SQL
        String strSql, condicoes, vazio;
        strSql = "SELECT MTE.MTE_COD, MTE.MTE_DESCRICAO, MTE.MTE_PRECO, MAR.MAR_COD, MAR.MAR_NOME ";
        strSql += "FROM MTE_MODELO_TENIS MTE INNER JOIN MAR_MARCA MAR ON MTE.MTE_MARCA = MAR.MAR_COD ";
        vazio = "";
        condicoes = "";
        
        boolean pesquisaPorCodigo, pesquisaPorMarca, pesquisaPorDescricao;
        pesquisaPorCodigo = codigoModelo != 0;
        pesquisaPorMarca = codigoMarca != 0;
        pesquisaPorDescricao = descricao.compareTo(vazio) != 0;
        
        if (pesquisaPorCodigo){
            condicoes += "MTE.MTE_COD = " + Integer.toString(codigoModelo) + " ";
        } 

        if (pesquisaPorMarca){
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            }
            condicoes += "MAR.MAR_COD = " + Integer.toString(codigoMarca) + " ";
        }

        if (pesquisaPorDescricao){
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            } 
            condicoes += "MTE.MTE_DESCRICAO LIKE '%" + descricao + "%' ";
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
    
    public void alterarModelo(Modelo modelo){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE MTE_MODELO_TENIS MTE SET MTE_DESCRICAO = '?', MTE_MARCA = ?, MTE_PRECO = ? WHERE MTE_COD = ?");
            stmt.setString(1, modelo.getDesc());
            stmt.setString(2, Integer.toString(modelo.getMarca().getCod()));
            stmt.setString(3, Float.toString(modelo.getPreco()));
            stmt.setString(4, Integer.toString(modelo.getCod()));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    public void excluirModelo(int modelo_cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM mte_modelo_tenis WHERE mte_cod = ?");
            stmt.setInt(1, modelo_cod);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
}
