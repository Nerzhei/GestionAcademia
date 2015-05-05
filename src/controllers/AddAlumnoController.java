package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import listeners.DatabaseListener;
import listeners.ListenableObject;
import other.ImageCopy;
import other.Validator;
import windows.MainWindow;

import java.io.File;
import java.util.Optional;

/**
 * Created by Maicol on 06/02/2015.
 */
public class AddAlumnoController implements ListenableObject {

    @FXML
    private TextField addAlumnoCi;
    @FXML
    private TextField addAlumnoNombre;
    @FXML
    private DatePicker addAlumnoFechaPf;
    @FXML
    private DatePicker addAlumnoFechaT;
    @FXML
    private TextField addAlumnoTel;
    @FXML
    private TextField addAlumnoDir;
    @FXML
    private TextField addAlumnoEmail;
    @FXML
    private ImageView addAlumnoImagen;

    private File imagen;
    private Stage stage;
    private MainWindow mainWindow;
    private DatabaseListener databaseListener;

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

    @FXML
    private void addAlumno() {
        boolean ci = false, name = false;
        if (Validator.IsValidCi(addAlumnoCi.getText())) {
            mainWindow.getBeanAlumno().getAlumno().setCi(Integer.parseInt(addAlumnoCi.getText()));
            ci = true;
        }
        if (addAlumnoNombre.getText().length() > 0) {
            mainWindow.getBeanAlumno().getAlumno().setNombre(addAlumnoNombre.getText());
            name = true;
        }
        if (addAlumnoFechaPf.getValue() != null) {
            mainWindow.getBeanAlumno().getAlumno().setFechapf(addAlumnoFechaPf.getValue());
        }
        if (addAlumnoFechaT.getValue() != null) {
            mainWindow.getBeanAlumno().getAlumno().setFechat(addAlumnoFechaT.getValue());
        }
        if (addAlumnoTel.getText() != "") {
            if (Validator.IsValidTel(addAlumnoTel.getText())) {
                mainWindow.getBeanAlumno().getAlumno().setTel(addAlumnoTel.getText());
            }
        }
        if (addAlumnoDir.getText() != "") {
            mainWindow.getBeanAlumno().getAlumno().setDir(addAlumnoDir.getText());
        }
        if (addAlumnoEmail.getText() != "") {
            mainWindow.getBeanAlumno().getAlumno().setMail(addAlumnoEmail.getText());
        }
        if (ci && name) {
            if (mainWindow.getBeanAlumno().create()) {
                if (databaseListener != null) {
                    databaseListener.onDatabaseChange(this);
                }
                if (imagen != null) {
                    new ImageCopy(imagen, mainWindow.getBeanAlumno().getAlumno());
                } else {}
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Alumno ingresado en la base de datos con éxito");
                alert.setContentText("¿Desea salir o continuar agregando alumnos?");
                ButtonType continuar = new ButtonType("Continuar");
                ButtonType salir = new ButtonType("Salir");
                alert.getButtonTypes().setAll(continuar, salir);
                Optional<ButtonType> response = alert.showAndWait();
                if (response.get() == salir) {
                    stage.close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Falló la operación");
                alert.setContentText("Es posible que el alumno con esa cédula de identidad ya se encuentre registrado o que la base de datos no esté disponible, intente utilizando otra cédula de identidad. En caso de no funcionar, reintente reiniciando la aplicación.");
                alert.setResizable(true);
                ButtonType continuar = new ButtonType("Continuar");
                alert.getButtonTypes().setAll(continuar);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Verifique el campo de cédula y nombre (!)");
            alert.setContentText("Debe ingresar la cédula como una cadena completa, sin puntos ni guiones. El nombre puede ser cualquier cosa.");
            ButtonType continuar = new ButtonType("Continuar");
            ButtonType salir = new ButtonType("Salir");
            alert.getButtonTypes().setAll(continuar);
            alert.showAndWait();
        }
    }

    @FXML
    private void setImagen() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleccionar imagen");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos de imagen", "*.jpg"));
        imagen = chooser.showOpenDialog(null);
        if (imagen != null) {
            Image img = new Image(Validator.getString(imagen.toURI()));
            addAlumnoImagen.setImage(img);
        }
    }

    @FXML
    private void salir() {
        stage.close();
    }
}
