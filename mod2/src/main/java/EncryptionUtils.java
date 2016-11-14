import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Mateusz Wieczorek on 11/14/16.
 */
public class EncryptionUtils {

    private static List<String> readFile(File file) {
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveToFile(List<String> values, File file) {
        for (String s : values) {
            try {
                Files.write(Paths.get(file.getAbsolutePath()), s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
