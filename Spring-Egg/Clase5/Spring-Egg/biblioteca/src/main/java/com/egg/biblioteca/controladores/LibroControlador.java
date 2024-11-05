package com.egg.biblioteca.controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;
import com.egg.biblioteca.servicios.EditorialServicio;
import com.egg.biblioteca.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") // localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();                
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
                           @RequestParam(required = false) Integer ejemplares, @RequestParam UUID idAutor,
                           @RequestParam UUID idEditorial, ModelMap modelo) {
        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.addAttribute("exito", "Libro registrado exitosamente.");
            return "index.html";
        } catch (MiException ex) {
            modelo.addAttribute("error", "Error al registrar el libro: " + ex.getMessage());
            modelo.addAttribute("autores", autorServicio.listarAutores());
            modelo.addAttribute("editoriales", editorialServicio.listarEditoriales());
            return "libro_form.html"; // volvemos a cargar el formulario con mensaje de error
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Libro> libros = libroServicio.listarLibros();
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();                
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        modelo.addAttribute("libros", libros);
        return "libro_list.html";
    }

    @GetMapping("/modificar/{isbn}")
public String mostrarFormularioModificar(@PathVariable Long isbn, ModelMap model) {
    model.addAttribute("libro", libroServicio.getOne(isbn));
    model.addAttribute("autores", autorServicio.listarAutores());
    model.addAttribute("editoriales", editorialServicio.listarEditoriales()); 
    return "libro_modificar.html";
}

@PostMapping("/modificar/{isbn}")
public String modificarLibro(
    @PathVariable Long isbn,
    @RequestParam("titulo") String titulo,
    @RequestParam("ejemplares") Integer ejemplares,
    @RequestParam("alta") String alta, // Cambiar Date a String
    @RequestParam("autorID") UUID autorID,
    @RequestParam("editorialID") UUID editorialID,
    ModelMap modelo) {
    try {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaAlta = dateFormat.parse(alta);
        
        libroServicio.modificarLibro(isbn, titulo, ejemplares, fechaAlta, autorID, editorialID);
        return "redirect:/libro/lista";
    } catch (MiException e) {
        modelo.addAttribute("error", e.getMessage());
        modelo.addAttribute("autores", autorServicio.listarAutores());
        modelo.addAttribute("editoriales", editorialServicio.listarEditoriales());
        modelo.addAttribute("libro", libroServicio.getOne(isbn));
        return "libro_modificar.html";
    } catch (ParseException e) {
        modelo.addAttribute("error", "Formato de fecha inválido. Utiliza el formato yyyy-MM-dd.");
        return "libro_modificar.html";
    }
}




}
