package listeners;

import windows.MainWindow;

/**
 * Created by maicol on 16/04/15.
 */
public interface ListenableObject {
    void setDatabaseListener(DatabaseListener databaseListener);

    void setMainWindow(MainWindow mainWindow);
}