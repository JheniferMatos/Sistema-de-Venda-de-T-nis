package model.bean;
import java.util.ArrayList;
import java.util.Date;

public class Venda{
    private int codigo;
    private Cliente cliente;
    private Funcionario funcionario;
    private Date dataHora;
    private float total;
    private ArrayList<ModeloVendido> modelosVendidos;

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
    
    public Float getTotal(){
        return this.total;
    }
    
    public void setTotal(float total){
        this.total = total;
    }

    public ArrayList<ModeloVendido> getModelosVendidos() {
        return modelosVendidos;
    }

    public void setModelosVendidos(ArrayList<ModeloVendido> modelosVendidos) {
        this.modelosVendidos = modelosVendidos;
    }
}
