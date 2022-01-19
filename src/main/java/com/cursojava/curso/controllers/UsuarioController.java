package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

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
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {

        if(!validarToken(token)){ return null; }

        return usuarioDao.getUsuarios();
    }

    public boolean validarToken(String token){
        String usuarioID = jwtUtil.getKey(token);
        return usuarioID != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create();


        try {
            // Hash password
            String hash = argon2.hash(10, 65536, 1, usuario.getPassword());

            usuario.setPassword(hash);
            usuarioDao.registrarUsuario(usuario);
        } finally {
            // Wipe confidential data
//            argon2.wipeArray(password);
        }

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
    public void eliminar(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id) {
        if(!validarToken(token)){ return; }
        usuarioDao.eliminarUsuario(id);
    }
}
