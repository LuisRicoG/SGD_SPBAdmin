/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.service;

import com.company.sgdadmin.entity.LoginEntity;
import com.company.sgdadmin.repository.LoginRepository;
import com.company.sgdadmin.util.Encoder;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author the_d
 */
@Service
public class UsuarioService {

    @Autowired
    public LoginRepository loginRepository;

    public boolean registro(LoginEntity login) {
        Optional<LoginEntity> loginBD = null;
        //1.-Validar que el usuario no exista
        if (login.getUsuario_id() != null) {
            loginBD = loginRepository.findById(login.getUsuario_id());
        }

        //2.-Si existe --- false
        if (loginBD != null) {
            LoginEntity entity = loginBD.get();
            if (!entity.getContrasena().equalsIgnoreCase(login.getContrasena())) {
                login.setContrasena(Encoder.getEncodePassword(login.getContrasena()));
            }
        }

        login = loginRepository.save(login);

        //4.-Obtener id de registro
        int loginId = login.getUsuario_id();

        //5.-Si el registro es > 0 regresar true
        if (loginId > 0) {
            return true;
        } //6.-Si el registro es <=0 regresar false
        else {
            return false;
        }
    }
}
