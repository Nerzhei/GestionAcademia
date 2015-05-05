package listeners;

import javafx.collections.ObservableList;
import models.Licencia;

/**
 * Created by maicol on 21/04/15.
 */
public interface ListenableObjectLicencia extends ListenableObject {
    void setTabla(ObservableList<Licencia> licencias);
}
