package com.egg.desafio_spring.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.desafio_spring.entidades.Usuario;
import com.egg.desafio_spring.excepciones.MiException;
import com.egg.desafio_spring.servicios.UsuarioServicio;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "usuario_list";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable UUID id, Model model) throws MiException {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuario_editar";
    }

    @PostMapping("/editar")
    public String editarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioServicio.actualizarUsuario(usuario.getId(), usuario.getNombre(), usuario.getApellido(),
                    usuario.getContrasena());
            return "redirect:/usuarios/listar";
        } catch (MiException e) {
            model.addAttribute("error", e.getMessage());
            return "usuario_editar";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable UUID id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return "redirect:/usuarios/listar";
        } catch (MiException e) {
            return "error";
        }
    }
}