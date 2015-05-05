package dao;

import daointerface.DaoInterfaceAlumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Alumno;
import other.Validator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by maicol on 23/12/14.
 */
public class DaoAlumno implements DaoInterfaceAlumno {

    Validator.GetFecha getFecha = (String fechadb) -> {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fechadb, formatter);
        } catch (Exception e) {
            return null;
        }
    };

    @Override
    public void create(Alumno alumno, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "INSERT INTO alumnos(ci, nombre, fechapf, fechat, tel, dir, mail) VALUES ('" + Validator.getString(alumno.getCi()) + "', '" + Validator.getString(alumno.getNombre()) + "', '" + Validator.getString(alumno.getFechapf()) + "', '" + Validator.getString(alumno.getFechat()) + "', '" + Validator.getString(alumno.getTel()) + "', '" + Validator.getString(alumno.getDir()) + "', '" + Validator.getString(alumno.getMail()) + "')";
        st.execute(query);
        st.close();
    }

    @Override
    public Alumno read(int ci, Connection conn) throws Exception {
        Alumno alumno = null;
        Statement st = conn.createStatement();
        String query = "SELECT * FROM alumnos WHERE ci = 'ci'";
        ResultSet rs = st.executeQuery(query);
        rs.next();
        alumno.setNombre(rs.getString(1));
        alumno.setFechapf(getFecha.get(rs.getString(2)));
        alumno.setFechat(getFecha.get(rs.getString(3)));
        alumno.setTel((rs.getString(4)));
        alumno.setDir(rs.getString(5));
        alumno.setMail(rs.getString(6));
        st.close();
        return alumno;
    }

    @Override
    public void update(Alumno alumno, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "UPDATE alumnos SET nombre='" + Validator.getString(alumno.getNombre()) + "', fechapf='" + Validator.getString(alumno.getFechapf()) + "', fechat='" + Validator.getString(alumno.getFechat()) + "', tel='" + Validator.getString(alumno.getTel()) + "', dir='" + Validator.getString(alumno.getDir()) + "', mail='" + Validator.getString(alumno.getMail()) + "' WHERE ci='" + Validator.getString(alumno.getCi()) + "';";
        st.execute(query);
        st.close();
    }

    @Override
    public void delete(Alumno alumno, Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "DELETE FROM alumnos WHERE ci='" + Validator.getString(alumno.getCi()) + "';";
        st.execute(query);
        st.close();
    }

    @Override
    public ObservableList<Alumno> readAll(Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "SELECT * FROM alumnos";
        ResultSet rs = st.executeQuery(query);
        ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
        while (rs.next()) {
            Alumno alumno = new Alumno();
            alumno.setCi(Integer.parseInt(rs.getString(1)));
            alumno.setNombre(rs.getString(2));
            alumno.setFechapf(getFecha.get(rs.getString(3)));
            alumno.setFechat(getFecha.get(rs.getString(4)));
            alumno.setTel(rs.getString(5));
            alumno.setDir(rs.getString(6));
            alumno.setMail(rs.getString(7));
            alumnos.add(alumno);
        }
        rs.close();
        st.close();
        return alumnos;
    }

    @Override
    public void setDatabase(Connection conn) throws Exception {
        Statement st = conn.createStatement();
        String query = "CREATE TABLE alumnos(ci VARCHAR(100) PRIMARY KEY, nombre VARCHAR(100), fechapf VARCHAR(100), fechat VARCHAR(100), tel VARCHAR(100), dir VARCHAR(100), mail VARCHAR(100));";
        st.execute(query);
        st.close();
    }
}
