package com.tp8.tp8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicPrivateController {

    @GetMapping("/public")
    public String publico() {
        return "Endpoint publico";
    }

    @GetMapping("/private")
    public String privado() {
        return "Acceso autorizado con rol USER";
    }
}
