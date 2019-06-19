/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.util;

import com.company.sgdadmin.entity.LoginEntity;
import com.company.sgdadmin.repository.LoginRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author JEPPLAP
 */
public class UsuariosUtil {
	@Autowired
	private LoginRepository loginRepository;
    //private static List<LoginEntity> entitys = new ArrayList<LoginEntity>();

    private static final int NUM_USUARIOS = 30;

    public static List<LoginEntity> buildUsuarios() {
        UsuariosUtil util = new UsuariosUtil();
        
//        if (entitys.isEmpty()) {
//            IntStream.range(0, NUM_USUARIOS).forEach(n -> {
//                LoginEntity entity =new LoginEntity();
////                entity.setNombre1(registro.getNombre1());
////                entity.setNombre2(registro.getNombre2());
////                entity.setApellido_paterno(registro.getApellidopaterno());
////                entity.setApellido_materno(registro.getApellidomaterno());
////                entity.setCorreo_electronico(registro.getCorreoelectronico());
////                entity.setTelefono(registro.getTelefono());
////                entity.setUsuario(registro.getUsuario());
////                entity.setContrasena(Encoder.getEncodePassword(registro.getContrasena()));
////                entity.setEstatus(registro.getEstatus());
////                entity.setRol_id(registro.getRolid());
//               entitys.add(entity);
//            });
//        }

        return util.getEntities();
    }
    private List<LoginEntity> getEntities(){
        return (List<LoginEntity>)loginRepository.findAll();
    }
}
