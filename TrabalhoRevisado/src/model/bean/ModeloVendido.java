package model.bean;

public class ModeloVendido {
    private int codigo;
    private Venda venda;
    private Modelo modelo;
    private boolean devolvido;
    
    public int getCod(){
        return codigo;
    }
    
    public Venda getVenda(){
        return venda;
    }
    
    public Modelo getModelo() {
        return modelo;
    }
    
    public boolean getDevolvido(){
        return devolvido;
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
    
    public void setDevolvido(boolean estado){
        this.devolvido = estado;
    }
}

