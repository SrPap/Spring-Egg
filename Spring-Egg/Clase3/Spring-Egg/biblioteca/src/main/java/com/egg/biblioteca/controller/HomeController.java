package com.egg.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Ruta raíz
    public String home() {
        return "index"; // Nombre del archivo HTML en "templates" sin extensión
    }
}
