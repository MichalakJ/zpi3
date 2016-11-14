package FileDownloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Jakub on 2016-11-14.
 */
public class FileDownloader {

    public static void downloadFile(String url, String destination) throws IOException {
        URL website = new URL(url);
        InputStream in = website.openStream();
        Path target = Paths.get(destination);
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);

    }
}
