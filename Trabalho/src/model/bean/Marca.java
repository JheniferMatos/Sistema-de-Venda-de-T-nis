package model.bean;

public class Marca {
    private int codigo;
    private String nome;
    
    public Marca(int nCodigo, String nNome){
        this.codigo = nCodigo;
        this.nome = nNome;
    }

    public int getCod() {
        return codigo;
    }

    public void setCod(int nCod) {
        this.codigo = nCod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nNome) {
        this.nome = nNome;
    }
    
    @Override
    public String toString(){
        return getNome();
    }
}
