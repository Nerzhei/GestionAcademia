package listeners;

import java.util.EventListener;

/**
 * Created by maicol on 16/04/15.
 */
public interface DatabaseListener extends EventListener {
    void onAlumnosChange(ListenableObjectAlumno obj);

    void onLicensesChange(ListenableObjectLicencia obj);

    void onDatabaseChange(ListenableObject obj);
}
