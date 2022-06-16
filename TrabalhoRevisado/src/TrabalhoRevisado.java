import controller.Controller;
import view.TabelaVendaView;

public class TrabalhoRevisado {
    public static void main(String[] args) {
        Controller controller = new Controller();
        TabelaVendaView frame = new TabelaVendaView(controller);
        frame.setVisible(true);
    }   
}
