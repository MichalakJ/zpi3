import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.apache.commons.codec.digest.DigestUtils.*;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

/**
 * @Author Mateusz Wieczorek on 11/14/16.
 */
public class ChecksumUtils {

    public static String calculateMD5Checksum(File file) throws NoSuchAlgorithmException {
        FileInputStream fis = null;
        String md5 = "";
        try {
            fis = new FileInputStream(file);
            md5 = md5Hex(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static String calculateSHAChecksum(File file) throws NoSuchAlgorithmException {
        FileInputStream fis = null;
        String sha1 = "";
        try {
            fis = new FileInputStream(file);
            sha1 = sha1Hex(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sha1;
    }

}
