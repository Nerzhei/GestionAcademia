package models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Alumno {

    private IntegerProperty ci;
    private StringProperty nombre;
    private ObjectProperty<LocalDate> fechapf;
    private ObjectProperty<LocalDate> fechat;
    private StringProperty tel;
    private StringProperty mail;
    private StringProperty dir;

    public Alumno() {
        this.ci = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.fechapf = new SimpleObjectProperty<LocalDate>();
        this.fechat = new SimpleObjectProperty<LocalDate>();
        this.tel = new SimpleStringProperty();
        this.mail = new SimpleStringProperty();
        this.dir = new SimpleStringProperty();
    }

    public int getCi() {
        return ci.get();
    }

    public IntegerProperty ciProperty() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci.set(ci);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public LocalDate getFechapf() {
        return fechapf.get();
    }

    public ObjectProperty<LocalDate> fechapfProperty() {
        return fechapf;
    }

    public void setFechapf(LocalDate fechapf) {
        this.fechapf.set(fechapf);
    }

    public LocalDate getFechat() {
        return fechat.get();
    }

    public ObjectProperty<LocalDate> fechatProperty() {
        return fechat;
    }

    public void setFechat(LocalDate fechat) {
        this.fechat.set(fechat);
    }

    public String getTel() {
        return tel.get();
    }

    public StringProperty telProperty() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getMail() {
        return mail.get();
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public String getDir() {
        return dir.get();
    }

    public StringProperty dirProperty() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir.set(dir);
    }
}
