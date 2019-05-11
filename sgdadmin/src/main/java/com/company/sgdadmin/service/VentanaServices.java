/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jova_
 */
public interface VentanaServices {

    /**
     *
     * @param file
     * @param year
     * @param month
     * @param date
     * @param direccion
     * @param descripcion
     */
    public void getVentanas(MultipartFile file, String year, String month, String date, String direccion, String descripcion);

    public void visibleOptions(ModelAndView mv, String direccion);

}
