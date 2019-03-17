/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jova_
 */
public interface VentanaServices {
     public void getVentanas(MultipartFile file,String year, String month, String date,String direccion);
    
    
    
}
