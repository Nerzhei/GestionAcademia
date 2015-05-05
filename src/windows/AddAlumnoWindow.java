package windows;

import controllers.AddAlumnoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by maicol on 16/04/15.
 */
public class AddAlumnoWindow {

    public AddAlumnoWindow(MainWindow mainWindow) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/vistaAddAlumno.fxml"));
            Parent root = loader.load();
            AddAlumnoController ctrl = loader.getController();
            ctrl.setDatabaseListener(mainWindow);
            ctrl.setMainWindow(mainWindow);
            ctrl.setStage(stage);
            stage.setScene(new Scene(root));
            stage.setTitle("Añadir alumno");
            stage.getIcons().add(new Image("img/icon.png"));
            stage.showAndWait();
        } catch (IOException e) {
        }
    }
}
