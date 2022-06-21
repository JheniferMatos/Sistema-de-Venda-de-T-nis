import controller.Controladora;
import view.TabelaVendaView;

public class TrabalhoRevisado {
    public static void main(String[] args) {
        Controladora controller = new Controladora();
        TabelaVendaView frame = new TabelaVendaView(controller);
        frame.setVisible(true);
    }   
}
