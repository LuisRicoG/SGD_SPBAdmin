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
        System.out.println(Encoder.getEncodePassword("reach2106"));
        System.out.println(Encoder.getEncodePassword("luisrico23"));
        System.out.println(Encoder.getEncodePassword("georchito"));
        System.out.println(Encoder.getEncodePassword("chuwy"));
    }
	
    public static String getEncodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}