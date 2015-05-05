package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listeners.DatabaseListener;
import listeners.ListenableObject;
import other.Validator;
import windows.MainWindow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by maicol on 16/04/15.
 */
public class EditAlumnoController implements ListenableObject {

    @FXML
    private TextField editAlumnoCi;
    @FXML
    private TextField editAlumnoNombre;
    @FXML
    private DatePicker editAlumnoFechaPf;
    @FXML
    private DatePicker editAlumnoFechaT;
    @FXML
    private TextField editAlumnoTel;
    @FXML
    private TextField editAlumnoDir;
    @FXML
    private TextField editAlumnoEmail;

    private MainWindow mainWindow;
    private DatabaseListener databaseListener;
    private Stage stage;

    public void setDatabaseListener(DatabaseListener databaseListener) {
        this.databaseListener = databaseListener;
    }

    @Override
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void init() {
        editAlumnoCi.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getCi()));
        editAlumnoNombre.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getNombre()));
        Validator.GetFecha getFecha = (String fechadb) -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(fechadb, formatter);
            } catch (Exception e) {
                return null;
            }
        };
        editAlumnoFechaPf.setValue(getFecha.get(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getFechapf())));
        editAlumnoFechaT.setValue(getFecha.get(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getFechat())));
        editAlumnoTel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getTel()));
        editAlumnoDir.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getDir()));
        editAlumnoEmail.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getMail()));
    }

    @FXML
    private void updateAlumno() {
        boolean name = false;
        mainWindow.getBeanAlumno().getAlumno().setCi(Integer.parseInt(editAlumnoCi.getText()));
        if (editAlumnoNombre.getText() != "") {
            mainWindow.getBeanAlumno().getAlumno().setNombre(editAlumnoNombre.getText());
            name = true;
        } else {
        }
        if (editAlumnoFechaPf.getValue() != null) {
            mainWindow.getBeanAlumno().getAlumno().setFechapf(editAlumnoFechaPf.getValue());
        }
        if (editAlumnoFechaT.getValue() != null) {
            mainWindow.getBeanAlumno().getAlumno().setFechat(editAlumnoFechaT.getValue());
        }
        if (editAlumnoTel.getText() != "") {
            if (Validator.IsValidTel(editAlumnoTel.getText())) {
                mainWindow.getBeanAlumno().getAlumno().setTel(editAlumnoTel.getText());
            }
        }
        if (editAlumnoDir.getText() != "") {
            mainWindow.getBeanAlumno().getAlumno().setDir(editAlumnoDir.getText());
        }
        if (editAlumnoEmail.getText() != "") {
            mainWindow.getBeanAlumno().getAlumno().setMail(editAlumnoEmail.getText());
        }
        if (name) {
            if (mainWindow.getBeanAlumno().update()) {
                if (databaseListener != null) {
                    databaseListener.onDatabaseChange(this);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Alumno actualizado con éxito");
                alert.setContentText("Presione continuar para salir");
                ButtonType continuar = new ButtonType("Continuar");
                alert.getButtonTypes().setAll(continuar);
                alert.showAndWait();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Falló la operación");
                alert.setContentText("Es posible que la base de datos no esté disponible. Intente reiniciando la aplicación.");
                alert.setResizable(true);
                ButtonType continuar = new ButtonType("Continuar");
                alert.getButtonTypes().setAll(continuar);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void salir() {
        stage.close();
    }
}
