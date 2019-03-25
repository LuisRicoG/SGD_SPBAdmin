/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author the_d
 */
public class Encoder {
	
    public static void main(String args[]){
        System.out.println("Allan " + Encoder.getEncodePassword("reach2106"));
        System.out.println("Luis " + Encoder.getEncodePassword("luisrico23"));
        System.out.println("yo " + Encoder.getEncodePassword("jepp"));
        System.out.println("Chuwy " + Encoder.getEncodePassword("chuwy"));
        System.out.println("Patron " + Encoder.getEncodePassword("STAPIA2019"));
    }
	
    public static String getEncodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}