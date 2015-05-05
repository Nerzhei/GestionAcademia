package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import models.Alumno;
import other.ImageReader;
import other.ImageReaderInt;
import other.PDFMaker;
import other.Validator;
import windows.EditAlumnoWindow;
import windows.MainWindow;
import windows.ManageLicensesWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by maicol on 16/04/15.
 */
public class ShowAlumnoController {

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
    private Label mailLabel;
    @FXML
    private ImageView showAlumnoImagen;

    private MainWindow mainWindow;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    ImageReaderInt imageReaderInt = (Alumno alumno) -> {
        File file = null;
        Image image = null;
        try {
            file = new File("./images" + Validator.getString(alumno.getCi()) + ".jpg");
            image = new Image(Validator.getString(file.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    };

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    public void init() {
        ciLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getCi()));
        nombreLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getNombre()));
        fechapfLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getFechapf()));
        fechatLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getFechat()));
        telLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getTel()));
        dirLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getDir()));
        mailLabel.setText(Validator.getString(mainWindow.getBeanAlumno().getAlumno().getMail()));
        showAlumnoImagen.setImage(new ImageReader().getImgByAlumno(mainWindow.getBeanAlumno().getAlumno()));
    }

    @FXML
    private void salir() {
        stage.close();
    }

    @FXML
    private void editar() {
        new EditAlumnoWindow(mainWindow);
        stage.close();
    }

    @FXML
    private void imprimir() {
    }

    @FXML
    private void exportarPDF() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Guardar en");
            File pdf = directoryChooser.showDialog(new Stage());
            /*if (pdf != null) {
                new PDFMaker(pdf.toString() + "/" + mainWindow.getBeanAlumno().getAlumno().getNombre() + ".pdf", mainWindow.getBeanAlumno().getAlumno());
            }*/
        /*} catch (FileNotFoundException e) {

        } catch (DocumentException e) {

        } catch (IOException e) {

        }*/
        } catch(Exception e) {}
    }

    @FXML
    private void manageLicenses() {
        new ManageLicensesWindow(mainWindow);
        stage.close();
    }

}
