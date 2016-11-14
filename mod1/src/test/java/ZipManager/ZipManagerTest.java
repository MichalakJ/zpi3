package ZipManager;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Jakub on 2016-11-14.
 */
public class ZipManagerTest {
    //@Ignore
    @Test
    public void zipFileExistsValidLocation_unzip_extractedFileExists() throws URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("zipTest.zip").getFile());
        ZipManager.unzip(file.getAbsolutePath(), file.getParent());
        assertThat(file.getParentFile().exists(), equalTo(true));
    }
}
