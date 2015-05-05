package other;

import javafx.scene.image.Image;
import models.Alumno;

/**
 * Created by maicol on 27/04/15.
 */
@FunctionalInterface
public interface ImageReaderInt {
    Image read(Alumno alumno);
}
