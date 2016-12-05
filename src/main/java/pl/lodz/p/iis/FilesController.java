package pl.lodz.p.iis;

import FileDownloader.FileDownloader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.iis.library.FileList;
import pl.lodz.p.iis.library.PostFile;

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

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ResponseEntity<PostFile> addFile(@RequestBody PostFile file){
        try {
            FileDownloader.downloadFile(file.getUrl(), ROOT_LOCATION + File.separator + file.getName());
        } catch (IOException e) {
            return new ResponseEntity<PostFile>(file, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PostFile>(file, HttpStatus.CREATED);
    }
}
