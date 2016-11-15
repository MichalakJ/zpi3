package FileDownloader;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.InvalidPathException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Jakub on 2016-11-14.
 */
public class FileDownloaderTest {

    @Test
    public void urlAndDestinationValid_downloadFile_destinationExists() throws IOException {
        FileDownloader.downloadFile("http://www.textfiles.com/games/abc.txt", "test.txt");
        assertThat(new File("test.txt").exists(), equalTo(true));
    }

    @Test(expected = MalformedURLException.class)
    public void incorrectURL_downloadFile_throwsMalformedURLException() throws IOException {
        FileDownloader.downloadFile("gaws", "E:\\tests\\test2.txt");
    }


    @Test(expected = InvalidPathException.class)
    public void incorrectDestination_downloadFile_throwsMalformedURLException() throws IOException {
        FileDownloader.downloadFile("http://www.textfiles.com/games/abc.txt", "X:\\X:\\X:\\");
    }

}
