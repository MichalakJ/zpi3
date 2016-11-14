package ZipManager;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Jakub on 2016-11-14.
 */
public class ZipManager {

    public static void unzip(String zipFile, String destination) throws IOException {

        byte[] buffer = new byte[1024];
        File folder = new File(destination);
        if (!folder.exists()) {
            folder.mkdir();
        }
        ZipInputStream zis =
                new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destination + File.separator + fileName);
            System.out.println("file unzip : " + newFile.getAbsoluteFile());
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
}
