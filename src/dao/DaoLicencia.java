package dao;

import daointerface.DaoInterfaceLicencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Licencia;
import other.Validator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by maicol on 23/12/14.
 */
public class DaoLicencia implements DaoInterfaceLicencia {

    Validator.GetFecha getFecha = (String fechadb) -> {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fechadb, formatter);
        } catch (Exception e) {
            return null;
        }
    };

    @Override
    public void create(Licencia licencia, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "INSERT INTO licencias(alumno, tipo, fechaexp, fechacad) VALUES ('" + Validator.getString(licencia.getAlumno().getCi()) + "', '" + Validator.getString(licencia.getTipo()) + "', '" + Validator.getString(licencia.getFechaexp()) + "', '" + Validator.getString(licencia.getFechacad()) + "');";
        st.execute(query);
        st.close();
    }

    @Override
    public void update(Licencia licencia, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "UPDATE licencias SET fechaexp='" + Validator.getString(licencia.getFechaexp()) + "', fechacad='" + Validator.getString(licencia.getFechacad()) + "' WHERE alumno='" + Validator.getString(licencia.getAlumno().getCi()) + "' AND tipo = '" + Validator.getString(licencia.getTipo()) + "';";
        st.execute(query);
        st.close();
    }

    @Override
    public void delete(Licencia licencia, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "DELETE FROM licencias WHERE alumno='" + Validator.getString(licencia.getAlumno().getCi()) + "' AND tipo = '" + Validator.getString(licencia.getTipo()) + "';";
        st.execute(query);
        st.close();
    }

    @Override
    public ObservableList<Licencia> readByAlumno(Licencia licencia, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "SELECT * FROM licencias WHERE alumno = '" + Validator.getString(licencia.getAlumno().getCi()) + "'";
        ResultSet rs = st.executeQuery(query);
        ObservableList<Licencia> licencias = FXCollections.observableArrayList();
        while (rs.next()) {
            Licencia licenciaAux = new Licencia();
            licenciaAux.setAlumno(licencia.getAlumno());
            licenciaAux.setTipo(Licencia.Type.valueOf(rs.getString(3)));
            licenciaAux.setFechaexp(getFecha.get(rs.getString(4)));
            licenciaAux.setFechacad(getFecha.get(rs.getString(5)));
            licencias.add(licenciaAux);
        }
        rs.close();
        st.close();
        return licencias;
    }

    @Override
    public void setDatabase(Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "CREATE TABLE licencias(ID INT PRIMARY KEY AUTO_INCREMENT, alumno VARCHAR(100), tipo VARCHAR(100), fechaexp VARCHAR(100), fechacad VARCHAR(100));";
        st.execute(query);
        st.close();
    }
}
