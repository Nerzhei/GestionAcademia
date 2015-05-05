package controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import windows.AddAlumnoWindow;
import windows.MainWindow;

import java.io.IOException;

public class MainController {

    private MainWindow mainWindow;
    private Stage stage;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void addAlumnoWindow() throws IOException {
        new AddAlumnoWindow(mainWindow);
    }

    @FXML
    private void salir() {
        stage.close();
    }
}
