package pl.lodz.p.iis;

import FileDownloader.FileDownloader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.iis.library.FileList;

import java.io.File;
import java.io.IOException;

@RestController
public class FilesController {
    private static final String ROOT_LOCATION = "C:\\files";
    @RequestMapping("/files")
    public FileList greeting(@RequestParam(value="name", defaultValue="World") String name) throws IOException {
        File root = new File(ROOT_LOCATION);
        FileList fileList = new FileList();
        fileList.setFileList(root.list());
        return fileList;
    }
}
