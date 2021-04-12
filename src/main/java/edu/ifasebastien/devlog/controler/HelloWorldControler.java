package edu.ifasebastien.devlog.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldControler {
    @GetMapping("/hello")
    public String hello() {
        return "Héllo world";
    }
    @GetMapping("/")
    public String ras() {
        return "Rien à voir";
    }

}
