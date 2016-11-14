import org.junit.Test;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Author Mateusz Wieczorek on 11/14/16.
 */
public class ChecksumUtilsTest {

    @Test
    public void shouldReturnMD5Checksum() {
        File file = new File("someFile.txt");
        String checkSum = "";
        try {
            checkSum = ChecksumUtils.calculateMD5Checksum(file);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assertThat(checkSum.isEmpty(), is(false));
    }

}