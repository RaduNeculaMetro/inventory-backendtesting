package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtils {

    public static String read (String fileName) {
        String expected = null;

        try {
            expected = new String(Files.readAllBytes(Paths.get("src/main/resources/" + fileName)));
        }catch (IOException e) {
            throw new RuntimeException("No file found " + e);
        }
        return expected;
    }
}
