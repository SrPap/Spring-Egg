package com.egg.biblioteca.controladores;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;

@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, Model model) {
        try {
            autorServicio.crearAutor(nombre); // llamo a mi servicio para persistir
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("error", ex.getMessage());
            return "autor_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autores", autores);
        return "autor_list.html";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, ModelMap modelo) {
        modelo.put("autor", autorServicio.getOne(id));
        return "autor_modificar.html";
       }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, String nombre, ModelMap modelo) {
    try {
        autorServicio.modificarAutor(nombre, id);
        return "redirect:/autor/lista";
    } catch (MiException ex) {
        modelo.put("error", ex.getMessage());
        return "autor_modificar.html";
    }
    }

}
