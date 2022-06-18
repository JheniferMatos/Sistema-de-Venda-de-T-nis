package controller;
import java.util.ArrayList;
import java.util.List;
import model.bean.Cliente;
import model.bean.Marca;
import model.bean.ModeloVendido;
import model.bean.Venda;
import model.bean.Devolucao;
import model.bean.Modelo;
import model.dao.FuncionarioDAO;
import model.dao.ClienteDAO;
import model.dao.MarcaDAO;
import model.dao.ModeloDAO;
import model.dao.VendaDAO;
import model.dao.DevolucaoDAO;
import model.dao.ModeloVendidoDAO;

public class Controller {

    private FuncionarioDAO funcionarioDAO;
    private ClienteDAO clienteDAO;
    private MarcaDAO marcaDAO;
    private ModeloDAO modeloDAO;
    private ModeloVendidoDAO modeloVendidoDAO;
    private VendaDAO vendaDAO;
    private DevolucaoDAO devolucaoDAO;
    
    public Controller(){
        this.funcionarioDAO = new FuncionarioDAO();
        this.clienteDAO = new ClienteDAO();
        this.marcaDAO = new MarcaDAO();
        this.modeloDAO = new ModeloDAO();
        this.modeloVendidoDAO = new ModeloVendidoDAO();        
        this.vendaDAO = new VendaDAO();  
        this.devolucaoDAO = new DevolucaoDAO();
    }
    
    public List<Venda> getVendas(){       
        return vendaDAO.buscaVendas();
    }
    
    public List<ModeloVendido> getModelosVendidos(int codVenda){
        ArrayList<ModeloVendido> modelosVendidos = modeloVendidoDAO.buscarModelosVendidos(codVenda);
        
        return modelosVendidos;
    }
    
    public List <Marca> getMarcas(){        
        return marcaDAO.buscaMarcas();
    }
    
    public Marca getMarca(int codigo){
        return marcaDAO.buscaMarcaCod(codigo);
    }
    
    public boolean InserirMarca(Marca marca){
        return marcaDAO.InserirMarca(marca);
    }
    
    public boolean AlteraMarca(Marca marca){
        return marcaDAO.AlterarMarca(marca);
    }
    
    public boolean ExcluirMarca(Marca marca){
        return marcaDAO.ExcluirMarca(marca);
    }
    
    public List<Modelo> getModelos(int codModelo, Marca marca, String desc){
        return modeloDAO.buscaModelos(codModelo, marca, desc);
    }
    

    public boolean realizarDevolucao(int codModeloVendido, String motivo){
        //Declarando objetos necessários
        Devolucao devolucao = new Devolucao();
        ModeloVendido mVendido = new ModeloVendido();
        
        //Criando modelo vendido
        mVendido = modeloVendidoDAO.buscaModeloVendido(codModeloVendido);
        mVendido.setDevolvido(true);
        
        //Criando devolucao
        devolucao.setModeloVendido(mVendido);
        devolucao.setMotivo(motivo);
        
        //Atualizando estado do modeloVendido e persistindo devolucao
        modeloVendidoDAO.alteraEstadoDevolvido(mVendido);
        return devolucaoDAO.inserirDevolucao(devolucao);
    }
    
    public Cliente buscarClienteCpf(String cpf){
        return clienteDAO.buscaClienteCpf(cpf);
    }
    
    public Modelo buscarModelo(int codModelo){
        return modeloDAO.buscaModeloCod(codModelo);
    }
}