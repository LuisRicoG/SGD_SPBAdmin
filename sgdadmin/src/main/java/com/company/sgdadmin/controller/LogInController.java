package com.company.sgdadmin.controller;


import com.company.sgdadmin.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogInController {
	
    @RequestMapping(value = "/index")
    public ModelAndView getLogin() {
    	ModelAndView mv = new ModelAndView();
        mv.addObject("user", new Login());
        mv.setViewName("index");
        return mv;
    }
    /**
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView menu(Login login) {
        System.out.println("usuario: " + login.getUsuario());
        System.out.println("contraseña: " + login.getContrasena());
        return new ModelAndView("redirect:/menu.mvc");
    }**/
}

