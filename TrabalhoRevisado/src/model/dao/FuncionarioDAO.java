package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Funcionario;

public class FuncionarioDAO {
    public Funcionario buscaFuncionarioCod(int Cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = new Funcionario();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fun_funcionario WHERE fun_cod = ?");
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            
            while(rs.next()){           
                //Objeto funcionario sendo preenchido pelo resultSet obtido
                funcionario.setCod(rs.getInt("fun_cod"));
                funcionario.setUsuario(rs.getString("fun_usuario"));
                funcionario.setSenha(rs.getString("fun_senha"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setEmail(rs.getString("fun_email"));
                if(rs.getBoolean("fun_administrador")) funcionario.setAdministrador(true);
                else funcionario.setAdministrador(false);
            }               
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        //Retorna o objeto
        return funcionario;
    }
}
