package model.bean;

public class Modelo {
    private int codigo;
    private String descricao;
    private Marca marca;
    private float preco;

    public int getCod() {
        return codigo;
    }

    public void setCod(int cod) {
        this.codigo = cod;
    }

    public String getDesc() {
        return descricao;
    }

    public void setDesc(String desc) {
        this.descricao = desc;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    @Override
    public String toString(){
        return getDesc();
    }
}
