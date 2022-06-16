package model.dao;

import connection.ConnectionDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Venda;

public class VendaDAO {
    public List<Venda> buscaVendas(){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Venda> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ven_venda");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Funcionario e cliente
                FuncionarioDAO fDAO = new FuncionarioDAO();
                ClienteDAO cDAO = new ClienteDAO();
                
                Venda v = new Venda();
                
                v.setCod(rs.getInt("ven_cod"));
                v.setCliente(cDAO.buscaClienteCod(rs.getInt("ven_cliente")));
                v.setFuncionario(fDAO.buscaFuncionarioCod(rs.getInt("ven_funcionario")));
                v.setData(rs.getDate("ven_data_hora"));
                v.setTotal(rs.getFloat("ven_total"));
                
                vendas.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return vendas;
    }
    public Venda buscaVenda(int codVenda){
        Connection con = ConnectionDataBase.getConnection();
        Venda venda = new Venda();
        PreparedStatement stmt = null;
        ResultSet rs = null;
               
        try {
            stmt = con.prepareStatement("SELECT * FROM ven_venda WHERE ven_cod = ?");
            stmt.setString(1, Integer.toString(codVenda));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Funcionario e cliente
                FuncionarioDAO fDAO = new FuncionarioDAO();
                ClienteDAO cDAO = new ClienteDAO();
                
                venda.setCod(rs.getInt("ven_cod"));
                venda.setCliente(cDAO.buscaClienteCod(rs.getInt("ven_cliente")));
                venda.setFuncionario(fDAO.buscaFuncionarioCod(rs.getInt("ven_funcionario")));
                venda.setData(rs.getDate("ven_data_hora"));
                venda.setTotal(rs.getFloat("ven_total"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return venda;
    }
}
