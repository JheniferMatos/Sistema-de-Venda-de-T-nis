package model.bean;

import java.util.ArrayList;

public class Cliente {
    private int codigo;
    private String nome;
    private String cpf;
    private String logradouro;
    private int numero;
    private String cidade;
    private String estado;
    private String bairro;
    private String cep;
    private String email;
    private ArrayList<Telefone> telefones;

    public int getCod(){
        return this.codigo ;
    }
    
    public void setCod(int nCodigo){
        this.codigo = nCodigo;
    }
    
    public String getNome(){
        return this.nome ;
    }
    
    public void setNome(String nNome){
        this.nome = nNome;
    }
    
    public String getCpf(){
        return this.cpf ;
    }
    
    public void setCpf(String nCpf){
        this.cpf = nCpf;
    }
    
    public String getLogradouro(){
        return this.logradouro;
    }
    
    public void setLogradouro(String nLogradouro){
        this.logradouro = nLogradouro;
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int nNumero){
        this.numero = nNumero;
    }
    
    public String getCidade(){
        return this.cidade;
    }
    
    public void setCidade(String nCidade){
        this.cidade = nCidade;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public void setEstado(String nEstado){
        this.estado = nEstado;
    }
    
    public String getBairro(){
        return this.bairro;
    }
    
    public void setBairro(String nBairro){
        this.bairro = nBairro;
    }
    
    public String getCep(){
        return this.cep;
    }
    
    public void setCep(String nCep){
        this.cep = nCep;
    }
    
    public String getEmail(){
        return this.email ;
    }
    
    public void setEmail(String nEmail){
        this.email = nEmail;
    }
    
    public ArrayList<Telefone> getTelefones(){
        return this.telefones;
    }
    
    public void setTelefones(ArrayList<Telefone> nTelefones){
        this.telefones = nTelefones;
    }
    @Override
    public String toString(){
        return getNome();
    }
}
