package pl.lodz.p.iis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilesController {

    @RequestMapping("/files")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello " + name;
    }
}
