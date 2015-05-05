package windows;

import beans.BeanAlumno;
import beans.BeanLicencia;
import controllers.VistaPrincipalController;
import dao.DaoAlumno;
import dao.DaoLicencia;
import database.AlumnosDB;
import database.LicenciasDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import listeners.DatabaseListener;
import listeners.ListenableObject;
import listeners.ListenableObjectAlumno;
import listeners.ListenableObjectLicencia;

public class MainWindow extends Application implements DatabaseListener {


    private BeanAlumno beanAlumno;
    private BeanLicencia beanLicencia;
    private VistaPrincipalController vistaPrincipalController;

    public BeanAlumno getBeanAlumno() {
        return beanAlumno;
    }

    public BeanLicencia getBeanLicencia() {
        return beanLicencia;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Alumnos");
        primaryStage.getIcons().add(new Image("img/icon.png"));

        beanAlumno = new BeanAlumno();
        beanLicencia = new BeanLicencia();

        try {
            new DaoAlumno().setDatabase(AlumnosDB.conn());
        } catch (Exception e) { }
        try {
            new DaoLicencia().setDatabase(LicenciasDB.conn());
        } catch (Exception e) { }

        BorderPane layout = new RaizWindow(primaryStage, this).getLayout();
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        VistaPrincipalWindow vistaPrincipalWindow = new VistaPrincipalWindow(this);
        vistaPrincipalController = vistaPrincipalWindow.getVistaPrincipalController();
        layout.setCenter(vistaPrincipalWindow.getVistaPrincipal());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onAlumnosChange(ListenableObjectAlumno obj) {
        beanAlumno.readAll();
        obj.setTabla(beanAlumno.getAlumnos());
    }

    @Override
    public void onLicensesChange(ListenableObjectLicencia obj) {
        beanLicencia.readByAlumno();
        obj.setTabla(beanLicencia.getLicencias());
    }

    @Override
    public void onDatabaseChange(ListenableObject obj) {
        beanAlumno.readAll();
        vistaPrincipalController.setTabla(beanAlumno.getAlumnos());
    }
}