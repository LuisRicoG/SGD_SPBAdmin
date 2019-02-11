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
import com.company.sgdadmin.service.ListaUsuariosService;
import com.company.sgdadmin.service.UsuarioService;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdmintradorUsuariosController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ListaUsuariosService service;

     AdmintradorUsuariosController(ListaUsuariosService listaUsuariosService){
         this.service = listaUsuariosService;
        
    }
    //@RequestMapping(value = "/administradorusuarios")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("administradorusuarios");
        //listaUsuarios(mv.get, 0, 10);
        return mv;
    }

    @GetMapping("/nuevousuario")
    public String signup(Model model) {
        model.addAttribute("registro", new Login());
        return "nuevousuario";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute("registro") Login registro, Model model) {
        LoginEntity entity = new LoginEntity();
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

        boolean respuesta = usuarioService.registro(entity);

        if (respuesta) {
            model.addAttribute("correcto", "Usuario creado Satisfactoriamente");
            return "administradorusuarios";
        } else {
            model.addAttribute("error", "Inténtelo más tarde");
            return "nuevousuario";
        }
    }

    @GetMapping("/administradorusuarios")
    public String listaUsuarios(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<LoginEntity> loginPage = service.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("loginPage", loginPage);

        int totalPages = loginPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "administradorusuarios";
    }
}
