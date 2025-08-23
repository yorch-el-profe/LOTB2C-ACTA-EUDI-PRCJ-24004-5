package org.bedu.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Un controlador es el punto de acceso a un backend de spring
 * Ofrece un grupo de endpoints (o rutas) que realizan alguna acción.
 * Nota: Los controladores funcionan a través del protocolo HTTP.
 * 
 * Los endpoints son métodos con anotaciones @RequestMapping/@GetMapping/etc...
 */
@RestController
public class PruebaController {
    
    // "RequestMapping" crea un endpoint, se necesita proporcionar un nombre (o ruta)
    @RequestMapping("test")
    public String helloWorld() {
        return "Hello World";
    }
}
