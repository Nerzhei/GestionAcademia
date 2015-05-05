package windows;

import controllers.VistaPrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by maicol on 23/04/15.
 */
public class VistaPrincipalWindow {

    private AnchorPane vistaPrincipal;
    private VistaPrincipalController vistaPrincipalController;

    VistaPrincipalWindow(MainWindow mainWindow) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/vistaPrincipal.fxml"));
            vistaPrincipal = loader.load();
            vistaPrincipalController = loader.getController();
            mainWindow.getBeanAlumno().readAll();
            vistaPrincipalController.setTabla(mainWindow.getBeanAlumno().getAlumnos());
            vistaPrincipalController.setDatabaseListener(mainWindow);
            vistaPrincipalController.setMainWindow(mainWindow);
        } catch (IOException e) {
        }
    }

    public AnchorPane getVistaPrincipal() {
        return vistaPrincipal;
    }

    public VistaPrincipalController getVistaPrincipalController() {
        return vistaPrincipalController;
    }
}
