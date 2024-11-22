package com.egg.desafio_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.desafio_spring.servicios.FabricaServicio;

@Controller
public class FabricaControlador {

    @Autowired
    private FabricaServicio fabricaServicio;

    @GetMapping("/fabrica/registro")
    public String mostrarFormularioRegistro(Model model) {
        return "fabrica_form.html"; 
    }

    @PostMapping("/fabrica/registro")
    public String registrarFabrica(@RequestParam String nombre, Model model) {
        try {
            fabricaServicio.crearFabrica(nombre); 
            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("error", "Hubo un error al registrar la fábrica.");
            return "registro_fabrica"; 
        }
    }
}