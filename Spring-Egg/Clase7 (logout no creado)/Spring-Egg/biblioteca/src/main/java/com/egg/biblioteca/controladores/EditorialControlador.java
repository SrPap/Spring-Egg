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

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial") // localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/registrar") // localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, Model model) {
        try {
            editorialServicio.crearEditorial(nombre); // Llama al servicio para crear la editorial
        } catch (MiException ex) {
            Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            model.addAttribute("error", ex.getMessage()); 
            return "editorial_form.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editoriales", editoriales);
        return "editorial_list.html";
    }

    @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getOne(id));
        return "editorial_modificar.html";
       }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable UUID id, String nombre, ModelMap modelo) {
    try {
        editorialServicio.modificarEditorial(id, nombre);
        return "redirect:/editorial/lista";
    } catch (MiException ex) {
        modelo.put("error", ex.getMessage());
        return "editorial_modificar.html";
    }
}

}
