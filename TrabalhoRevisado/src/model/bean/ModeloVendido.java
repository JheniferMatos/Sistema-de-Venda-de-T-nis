package model.bean;

public class ModeloVendido {
    private int codigo;
    private Venda venda;
    private Modelo modelo;
    
    public int getCod(){
        return codigo;
    }
    
    public Venda getVenda(){
        return venda;
    }
    
    public Modelo getModelo() {
        return modelo;
    }
    
    public void setCod(int codModeloVendido){
        this.codigo = codModeloVendido;
    }
    
    public void setVenda(Venda venda){
        this.venda = venda;
    }
    
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}

