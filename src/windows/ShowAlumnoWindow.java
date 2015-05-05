package windows;

import controllers.ShowAlumnoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by maicol on 16/04/15.
 */
public class ShowAlumnoWindow {
    public ShowAlumnoWindow(MainWindow mainWindow) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/vistaShowAlumno.fxml"));
            Parent root = loader.load();
            ShowAlumnoController ctrl = loader.getController();
            ctrl.setMainWindow(mainWindow);
            ctrl.setStage(stage);
            ctrl.init();
            stage.setScene(new Scene(root));
            stage.setTitle("Perfil de Alumno");
            stage.getIcons().add(new Image("img/icon.png"));
            stage.showAndWait();
        } catch (IOException e) {
        }
    }
}
