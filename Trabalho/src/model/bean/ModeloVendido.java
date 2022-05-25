package model.bean;

public class ModeloVendido {
    private Venda venda;
    private Modelo modelo;
    private int quantidade;
    
    public ModeloVendido(Venda nVenda, Modelo nModelo, int nQuantidade){
        this.venda = nVenda;
        this.modelo = nModelo;
        this.quantidade = nQuantidade;
    }    

    public Venda getVenda() {
        return venda;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

