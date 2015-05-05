package beans;

import dao.DaoAlumno;
import database.AlumnosDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Alumno;

import java.sql.Connection;

/**
 * Created by maicol on 16/12/14.
 */
public class BeanAlumno {

    private Alumno alumno;
    private final DaoAlumno daoAlumno;
    private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();

    public BeanAlumno() {
        alumno = new Alumno();
        daoAlumno = new DaoAlumno();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public ObservableList<Alumno> getAlumnos() {
        return alumnos;
    }

    public boolean create() {
        boolean result = false;
        try {
            Connection conn = AlumnosDB.conn();
            daoAlumno.create(alumno, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    public boolean update() {
        Boolean result = false;
        try {
            Connection conn = AlumnosDB.conn();
            daoAlumno.update(alumno, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    public boolean delete() {
        Boolean result = false;
        try {
            Connection conn = AlumnosDB.conn();
            daoAlumno.delete(alumno, conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    public boolean readAll() {
        boolean result = false;
        try {
            Connection conn = AlumnosDB.conn();
            alumnos = daoAlumno.readAll(conn);
            conn.close();
            result = true;
        } catch (Exception e) {
        }
        return result;
    }
}
