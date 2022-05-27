package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Venda;
import view.IAVendaView;

public class VendaDAO {
    
    public List<Venda> buscarVendas(int codigoVenda, String nomeCliente, Date dataInicial, Date dataFinal){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Venda> vendas = new ArrayList<>();
        
        //Montar string SQL
        String strSql, condicoes, vazio;
        strSql = "SELECT VEN.VEN_COD, VEN.VEN_CLIENTE, VEN.VEN_FUNCIONARIO, VEN.VEN_DATA_HORA ";
        strSql += "FROM VEN_VENDA VEN INNER JOIN CLI_CLIENTE CLI ON CLI.CLI_COD = VEN.VEN_CLIENTE ";
        vazio = "";
        condicoes = "";
        
        boolean pesquisaPorCodigo, pesquisaPorCliente, pesquisaPorDataInicio, pesquisaPorDataFim;
        pesquisaPorCodigo = codigoVenda != 0;
        pesquisaPorCliente = nomeCliente.compareTo(vazio)!= 0;
        pesquisaPorDataInicio = dataInicial != null;
        pesquisaPorDataFim = dataFinal != null;
        
        if (pesquisaPorCodigo){
            condicoes += "VEN.VEN_COD = " + Integer.toString(codigoVenda)+ " ";
        } 

        if (pesquisaPorCliente){
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            }
            condicoes += "CLI.CLI_NOME LIKE '%" + nomeCliente + "%' ";
        }

        if (pesquisaPorDataInicio){
            String strDataInicio;
            strDataInicio = Integer.toString(dataInicial.getYear()) + "-";
            strDataInicio = Integer.toString(dataInicial.getMonth()) + "-";
            strDataInicio = Integer.toString(dataInicial.getDay());
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            } 
            condicoes += "VEN.VEN_DATA_HORA >= '" + strDataInicio + "' ";
        }
        
        if (pesquisaPorDataFim){
            String strDataFim;
            strDataFim = Integer.toString(dataFinal.getYear()) + "-";
            strDataFim = Integer.toString(dataFinal.getMonth()) + "-";
            strDataFim = Integer.toString(dataFinal.getDay());
            if (condicoes.compareTo(vazio) != 0){
                condicoes += " AND ";
            } 
            condicoes += "VEN.VEN_DATA_HORA <= '" + strDataFim + "' ";
        }
        
        if (condicoes.compareTo(vazio) != 0){
            strSql += "WHERE " + condicoes;
        } 
        
        try {
            stmt = con.prepareStatement(strSql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Date nDataHora = rs.getDate("ven_data_hora");
                ClienteDAO cDAO = new ClienteDAO();
                FuncionarioDAO fDAO = new FuncionarioDAO();
                ModeloVendidoDAO mDAO = new ModeloVendidoDAO();
                Venda v = new Venda();
                
                v.setCod(rs.getInt("VEN_COD"));
                v.setCliente(cDAO.buscaClienteCod(rs.getInt("VEN_CLIENTE")));
                v.setFuncionario(fDAO.buscaFuncionarioCod(rs.getInt("VEN_FUNCIONARIO")));
                v.setData(nDataHora);               
                vendas.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return vendas;
    }    
    
   
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
                
                v.setCod(rs.getInt("VEN_COD"));
                v.setCliente(cDAO.buscaClienteCod(rs.getInt("VEN_CLIENTE")));
                v.setFuncionario(fDAO.buscaFuncionarioCod(5));
                v.setData(rs.getDate("ven_data_hora"));
                v.setTotal(rs.getFloat("VEN_TOTAL"));
                
                vendas.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        return vendas;
    }
    
    public int inserirVenda(Venda venda){
        Connection con = ConnectionDataBase.getConnection();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        PreparedStatement stmt = null;
        int novoId = 0;
        
        try {
            stmt = con.prepareStatement("INSERT INTO ven_venda(ven_cliente, ven_funcionario, ven_data_hora, ven_total) VALUES (?, ?, ?, ?)");
            stmt.setString(1, Integer.toString(venda.getCliente().getCod()));
            stmt.setString(2, Integer.toString(venda.getFuncionario().getCod()));
            stmt.setString(3, df.format(venda.getData()));
            stmt.setString(4, Float.toString(venda.getTotal()));
            
            stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if (resultSet.next()) {
                novoId = resultSet.getInt("LAST_INSERT_ID()");
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            return novoId;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
        return 0;
    }
    
    public void alterarVenda(Venda venda){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE VEN_VENDA VEN SET VEN.VEN_CLIENTE = ?, VEN.VEN_FUNCIONARIO = ?, VEN.VEN_DATA_HORA = '?' WHERE VEN.VEN_CODIGO = ?");
            stmt.setString(1, Integer.toString(venda.getCliente().getCod()));
            stmt.setString(2, Integer.toString(venda.getFuncionario().getCod()));
            stmt.setString(3, venda.getData().toString());
            stmt.setString(4, Integer.toString(venda.getCod()));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void excluirVenda(int ven_cod){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM VEN_VENDA VEN WHERE VEN.VEN_COD = ?");
            stmt.setString(1, Integer.toString(ven_cod));
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Venda excluída com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        } 
    }
}
