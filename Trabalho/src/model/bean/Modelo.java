package model.bean;

public class Modelo {
    private int cod;
    private String desc;
    private int codMarca;
    private float preco;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCodMarca() {
        return codMarca;
    }
    
    public String getCodMarcaS() {
        return Integer.toString(codMarca);
    }

    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
