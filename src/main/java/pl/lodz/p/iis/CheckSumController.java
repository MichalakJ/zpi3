package pl.lodz.p.iis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.iis.library.CheckSumRS;

import java.io.File;

/**
 * @Author Mateusz Wieczorek on 05.12.2016.
 */
@RestController
public class CheckSumController {

    private static final String DEFAULT_ROOT_LOCATION = "C:\\files";

    @RequestMapping(value = "/files/{name}/md5", method = RequestMethod.POST)
    public ResponseEntity<CheckSumRS> md5CheckSum(@PathVariable String name){
        String fullFilePath = DEFAULT_ROOT_LOCATION + File.separator + name;
        File file = new File(fullFilePath);

        if(!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String md5 = ChecksumUtils.calculateMD5Checksum(file);

        if (StringUtils.isEmpty(md5)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CheckSumRS response = new CheckSumRS();
        response.setCheckSum(md5);
        response.setType("md5");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{name}/sha", method = RequestMethod.POST)
    public ResponseEntity<CheckSumRS> shaCheckSum(@PathVariable String name){
        String fullFilePath = DEFAULT_ROOT_LOCATION + File.separator + name;
        File file = new File(fullFilePath);

        if(!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String sha = ChecksumUtils.calculateSHAChecksum(file);

        if (StringUtils.isEmpty(sha)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CheckSumRS response = new CheckSumRS();
        response.setCheckSum(sha);
        response.setType("sha");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
