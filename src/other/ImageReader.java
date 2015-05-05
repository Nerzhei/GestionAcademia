package other;

import javafx.scene.image.Image;
import models.Alumno;

import java.io.File;

/**
 * Created by maicol on 27/04/15.
 */
public class ImageReader {
    public Image getImgByAlumno(Alumno alumno) {
        File file = null;
        Image image = null;
        try {
            file = new File("./images/" + Validator.getString(alumno.getCi()) + ".jpg");
            image = new Image(file.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
