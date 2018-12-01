package org.penfold.website;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello Katy, the time at the server is now " + new Date() + "\n";
    }
}