///*
// *  ing_farias@live.com
// */
//package com.company.sgdadmin.controller;
//
///**
// *
// * @author jova_
// */
//
//import com.company.sgdadmin.service.UploadFileService;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URLConnection;
//import java.nio.charset.Charset;
//import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping(value = "/upload")
//public class EscriturasController {
//    
//      @Autowired
//    private UploadFileService uploadFileService;
//      
//      
//       @RequestMapping(value = "/escrituras")
//    public ModelAndView sayHello() {
//        ModelAndView mv = new ModelAndView();
//        //mv.addObject("message", "Hello Reader!");
//        mv.setViewName("escrituras");
//        return mv;
//    }
//      
////   @PostMapping("/upload")
////    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
////        if (file.isEmpty()) {
////            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
////        }
////
////        uploadFileService.saveFile(file);
////
////        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
////    }
//    
//}
