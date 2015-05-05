package controllers;

import beans.BeanAlumno;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import listeners.DatabaseListener;
import listeners.ListenableObjectAlumno;
import models.Alumno;
import windows.EditAlumnoWindow;
import windows.MainWindow;
import windows.ManageLicensesWindow;
import windows.ShowAlumnoWindow;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Maicol on 25/01/2015.
 */
public class VistaPrincipalController implements ListenableObjectAlumno, Initializable {

    private BeanAlumno beanAlumno;

    @FXML
    private TextField filtroField;
    @FXML
    private TableView<Alumno> tablaAlumnos;
    @FXML
    private TableColumn<Alumno, String> ciColumn;
    @FXML
    private TableColumn<Alumno, String> nombreColumn;
    @FXML
    private Label ciLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label fechapfLabel;
    @FXML
    private Label fechatLabel;
    @FXML
    private Label telLabel;
    @FXML
    private Label dirLabel;
    @FXML
    private Label emailLabel;

    public void setDatabaseListener(DatabaseListener databaseListener) {
        this.databaseListener = databaseListener;
    }

    private DatabaseListener databaseListener;
    private MainWindow mainWindow;

    private void showPersonDetails(Alumno alumno) {
        if (alumno != null) {
            ciLabel.setText(Integer.toString(alumno.getCi()));
            nombreLabel.setText(alumno.getNombre());
            if (alumno.getFechapf() != null) {
                fechapfLabel.setText(alumno.getFechapf().toString());
            }
            if (alumno.getFechapf() != null) {
                fechatLabel.setText(alumno.getFechapf().toString());
            }
            if (alumno.getTel() != null) {
                telLabel.setText(alumno.getTel());
            }
            if (alumno.getDir() != null) {
                dirLabel.setText(alumno.getDir());
            }
            if (alumno.getMail() != null) {
                emailLabel.setText(alumno.getMail());
            }
        } else {
            ciLabel.setText("");
            nombreLabel.setText("");
            fechapfLabel.setText("");
            fechatLabel.setText("");
            telLabel.setText("");
            dirLabel.setText("");
            emailLabel.setText("");
        }
    }

    @Override
    public void setTabla(ObservableList alumnos) {


        tablaAlumnos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        FilteredList<Alumno> filteredData = new FilteredList<>(alumnos, p -> true);

        MenuItem edit = new MenuItem("Editar");
        MenuItem show = new MenuItem("Mostrar");
        MenuItem remove = new MenuItem("Eliminar");
        MenuItem manageLicenses = new MenuItem("Gestionar Licencias");
        ContextMenu context = new ContextMenu();
        context.getItems().addAll(show, edit, remove, manageLicenses);

        show.setOnAction((observable) -> {
            if (tablaAlumnos.getSelectionModel().selectedItemProperty().getValue() != null) {
                mainWindow.getBeanAlumno().setAlumno(tablaAlumnos.getSelectionModel().selectedItemProperty().getValue());
                new ShowAlumnoWindow(mainWindow);
            }
        });
        edit.setOnAction((observable) -> {
            if (tablaAlumnos.getSelectionModel().selectedItemProperty().getValue() != null) {
                mainWindow.getBeanAlumno().setAlumno(tablaAlumnos.getSelectionModel().selectedItemProperty().getValue());
                new EditAlumnoWindow(mainWindow);
            }
        });
        remove.setOnAction((observable) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Precaución");
            alert.setHeaderText("Esta operación es irreversible");
            alert.setContentText("Si continúa se borrará completamente el registro del alumno seleccionado de la base de datos, lo que supone que no habrá forma de recuperarlo.");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                mainWindow.getBeanAlumno().setAlumno(tablaAlumnos.getSelectionModel().selectedItemProperty().getValue());
                if (mainWindow.getBeanAlumno().delete()) {
                    databaseListener.onAlumnosChange(this);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Información");
                    alert2.setHeaderText("Transacción exitosa");
                    alert2.setContentText("El alumno ha sido eliminado con éxito");
                    alert2.showAndWait();
                }
            }
        });

        manageLicenses.setOnAction((observable) -> {
            if (tablaAlumnos.getSelectionModel().selectedItemProperty().getValue() != null) {
                mainWindow.getBeanAlumno().setAlumno(tablaAlumnos.getSelectionModel().selectedItemProperty().getValue());
                new ManageLicensesWindow(mainWindow);
            }
        });

        tablaAlumnos.setContextMenu(context);

        filtroField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(alumno -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (alumno.ciProperty().getValue().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (alumno.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Alumno> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaAlumnos.comparatorProperty());
        tablaAlumnos.setItems(sortedData);
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @FXML
    private void showAlumno() throws IOException {
        if (tablaAlumnos.getSelectionModel().selectedItemProperty().getValue() != null) {
            mainWindow.getBeanAlumno().setAlumno(tablaAlumnos.getSelectionModel().selectedItemProperty().getValue());
            new ShowAlumnoWindow(mainWindow);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No disponible");
            alert.setContentText("Debe seleccionar un alumno para realizar esta acción");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ciColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCi())));
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        showPersonDetails(null);
    }
}
