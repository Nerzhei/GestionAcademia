package listeners;

import javafx.collections.ObservableList;
import models.Alumno;

/**
 * Created by maicol on 21/04/15.
 */
public interface ListenableObjectAlumno extends ListenableObject {
    void setTabla(ObservableList<Alumno> alumnos);
}
