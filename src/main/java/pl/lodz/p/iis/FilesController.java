package pl.lodz.p.iis;

import FileDownloader.FileDownloader;
import ZipManager.ZipManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.iis.library.ResultMessage;
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
    public ResponseEntity<ResultMessage> addFile(@RequestBody PostFile file){
        try {
            FileDownloader.downloadFile(file.getUrl(), ROOT_LOCATION + File.separator + file.getName());
        } catch (IOException e) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("failed :" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultMessage>(new ResultMessage("created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/files/{name}/actions.zip", method = RequestMethod.POST)
    public ResponseEntity<ResultMessage> zipFile(@PathVariable String name, @RequestBody PostFile zipName){
        try {
            ZipManager.zip(ROOT_LOCATION + File.separator + name, ROOT_LOCATION + File.separator + zipName.getName());
        }catch(Exception ex){
            return new ResponseEntity<ResultMessage>(new ResultMessage("cannot create zip archive: "+ ex.getMessage()), HttpStatus.OK);
        }
        return new ResponseEntity<ResultMessage>(new ResultMessage("success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{name}/actions.unzip", method = RequestMethod.POST)
    public ResponseEntity<ResultMessage> unzipFile(@PathVariable String name){
        String zipFileDir = ROOT_LOCATION + File.separator + name;
        File file = new File(zipFileDir);

        if(!file.exists()) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("file not found"), HttpStatus.NOT_FOUND);
        }
        if(!name.endsWith(".zip")){
            return new ResponseEntity<ResultMessage>(new ResultMessage("not a zip file"), HttpStatus.BAD_REQUEST);
        }
        try {
            ZipManager.unzip(zipFileDir, ROOT_LOCATION);
        } catch (Exception e) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("could not unzip file: "+ e.getMessage()), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<ResultMessage>(new ResultMessage("success"), HttpStatus.OK);
    }
}
