package controller;

import javax.swing.table.DefaultTableModel;
import model.bean.Devolucao;
import model.bean.ModeloVendido;
import model.dao.DevolucaoDAO;
import model.dao.ModeloVendidoDAO;
import view.DevVendaView;

public class ControllerDevVenda {
    public void preencheInfoVenda(Object view){
        DevVendaView frame = (DevVendaView) view;
        DevolucaoDAO devDAO = new DevolucaoDAO();
        
        //Preenchendo informações da venda
        frame.getLbCod().setText("Cod.0000000-"+Integer.toString(frame.getCodVenda()));
        frame.getLbCliente().setText(frame.getCliente());
        frame.getLbData().setText(frame.getData());
        
        //Preenchendo tabela
        DefaultTableModel modeloTb = (DefaultTableModel) frame.getTabelaItens().getModel();
        modeloTb.setNumRows(0);
        ModeloVendidoDAO mvdao = new ModeloVendidoDAO();
        
        for(ModeloVendido modeloV: mvdao.buscarModelosVendidos(frame.getCodVenda())){
            Object row[] = new Object[]{modeloV.getCod(), modeloV.getModelo().getMarca().getNome(), modeloV.getModelo().getDesc(), modeloV.getModelo().getPreco()};
            
            if(devDAO.verificaModeloVendido(modeloV)){
                row[0] = "(DEVOLVIDO)";
            }
            
            modeloTb.addRow(row);
        }        
    }
    
    public void realizarDevolucao(Object view){
        DevVendaView frame = (DevVendaView) view;
        
        //Declarando objetos necessários
        Devolucao devolucao = new Devolucao();
        DevolucaoDAO devDAO = new DevolucaoDAO();
        ModeloVendidoDAO mvDAO = new ModeloVendidoDAO();
        
        //Inserindo no objeto de devolucao
        String motivo = "";
        if(frame.getCbMotivo1().isSelected()){
            motivo+= "Motivo1";
        }
        if(frame.getCbMotivo2().isSelected()){
            motivo+= " ";
            motivo+= "Motivo2";
        }
        if(frame.getCbMotivo3().isSelected()){
            motivo+= " ";
            motivo+= "Motivo3";
        }
        if(frame.getCbMotivo4().isSelected()){
            motivo+= " ";
            motivo+= "Motivo4";
        }
        
        devolucao.setModeloVendido(mvDAO.buscaModeloVendido(Integer.parseInt(frame.getTabelaItens().getValueAt(frame.getTabelaItens().getSelectedRow(), 0).toString())));
        devolucao.setMotivo(motivo);
        devDAO.inserirDevolucao(devolucao);
    }
}
