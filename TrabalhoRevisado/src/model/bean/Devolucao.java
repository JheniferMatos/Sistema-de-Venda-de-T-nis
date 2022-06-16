package model.bean;

import model.bean.ModeloVendido;

public class Devolucao {
    private int codigo;
    private ModeloVendido modeloVendido;
    private String motivo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ModeloVendido getModeloVendido() {
        return modeloVendido;
    }

    public void setModeloVendido(ModeloVendido modeloVendido) {
        this.modeloVendido = modeloVendido;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
