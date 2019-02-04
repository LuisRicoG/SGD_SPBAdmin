/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.util.Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.company.sgdadmin.beans.Login;
import com.company.sgdadmin.entity.LoginEntity;
import com.company.sgdadmin.repository.LoginRepository;
import com.company.sgdadmin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdmintradorUsuariosController {
    
    @Autowired
    private LoginRepository loginRepository;
	
    @Autowired
    private UsuarioService usuarioService;
	
    @RequestMapping(value = "/administradorusuarios")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("administradorusuarios");
        return mv;
    }
    
    @GetMapping("/nuevousuario")
	public String signup(Model model) {
            model.addAttribute("registro", new Login());
            return "nuevousuario";
	}
    
    @PostMapping("/registro")
    public String registro(@ModelAttribute("registro") Login registro, Model model) {
	LoginEntity entity=new LoginEntity();
	entity.setNombre1(registro.getNombre1());
        entity.setNombre2(registro.getNombre2());
	entity.setApellido_paterno(registro.getApellidopaterno());
	entity.setApellido_materno(registro.getApellidomaterno());
        entity.setCorreo_electronico(registro.getCorreoelectronico());
        entity.setTelefono(registro.getTelefono());
	entity.setUsuario(registro.getUsuario());
	entity.setContrasena(Encoder.getEncodePassword(registro.getContrasena()));
	entity.setEstatus(registro.getEstatus());
        entity.setRol_id(registro.getRolid());
		
	boolean respuesta=usuarioService.registro(entity);
		
	if (respuesta){
            model.addAttribute("correcto","Usuario creado Satisfactoriamente");
            return "administradorusuarios";
        }
	else {
            model.addAttribute("error","Inténtelo más tarde");
            return "nuevousuario";			
        }
    }
}

