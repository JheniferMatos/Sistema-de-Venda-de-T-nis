package model.bean;

public class ModeloVendido {
    private Modelo modelo;
    private int quantidade;
    
    public ModeloVendido(){}
    
    public ModeloVendido(Modelo nModelo, int nQuantidade){
        this.modelo = nModelo;
        this.quantidade = nQuantidade;
    }    

    public Modelo getModelo() {
        return modelo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

