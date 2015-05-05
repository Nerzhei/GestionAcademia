package daointerface;

import javafx.collections.ObservableList;
import models.Alumno;

import java.sql.Connection;

/**
 * Created by maicol on 19/12/14.
 */
public interface DaoInterfaceAlumno {
    public void create(Alumno alumno, Connection conn) throws Exception;

    public Alumno read(int ci, Connection conn) throws Exception;

    public void update(Alumno alumno, Connection conn) throws Exception;

    public void delete(Alumno alumno, Connection conn) throws Exception;

    public ObservableList<Alumno> readAll(Connection conn) throws Exception;

    public void setDatabase(Connection conn) throws Exception;
}
