package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Modelo;

public class ModeloDAO {
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
}
