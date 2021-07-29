package com.fundamentos.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    //Acepta todas las solicitudes dentro de este método a nivel HTTP
    @RequestMapping
    //Para las respuestas del servicio
    @ResponseBody
    public ResponseEntity<String> function() {
        return new ResponseEntity<>("Hello from controller", HttpStatus.OK);
    }

}
