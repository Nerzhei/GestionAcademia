package other;

import models.Alumno;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by maicol on 27/04/15.
 */
public class ImageCopy {
    public ImageCopy(File file, Alumno alumno) {
        try {
            Path source = Paths.get(file.getAbsolutePath());
            String ext = "";
            int i = Validator.getString(file.getAbsoluteFile()).lastIndexOf(".");
            if (i > 0) {
                ext = Validator.getString(file.getAbsoluteFile()).substring(i + 1);
            }
            Path destination = Paths.get("./images", Validator.getString(alumno.getCi()) + "." + ext);
            destination.toFile().mkdirs();
            Files.copy(source, destination, NOFOLLOW_LINKS, COPY_ATTRIBUTES, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
