package model.bean;
import java.util.ArrayList;

public class Funcionario {
    private int codigo;
    private String usuario;
    private String senha;
    private String nome;
    private String cpf;
    private String email;
    private ArrayList<Telefone> telefones;
    private boolean administrador;
    private boolean ativo;
    
    public Funcionario(int nCodigo, String nUsuario, String nSenha, String nNome, String nCpf, String nEmail, boolean nAdministrador, boolean nAtivo){
        this.codigo = nCodigo;
        this.usuario = nUsuario;
        this.senha = nSenha;
        this.nome = nNome;
        this.cpf = nCpf;
        this.email = nEmail;
        this.administrador = nAdministrador;
        this.ativo = nAtivo;
    }

    public int getCodigo(){
        return this.codigo ;
    }
    
    public void setCodigo(int nCodigo){
        this.codigo = nCodigo;
    }
    
    public String getUsuario(){
        return this.usuario ;
    }
    
    public void setUsuario(String nUsuario){
        this.usuario = nUsuario;
    }
    
    public String getSenha(){
        return this.senha ;
    }
    
    public void setSenha(String nSenha){
        this.senha = nSenha;
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
    
    public boolean getAdministrador(){
        return this.administrador ;
    }
    
    public void setAdministrador(boolean nAdministrador){
        this.administrador = nAdministrador;
    }
    
    public boolean getAtivo(){
        return this.ativo ;
    }
    
    public void setAtivo(boolean nAtivo){
        this.ativo = nAtivo;
    }
}
