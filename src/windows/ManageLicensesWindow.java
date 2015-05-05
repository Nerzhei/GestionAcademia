package windows;

import controllers.ManageLicensesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by maicol on 16/04/15.
 */
public class ManageLicensesWindow {

    public ManageLicensesWindow(MainWindow mainWindow) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/vistaManageLicenses.fxml"));
            Parent root = loader.load();
            ManageLicensesController ctrl = loader.getController();
            ctrl.setMainWindow(mainWindow);
            ctrl.setDatabaseListener(mainWindow);
            ctrl.setStage(stage);
            mainWindow.getBeanLicencia().getLicencia().setAlumno(mainWindow.getBeanAlumno().getAlumno());
            mainWindow.getBeanLicencia().readByAlumno();
            ctrl.setTabla(mainWindow.getBeanLicencia().getLicencias());
            stage.setScene(new Scene(root));
            stage.setTitle("Gestionar licencias");
            stage.getIcons().add(new Image("img/icon.png"));
            stage.showAndWait();
        } catch (IOException e) {
        }
    }
}
