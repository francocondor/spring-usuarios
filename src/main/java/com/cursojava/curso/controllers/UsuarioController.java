package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Franco");
        usuario.setApellido("Cóndor");
        usuario.setEmail("222L");
        usuario.setTelefono("912341234");
        usuario.setPassword("aaa");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuario() {
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {
        usuarioDao.registrarUsuario(usuario);
    }

    @RequestMapping(value = "usuarioEditar")
    public Usuario editar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Franco");
        usuario.setApellido("Cóndor");
        usuario.setEmail("222L");
        usuario.setTelefono("912341234");
        usuario.setPassword("aaa");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id) {
        usuarioDao.eliminarUsuario(id);
    }

    @RequestMapping(value = "usuarioBuscar")
    public Usuario buscar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Franco");
        usuario.setApellido("Cóndor");
        usuario.setEmail("222L");
        usuario.setTelefono("912341234");
        usuario.setPassword("aaa");
        return usuario;
    }
}