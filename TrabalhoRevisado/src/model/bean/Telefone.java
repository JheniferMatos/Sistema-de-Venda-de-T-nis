package model.bean;

public class Telefone {
    private int codigo;
    private String numero;
    private String descricao;
    
    public Telefone(int nCodigo, String nNumero, String nDescricao){
        this.codigo = nCodigo;
        this.numero = nNumero;
        this.descricao = nDescricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
