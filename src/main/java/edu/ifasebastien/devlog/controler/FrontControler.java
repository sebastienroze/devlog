package edu.ifasebastien.devlog.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class FrontControler
{
    @GetMapping("/front")
    public String getFront() {
        return "index";
    }
}
