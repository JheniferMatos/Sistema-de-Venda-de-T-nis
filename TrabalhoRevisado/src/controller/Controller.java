package controller;
import java.util.ArrayList;
import java.util.List;
import model.bean.Marca;
import model.bean.ModeloVendido;
import model.bean.Venda;
import model.bean.Devolucao;
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
        List<Venda> vendas = vendaDAO.buscaVendas();
        
        return vendas;
    }
    
    public List<ModeloVendido> getModelosVendidos(int codVenda){
        ArrayList<ModeloVendido> modelosVendidos = modeloVendidoDAO.buscarModelosVendidos(codVenda);
        
        return modelosVendidos;
    }
    
    public List <Marca> getMarcas(){
        List<Marca> marcas = marcaDAO.buscaMarcas();
        
        return marcas;
    }
	
	    public void realizarDevolucao(int codModeloVendido, String motivo){
        //Declarando objetos necessários
        Devolucao devolucao = new Devolucao();
        ModeloVendido mVendido = new ModeloVendido();
        
        //Criando modelo vendido
        mVendido = modeloVendidoDAO.buscaModeloVendido(codModeloVendido);
        mVendido.setDevolvido(true);
        
        //Criando devolucao
        devolucao.setModeloVendido(mVendido);
        devolucao.setMotivo(motivo);
        
        //Persistindo devolucao e atualizando estado do modeloVendido
        devolucaoDAO.inserirDevolucao(devolucao);
        modeloVendidoDAO.alteraEstadoDevolvido(mVendido);
    }
}