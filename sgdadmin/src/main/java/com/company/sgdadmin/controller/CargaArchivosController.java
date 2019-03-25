///*
// *  ing_farias@live.com
// */
//package com.company.sgdadmin.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.company.sgdadmin.service.UploadFileService;
//import java.io.IOException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//public class CargaArchivosController {
//	
//      @Autowired
//    private UploadFileService uploadFileService;
//      
//      
//    @RequestMapping(value = "/cargaarchivos")
//    public ModelAndView sayHello(@RequestParam("nombre") String nombre) {
//    	ModelAndView mv = new ModelAndView();
//        //mv.addObject("message", "Hello Reader!");
//        mv.setViewName("cargaarchivos");
//        return mv;
//    }
//    
//        @PostMapping("/upload")
//    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
//        }
//
//        uploadFileService.saveFile(file);
//
//        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
//    }
//}
//
