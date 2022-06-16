package controller;
import java.util.ArrayList;
import java.util.List;
import model.bean.ModeloVendido;
import model.bean.Venda;
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
        ArrayList<ModeloVendido> modelosVendidos = mvdao.buscarModelosVendidos(codVenda);
        
        return modelosVendidos;
    }
}