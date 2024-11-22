package com.egg.desafio_spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.desafio_spring.excepciones.MiException;
import com.egg.desafio_spring.servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador{

      @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/") 
    public String index() {
        return "index.html";  
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/inicio") 
    public String inicio() {
        return "inicio.html";  
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos!");
        }
        return "login.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html"; 
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String username, 
                                  @RequestParam String contrasena,
                                  @RequestParam String nombre, 
                                  @RequestParam String apellido,
                                  ModelMap model) {
    try {
        usuarioServicio.registrarUsuario(username, contrasena, nombre, apellido);
        model.addAttribute("success", "Usuario registrado con éxito");
        return "redirect:/";
    } catch (MiException e) {
        model.addAttribute("error", e.getMessage());
        return "registro.html";
    }
}

}