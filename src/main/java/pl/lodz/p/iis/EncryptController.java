package pl.lodz.p.iis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.iis.library.EncryptionFileRQ;
import pl.lodz.p.iis.library.ResultMessage;

import java.io.File;

/**
 * @Author Mateusz Wieczorek on 05.12.2016.
 */
@RestController
public class EncryptController {

    private static final String DEFAULT_ROOT_LOCATION = "C:\\files";

    @RequestMapping(value = "/files/actions.encrypt", method = RequestMethod.POST)
    public ResponseEntity<ResultMessage> encryptFile(@RequestBody EncryptionFileRQ fileRQ) {
        String fullFilePath = DEFAULT_ROOT_LOCATION + File.separator + fileRQ.getName();
        File file = new File(fullFilePath);

        if(!file.exists()) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("file not found"), HttpStatus.NOT_FOUND);
        }

        File encryptedFile = EncryptionUtils.encrypt(file, fileRQ.getEncryptionKey());

        if (encryptedFile == null) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ResultMessage>(new ResultMessage("success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/files/actions.decrypt", method = RequestMethod.POST)
    public ResponseEntity<ResultMessage> decryptFile(@RequestBody EncryptionFileRQ fileRQ) {
        String fullFilePath = DEFAULT_ROOT_LOCATION + File.separator + fileRQ.getName();
        File file = new File(fullFilePath);

        if(!file.exists()) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("file not found"), HttpStatus.NOT_FOUND);
        }

        File decryptedFile = EncryptionUtils.decrypt(file, fileRQ.getEncryptionKey());

        if (decryptedFile == null) {
            return new ResponseEntity<ResultMessage>(new ResultMessage("unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ResultMessage>(new ResultMessage("success"), HttpStatus.OK);
    }
}
