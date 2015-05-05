package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Licencia {

    public enum Type {A, B, C, D, E, F, G1, G2, G3, H}

    private Alumno alumno;
    private ObjectProperty<LocalDate> fechaexp;
    private ObjectProperty<LocalDate> fechacad;
    private Type tipo;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LocalDate getFechaexp() {
        return fechaexp.get();
    }

    public ObjectProperty<LocalDate> fechaexpProperty() {
        return fechaexp;
    }

    public void setFechaexp(LocalDate fechaexp) {
        this.fechaexp.set(fechaexp);
    }

    public LocalDate getFechacad() {
        return fechacad.get();
    }

    public ObjectProperty<LocalDate> fechacadProperty() {
        return fechacad;
    }

    public void setFechacad(LocalDate fechacad) {
        this.fechacad.set(fechacad);
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public Licencia() {
        this.alumno = new Alumno();
        this.fechaexp = new SimpleObjectProperty<LocalDate>();
        this.fechacad = new SimpleObjectProperty<LocalDate>();
    }
}