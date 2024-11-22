package com.egg.desafio_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.desafio_spring.entidades.Fabrica;
import com.egg.desafio_spring.servicios.ArticuloServicio;
import com.egg.desafio_spring.servicios.FabricaServicio;

@Controller
public class ArticuloControlador {

    @Autowired
    private FabricaServicio fabricaServicio;

    @Autowired
    private ArticuloServicio articuloServicio;

    @GetMapping("/articulo/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("fabricas", fabricaServicio.listarFabricas());
        return "articulo_form.html";
    }

    @PostMapping("/articulo/registro")
    public String registrarFabrica(@RequestParam String nombre,@RequestParam String descripcion, @RequestParam Fabrica fabrica, Model model) {
        try {
            articuloServicio.crearArticulo(nombre, descripcion, fabrica); 
            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("error", "Hubo un error al registrar el articulo.");
            return "articulo_form.html"; 
        }
    }
}