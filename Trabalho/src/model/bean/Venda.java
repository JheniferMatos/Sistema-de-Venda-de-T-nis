package model.bean;
import java.util.Date;

public class Venda {
    private int codigo;
    private Cliente cliente;
    private Funcionario funcionario;
    private Date dataHora;

    public Venda(int nCodigo, Cliente nCliente, Funcionario nFuncionario, Date nDataHora){
        this.codigo = nCodigo;
        this.cliente = nCliente;
        this.funcionario = nFuncionario;
        this.dataHora = nDataHora;     
    }
    
    public int getCod(){
        return this.codigo;
    }
    
    public void setCod(int nCodigo){
        this.codigo = nCodigo;
    }
    
    public Cliente getCliente(){
        return this.cliente;
    }
    
    public void setCliente(Cliente nCliente){
        this.cliente = nCliente;
    }
    
    public Funcionario getFuncionario(){
        return this.funcionario;
    }
    
    public void setFuncionario(Funcionario nFuncionario){
        this.funcionario = nFuncionario;
    }
    
    public Date getData(){
        return this.dataHora;
    }
    
    public void setData(Date nDataHora){
        this.dataHora = nDataHora;
    }
}
