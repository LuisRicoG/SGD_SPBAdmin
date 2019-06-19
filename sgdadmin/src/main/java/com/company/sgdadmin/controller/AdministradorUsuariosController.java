/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.util.Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.company.sgdadmin.entity.LoginEntity;
import com.company.sgdadmin.repository.LoginRepository;
import com.company.sgdadmin.service.ListaUsuariosService;
import com.company.sgdadmin.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/administradorusuarios")

public class AdministradorUsuariosController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ListaUsuariosService service;

    AdministradorUsuariosController(ListaUsuariosService listaUsuariosService) {
        this.service = listaUsuariosService;

    }

    @GetMapping
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("administradorusuarios");
        return mv;
    }

    @PostMapping(value = "/registro", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registro(@RequestBody LoginEntity login, Model model) {

        login.setContrasena(Encoder.getEncodePassword(login.getContrasena()));
        boolean respuesta = usuarioService.registro(login);
        if (!respuesta) {
            return new ResponseEntity("No Customer found for ID " + login.getUsuario_id(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(login, HttpStatus.OK);

    }

    @GetMapping("/userlist")
    @ResponseBody
    public List<LoginEntity> listaJSON() {
        return (List<LoginEntity>) loginRepository.findAll();
    }

    @PutMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody LoginEntity login) {

        boolean respuesta = usuarioService.registro(login);
        if (!respuesta) {
            return new ResponseEntity("No Customer found for ID " + login.getUsuario_id(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(login, HttpStatus.OK);
    }
}
