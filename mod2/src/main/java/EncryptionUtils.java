import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author Mateusz Wieczorek on 11/14/16.
 */
public class EncryptionUtils {

    private static final String RANDOM_INIT_VECTOR = "RandomInitVector";
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_TYPE = "AES";
    private static final String CHARACTER_ENCODING = "UTF-8";

    public static File encrypt(File file, String key) {
        try {
            List<String> values = readFile(file);
            List<String> encryptedValues = new ArrayList<>();

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARACTER_ENCODING), ENCRYPTION_TYPE);
            IvParameterSpec iv = new IvParameterSpec(RANDOM_INIT_VECTOR.getBytes(CHARACTER_ENCODING));

            for (String s : values) {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

                byte[] encrypted = cipher.doFinal(s.getBytes());
                encryptedValues.add(Base64.encodeBase64String(encrypted));
            }

            saveToFile(encryptedValues, file);

            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static File decrypt(File file, String key) {
        try {
            List<String> values = readFile(file);
            List<String> originalValues = new ArrayList<>();

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(CHARACTER_ENCODING), ENCRYPTION_TYPE);
            IvParameterSpec iv = new IvParameterSpec(RANDOM_INIT_VECTOR.getBytes(CHARACTER_ENCODING));

            for (String s : values) {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

                byte[] original = cipher.doFinal(Base64.decodeBase64(s));
                originalValues.add(new String(original));
            }

            saveToFile(originalValues, file);

            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

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

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; // 128 bit key

        File file = new File("someFile.txt");
        encrypt(file, key);
        //decrypt(file, key);
    }

}
