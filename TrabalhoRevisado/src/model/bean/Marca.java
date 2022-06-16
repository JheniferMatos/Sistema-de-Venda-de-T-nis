package model.bean;

public class Marca {
    private int codigo;
    private String nome;
    
    public Marca(){}
    
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
    
    @Override
    public boolean equals(Object o) {
       Marca marca = (Marca) o;
       if(o == null){
           return false;
       }
       return this.getNome().equals(marca.getNome());
    }
}
