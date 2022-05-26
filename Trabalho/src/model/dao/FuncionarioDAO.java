package model.dao;

import connection.ConnectionDataBase;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Funcionario;
import model.bean.Telefone;

public class FuncionarioDAO {
    public Funcionario buscarFuncionarioPorVenda(int codigoVenda){
        Funcionario f;
        int nCodigo = 0;
        String nUsuario = "";
        String nSenha = "";
        String nNome = "";
        String nCpf = "";
        String nEmail = "";
        ArrayList<Telefone> nTelefones = null;
        boolean nAdministrador = false;
        boolean nAtivo = false;
                
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String strSql = "select fun.FUN_COD, fun.FUN_USUARIO, fun.FUN_SENHA, fun.FUN_NOME, ";
        strSql += "fun.FUN_CPF, fun.fun_email, fun.FUN_ADMINISTRADOR, fun.FUN_ATIVO ";   
        strSql += "from fun_funcionario fun inner join ven_venda ven on ven.VEN_FUNCIONARIO = fun.fun_cod ";
        strSql += "where ven.VEN_COD = ?;";
        try {
            stmt = con.prepareStatement(strSql);
            stmt.setString(1, Integer.toString(codigoVenda));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nCodigo = rs.getInt("FUN_COD");
                nUsuario = rs.getString("FUN_USUARIO");
                nSenha = rs.getString("FUN_SENHA");
                nNome = rs.getString("FUN_NOME");
                nCpf = rs.getString("FUN_CPF");
                nEmail = rs.getString("fun_email");    
                nAdministrador = rs.getInt("FUN_ADMINISTRADOR") == 1;
                nAtivo = rs.getInt("FUN_ATIVO") == 1;
                
                TelefoneDAO tDAO = new TelefoneDAO();
                nTelefones = tDAO.buscarTelefonesFuncionarios(nCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        f = new Funcionario(nCodigo, nUsuario, nSenha, nNome, nCpf, nEmail, nAdministrador, nAtivo);
        f.setTelefones(nTelefones);
        return f;
    }
    
    public Funcionario buscaFuncionarioCod(int Cod){
        Funcionario f;
        int nCodigo = 0;
        String nUsuario = "";
        String nSenha = "";
        String nNome = "";
        String nCpf = "";
        String nEmail = "";
        ArrayList<Telefone> nTelefones = null;
        boolean nAdministrador = false;
        boolean nAtivo = false;
                
        Connection con = ConnectionDataBase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String strSql = "select fun.FUN_COD, fun.FUN_USUARIO, fun.FUN_SENHA, fun.FUN_NOME, ";
        strSql += "fun.FUN_CPF, fun.fun_email, fun.FUN_ADMINISTRADOR, fun.FUN_ATIVO ";   
        strSql += "from fun_funcionario fun ";
        strSql += "where fun.FUN_COD = ?;";
        try {
            stmt = con.prepareStatement(strSql);
            stmt.setString(1, Integer.toString(Cod));
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nCodigo = rs.getInt("FUN_COD");
                nUsuario = rs.getString("FUN_USUARIO");
                nSenha = rs.getString("FUN_SENHA");
                nNome = rs.getString("FUN_NOME");
                nCpf = rs.getString("FUN_CPF");
                nEmail = rs.getString("fun_email");    
                nAdministrador = rs.getInt("FUN_ADMINISTRADOR") == 1;
                nAtivo = rs.getInt("FUN_ATIVO") == 1;
                
                TelefoneDAO tDAO = new TelefoneDAO();
                nTelefones = tDAO.buscarTelefonesFuncionarios(nCodigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionDataBase.closeConnection(con, stmt, rs);
        }
        
        f = new Funcionario(nCodigo, nUsuario, nSenha, nNome, nCpf, nEmail, nAdministrador, nAtivo);
        f.setTelefones(nTelefones);
        return f;
    }
    
    public void inserirCliente(Funcionario funcionario){
    }
    
    public void alterarCliente(Funcionario funcionario){

    }
    
    public void excluirCliente(int ven_cod){
    }
}
