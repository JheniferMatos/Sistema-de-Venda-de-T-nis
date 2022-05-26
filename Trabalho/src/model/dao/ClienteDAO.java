package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Telefone;

public class ClienteDAO {
    public Cliente buscarClientePorVenda(int codigoVenda){
        Cliente c;
        int nCodigo = 0;
        String nNome = "";
        String nCpf = "";
        String nLogradouro = "";
        int nNumero = 0;
        String nCidade = "";
        String nEstado = "";
        String nBairro = "";
        String nCep = "";
        String nEmail = "";
        ArrayList<Telefone> nTelefones = null;
                
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String strSql = "select cli.CLI_COD, cli.cli_nome, cli.CLI_CPF, cli.CLI_LOGRADOURO, ";
        strSql += "cli.CLI_NUMERO, cli.CLI_CIDADE, cli.CLI_ESTADO, ";   
        strSql += "cli.CLI_BAIRRO, cli.CLI_CEP, cli.CLI_EMAIL ";
        strSql += "from cli_cliente cli inner join ven_venda ven on ven.VEN_CLIENTE = cli.CLI_COD " ;
        strSql += "where ven.VEN_COD = ?;";
        try {
            stmt = con.prepareStatement(strSql);
            stmt.setString(1, Integer.toString(codigoVenda));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nCodigo = rs.getInt("CLI_COD");
                nNome = rs.getString("cli_nome");
                nCpf = rs.getString("CLI_CPF");
                nLogradouro = rs.getString("CLI_LOGRADOURO");
                nNumero = rs.getInt("CLI_NUMERO");
                nCidade = rs.getString("CLI_CIDADE");
                nEstado = rs.getString("CLI_ESTADO");
                nBairro = rs.getString("CLI_BAIRRO");
                nCep = rs.getString("CLI_CEP");
                nEmail = rs.getString("CLI_EMAIL");  
                
                TelefoneDAO tDAO = new TelefoneDAO();
                nTelefones = tDAO.buscarTelefonesClientes(nCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }

        c = new Cliente(nCodigo, nNome, nCpf);
        c.setLogradouro(nLogradouro);
        c.setNumero(nNumero);
        c.setCidade(nCidade);
        c.setEstado(nEstado);
        c.setBairro(nBairro);
        c.setCep(nCep);
        c.setEmail(nEmail);
        c.setTelefones(nTelefones);
        return c;
    }
    
    public Cliente buscaClienteCod(int Cod){
        Cliente c;
        int nCodigo = 0;
        String nNome = "";
        String nCpf = "";
        String nLogradouro = "";
        int nNumero = 0;
        String nCidade = "";
        String nEstado = "";
        String nBairro = "";
        String nCep = "";
        String nEmail = "";
        ArrayList<Telefone> nTelefones = null;
                
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String strSql = "select cli.CLI_COD, cli.cli_nome, cli.CLI_CPF, cli.CLI_LOGRADOURO, ";
        strSql += "cli.CLI_NUMERO, cli.CLI_CIDADE, cli.CLI_ESTADO, ";   
        strSql += "cli.CLI_BAIRRO, cli.CLI_CEP, cli.CLI_EMAIL ";
        strSql += "from cli_cliente cli " ;
        strSql += "where cli.CLI_COD = ?;";
        try {
            stmt = con.prepareStatement(strSql);
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nCodigo = rs.getInt("CLI_COD");
                nNome = rs.getString("cli_nome");
                nCpf = rs.getString("CLI_CPF");
                nLogradouro = rs.getString("CLI_LOGRADOURO");
                nNumero = rs.getInt("CLI_NUMERO");
                nCidade = rs.getString("CLI_CIDADE");
                nEstado = rs.getString("CLI_ESTADO");
                nBairro = rs.getString("CLI_BAIRRO");
                nCep = rs.getString("CLI_CEP");
                nEmail = rs.getString("CLI_EMAIL");  
                
                TelefoneDAO tDAO = new TelefoneDAO();
                nTelefones = tDAO.buscarTelefonesClientes(nCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }

        c = new Cliente(nCodigo, nNome, nCpf);
        c.setLogradouro(nLogradouro);
        c.setNumero(nNumero);
        c.setCidade(nCidade);
        c.setEstado(nEstado);
        c.setBairro(nBairro);
        c.setCep(nCep);
        c.setEmail(nEmail);
        c.setTelefones(nTelefones);
        return c;
    }
    
    public void inserirCliente(Cliente cliente){
    }
    
    public void alterarCliente(Cliente cliente){

    }
    
    public void excluirCliente(int ven_cod){
    }
}
