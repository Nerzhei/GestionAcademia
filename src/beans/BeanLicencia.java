package beans;

import dao.DaoLicencia;
import database.LicenciasDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Licencia;

import java.sql.Connection;

/**
 * Created by maicol on 16/12/14.
 */
public class BeanLicencia {

    private Licencia licencia;
    private final DaoLicencia daoLicencia;
    private ObservableList<Licencia> licencias = FXCollections.observableArrayList();

    public BeanLicencia() {
        licencia = new Licencia();
        daoLicencia = new DaoLicencia();
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public ObservableList<Licencia> getLicencias() {
        return licencias;
    }


    public boolean create() {
        boolean result = false;
        try {
            Connection conn = LicenciasDB.conn();
            daoLicencia.create(licencia, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    public boolean update() {
        Boolean result = false;
        try {
            Connection conn = LicenciasDB.conn();
            daoLicencia.update(licencia, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    public boolean delete() {
        Boolean result = false;
        try {
            Connection conn = LicenciasDB.conn();
            daoLicencia.delete(licencia, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    public boolean readByAlumno() {
        boolean result = false;
        try {
            Connection conn = LicenciasDB.conn();
            licencias = daoLicencia.readByAlumno(licencia, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }
}
