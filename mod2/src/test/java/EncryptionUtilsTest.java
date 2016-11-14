import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @Author Mateusz Wieczorek on 11/14/16.
 */
public class EncryptionUtilsTest {

    @Test
    public void shouldEncryptAndDecryptFile() {
        String key = "Bar12345Bar12345"; // 128 bit key
        String originalString = "";
        String stringAfterEncryption = "";

        File file = new File("someFile.txt");

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            List<String> values = stream.collect(Collectors.toList());
            originalString = values.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        EncryptionUtils.encrypt(file, key);
        EncryptionUtils.decrypt(file, key);

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            List<String> values = stream.collect(Collectors.toList());
            stringAfterEncryption = values.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(originalString, is(stringAfterEncryption));
    }
}