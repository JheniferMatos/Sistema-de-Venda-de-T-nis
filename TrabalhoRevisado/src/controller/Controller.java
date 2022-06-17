package controller;
import java.util.ArrayList;
import java.util.List;
import model.bean.Devolucao;
import model.bean.ModeloVendido;
import model.bean.Venda;
import model.dao.DevolucaoDAO;
import model.dao.VendaDAO;
import model.dao.ModeloVendidoDAO;

public class Controller {
    public List<Venda> getVendas(){
        VendaDAO vdao = new VendaDAO();
        List<Venda> vendas = vdao.buscaVendas();
        
        return vendas;
    }
    
    public List<ModeloVendido> getModelosVendidos(int codVenda){   
        ModeloVendidoDAO mvdao = new ModeloVendidoDAO();
        List<ModeloVendido> modelosVendidos = mvdao.buscarModelosVendidos(codVenda);
        
        return modelosVendidos;
    }
    
    public void realizarDevolucao(int codModeloVendido, String motivo){
        //Declarando objetos necessários
        Devolucao devolucao = new Devolucao();
        ModeloVendido mVendido = new ModeloVendido();
        DevolucaoDAO devDAO = new DevolucaoDAO();
        ModeloVendidoDAO mvDAO = new ModeloVendidoDAO();
        
        //Criando modelo vendido
        mVendido = mvDAO.buscaModeloVendido(codModeloVendido);
        mVendido.setDevolvido(true);
        
        //Criando devolucao
        devolucao.setModeloVendido(mVendido);
        devolucao.setMotivo(motivo);
        
        //Persistindo devolucao e atualizando estado do modeloVendido
        devDAO.inserirDevolucao(devolucao);
        mvDAO.alteraEstadoDevolvido(mVendido);
    }
}