package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Marca;

public class MarcaDAO {
    public Marca buscaMarcaCod(int Cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Marca marca = new Marca();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mar_marca WHERE mar_cod = ?");
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            
            while(rs.next()){           
                //Objeto cliente sendo preenchido pelo resultSet obtido
                marca.setCod(rs.getInt("mar_cod"));
                marca.setNome(rs.getString("mar_nome"));
            }               
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return marca;
    }
}
