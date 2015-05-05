package windows;

import controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by maicol on 23/04/15.
 */
public class RaizWindow {

    private BorderPane layout;

    RaizWindow(Stage stage, MainWindow mainWindow) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/vistaRaiz.fxml"));
            layout = loader.load();
            MainController ctrl = loader.getController();
            ctrl.setMainWindow(mainWindow);
            ctrl.setStage(stage);
        } catch (IOException e) {
        }
    }

    public BorderPane getLayout() {
        return layout;
    }
}
