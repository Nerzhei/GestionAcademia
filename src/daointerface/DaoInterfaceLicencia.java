package daointerface;

import javafx.collections.ObservableList;
import models.Licencia;

import java.sql.Connection;

/**
 * Created by maicol on 19/12/14.
 */
public interface DaoInterfaceLicencia {
    public void create(Licencia licencia, Connection conn) throws Exception;

    public void update(Licencia licencia, Connection conn) throws Exception;

    public void delete(Licencia licencia, Connection conn) throws Exception;

    public ObservableList<Licencia> readByAlumno(Licencia licencia, Connection conn) throws Exception;

    public void setDatabase(Connection conn) throws Exception;
}
