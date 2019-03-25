/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.util.Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.company.sgdadmin.beans.Login;
import com.company.sgdadmin.entity.LoginEntity;
import com.company.sgdadmin.repository.LoginRepository;
import com.company.sgdadmin.service.ListaUsuariosService;
import com.company.sgdadmin.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
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

    @GetMapping("/userlist")
    @ResponseBody
    public List<LoginEntity> listaJSON() {
        return (List<LoginEntity>) loginRepository.findAll();
    }

    @PutMapping(value ="/updateUser/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody LoginEntity login) {

        //login = customerDAO.update(id, login);

        if (null == login) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(login, HttpStatus.OK);
    }
}
