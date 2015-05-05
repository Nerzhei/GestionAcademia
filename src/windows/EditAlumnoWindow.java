package windows;

import controllers.EditAlumnoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by maicol on 16/04/15.
 */
public class EditAlumnoWindow {
    public EditAlumnoWindow(MainWindow mainWindow) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/vistaEditAlumno.fxml"));
            Parent root = loader.load();
            EditAlumnoController ctrl = loader.getController();
            ctrl.setStage(stage);
            ctrl.setDatabaseListener(mainWindow);
            ctrl.setMainWindow(mainWindow);
            ctrl.init();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Alumno");
            stage.getIcons().add(new Image("img/icon.png"));
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Nahuel");
        }
    }
}
