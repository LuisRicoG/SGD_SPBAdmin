/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.company.sgdadmin.service.UploadFileService;
import com.company.sgdadmin.service.VentanaServices;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VentanasController {
	
      @Autowired
    private UploadFileService uploadFileService;
      
          VentanaServices service;
          
          //constructor
           @Autowired
    public VentanasController(VentanaServices vtanaService) {
        this.service = vtanaService;
    }
      String direccion;
      
      
   @RequestMapping(value = "/cargaarchivos{direccion}")
    public ModelAndView sayHello(@RequestParam("direccion") String direccion) {
    	ModelAndView mv = new ModelAndView();
        this.direccion=direccion; 
        mv.addObject("message", direccion);
       service.visibleOptions(mv,direccion);
        mv.setViewName("sgdupload");
        return mv;
    }
    
        @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "year", defaultValue = "2017") String year,
            @RequestParam(value = "month", defaultValue = "ENERO") String month,
            @RequestParam(value = "date", defaultValue = "111111") String date) throws IOException {
        
   service.getVentanas(file,year, month,date,this.direccion);
         return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
       
   
    }
}

