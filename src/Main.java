import controller.PartyAController;
import javax.swing.*;
import model.PartyAModel;
import view.PartyAView;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize MVC
            PartyAModel model = new PartyAModel();
            PartyAView view = new PartyAView();
            new PartyAController(model, view);
            
            // Display view
            view.setVisible(true);
        });
    }
}
