package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Venda;

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
                condicoes += "AND ";
            }
            condicoes += "CLI.CLI_NOME LIKE '%" + nomeCliente + "%' ";
        }

        if (pesquisaPorDataInicio){
            String strDataInicio;
            strDataInicio = Integer.toString(dataInicial.getYear()) + "-";
            strDataInicio = Integer.toString(dataInicial.getMonth()) + "-";
            strDataInicio = Integer.toString(dataInicial.getDay());
            if (condicoes.compareTo(vazio) != 0){
                condicoes += "AND ";
            } 
            condicoes += "VEN.VEN_DATA_HORA >= '" + strDataInicio + "' ";
        }
        
        if (pesquisaPorDataFim){
            String strDataFim;
            strDataFim = Integer.toString(dataFinal.getYear()) + "-";
            strDataFim = Integer.toString(dataFinal.getMonth()) + "-";
            strDataFim = Integer.toString(dataFinal.getDay());
            if (condicoes.compareTo(vazio) != 0){
                condicoes += "AND ";
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
    
    /*public Venda buscaVendaCod(int Cod){
    
    }*/
    
    public void inserirVenda(Venda venda){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO ven_venda(ven_cliente, ven_funcionario, ven_data_hora) VALUES (?, ?, '?')");
            stmt.setString(1, Integer.toString(venda.getCliente().getCodigo()));
            stmt.setString(2, Integer.toString(venda.getFuncionario().getCodigo()));
            stmt.setString(3, venda.getData().toString());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        }
    }
    
    public void alterarVenda(Venda venda){
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE VEN_VENDA VEN SET VEN.VEN_CLIENTE = ?, VEN.VEN_FUNCIONARIO = ?, VEN.VEN_DATA_HORA = '?' WHERE VEN.VEN_CODIGO = ?");
            stmt.setString(1, Integer.toString(venda.getCliente().getCodigo()));
            stmt.setString(2, Integer.toString(venda.getFuncionario().getCodigo()));
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

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt);
        } 
    }
}
