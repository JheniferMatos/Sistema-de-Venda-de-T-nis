package controller;

import javax.swing.table.DefaultTableModel;
import model.bean.ModeloVendido;
import model.bean.Venda;
import model.dao.DevolucaoDAO;
import model.dao.ModeloVendidoDAO;
import model.dao.VendaDAO;
import view.TabelaVendaView;

public class ControllerTabelaVenda {
    /*
    public void preencheTabelaItens(Object view){
        TabelaVendaView frame = (TabelaVendaView) view;
        DevolucaoDAO devDAO = new DevolucaoDAO();        
        DefaultTableModel modeloTb = (DefaultTableModel) frame.getTabelaItens().getModel();
        modeloTb.setNumRows(0);
        ModeloVendidoDAO mvdao = new ModeloVendidoDAO();
        
        
        for(ModeloVendido modeloV: mvdao.buscarModelosVendidos(Integer.parseInt(frame.getTabelaVenda().getValueAt(frame.getTabelaVenda().getSelectedRow(), 0).toString()))){
            Object row[] = new Object[]{modeloV.getCod(), modeloV.getModelo().getMarca().getNome(), modeloV.getModelo().getDesc(), modeloV.getModelo().getPreco()};
            
            if(devDAO.verificaModeloVendido(modeloV)){
                row[0] = "(DEVOLVIDO)";
            }
            
            modeloTb.addRow(row);
        }
    }
    */
    
}
