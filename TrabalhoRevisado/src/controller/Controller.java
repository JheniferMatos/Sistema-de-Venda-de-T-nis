package controller;
import java.util.ArrayList;
import java.util.List;
import model.bean.ModeloVendido;
import model.dao.VendaDAO;

public class Controller {
    public ArrayList<ModeloVendido> getVendas(){
        VendaDAO vdao;
        List<Venda> vendas = new ArrayList<>();
        ArrayList<ModeloVendido> listaVendas = vdao.buscaVendas();
    }
}
