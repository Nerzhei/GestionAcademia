package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import listeners.DatabaseListener;
import listeners.ListenableObjectLicencia;
import models.Licencia;
import other.Validator;
import windows.MainWindow;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by maicol on 21/04/15.
 */
public class ManageLicensesController implements ListenableObjectLicencia, Initializable {

    private Stage stage;
    private DatabaseListener databaseListener;
    private MainWindow mainWindow;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDatabaseListener(DatabaseListener databaseListener) {
        this.databaseListener = databaseListener;
    }

    @Override
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @FXML
    private TableView<Licencia> tablaLicencias;
    @FXML
    private TableColumn<Licencia, String> tipoColumn;
    @FXML
    private TableColumn<Licencia, String> fechaExpColumn;
    @FXML
    private TableColumn<Licencia, String> fechaVenColumn;


    @FXML
    private Label labelText;
    @FXML
    private Button btn;
    @FXML
    private ChoiceBox tipoChooser;
    @FXML
    private DatePicker pickerFechaexp;
    @FXML
    private DatePicker pickerFechacad;

    public void setTabla(ObservableList licencias) {
        tablaLicencias.setItems(licencias);

    }

    @FXML
    public void addLicense() {
        boolean result = true;
        if (tipoChooser.getValue() != null) {
            mainWindow.getBeanLicencia().getLicencia().setTipo(Licencia.Type.valueOf(Validator.getString(tipoChooser.getValue())));
        } else result = false;
        if (pickerFechaexp.getValue() != null) {
            mainWindow.getBeanLicencia().getLicencia().setFechaexp(pickerFechaexp.getValue());
        } else result = false;
        if (pickerFechacad.getValue() != null) {
            mainWindow.getBeanLicencia().getLicencia().setFechacad(pickerFechacad.getValue());
        } else result = false;
        mainWindow.getBeanLicencia().readByAlumno();
        for (Licencia licencia : mainWindow.getBeanLicencia().getLicencias()) {
            if (licencia.getTipo() == Licencia.Type.valueOf(Validator.getString(tipoChooser.getValue()))) {
                result = false;
            }
        }
        if (result) {
            mainWindow.getBeanLicencia().create();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Transacción exitosa");
            alert.setContentText("Licencia agregada con éxito.");
            alert.showAndWait();
            databaseListener.onLicensesChange(this);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al añadir licencia");
            alert.setContentText("Debe completar todos los campos e ingresar una licencia que no exista.");
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    public void salir() {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Validator.getString(cellData.getValue().getTipo())));
        fechaExpColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Validator.getString(cellData.getValue().getFechaexp())));
        fechaVenColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Validator.getString(cellData.getValue().getFechacad())));
        MenuItem edit = new MenuItem("Editar");
        MenuItem remove = new MenuItem("Eliminar");
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(edit, remove);
        tablaLicencias.setContextMenu(contextMenu);

        edit.setOnAction((observable) -> {
            labelText.setText("Editar licencia");
            btn.setText("Editar");
            tipoChooser.setValue(Validator.getString(tablaLicencias.getSelectionModel().selectedItemProperty().getValue().getTipo()));
            tipoChooser.setDisable(true);
            pickerFechaexp.setValue(tablaLicencias.getSelectionModel().selectedItemProperty().getValue().getFechaexp());
            pickerFechacad.setValue(tablaLicencias.getSelectionModel().selectedItemProperty().getValue().getFechacad());
            btn.setOnAction((observableBtn) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación");
                alert.setHeaderText("Esta operación es irreversible");
                alert.setContentText("¿Seguro que desea actualizar esta licencia?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get() == ButtonType.OK) {
                    mainWindow.getBeanLicencia().setLicencia(tablaLicencias.getSelectionModel().selectedItemProperty().getValue());
                    mainWindow.getBeanLicencia().getLicencia().setFechaexp(pickerFechaexp.getValue());
                    mainWindow.getBeanLicencia().getLicencia().setFechacad(pickerFechacad.getValue());
                    System.out.println(mainWindow.getBeanLicencia().getLicencia().getAlumno().getCi());
                    if (mainWindow.getBeanLicencia().update()) {
                        databaseListener.onLicensesChange(this);
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Información");
                        alert2.setHeaderText("Transacción exitosa");
                        alert2.setContentText("La licencia ha sido actualizada con éxito");
                        alert2.showAndWait();
                    }
                }
                labelText.setText("Añadir licencia");
                btn.setText("Añadir");
                btn.setOnAction((observableBtn2) -> addLicense());
            });
        });

        remove.setOnAction((observable) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Precaución");
            alert.setHeaderText("Esta operación es irreversible");
            alert.setContentText("Si continúa se borrará completamente el registro de la licencia seleccionada de la base de datos, lo que supone que no habrá forma de recuperarla.");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                mainWindow.getBeanLicencia().setLicencia(tablaLicencias.getSelectionModel().selectedItemProperty().getValue());
                if (mainWindow.getBeanLicencia().delete()) {
                    databaseListener.onLicensesChange(this);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Información");
                    alert2.setHeaderText("Transacción exitosa");
                    alert2.setContentText("La licencia ha sido eliminada con éxito");
                    alert2.showAndWait();
                }
            }
        });

    }
}
