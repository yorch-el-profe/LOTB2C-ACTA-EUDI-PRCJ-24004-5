package org.bedu.demo.controller;

import java.util.LinkedList;
import java.util.List;

import org.bedu.demo.model.TipoCambio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * USD: 18.60 MXN
 * EUR: 22.07 MXN
 * JPY: 0.13 MXN
 */
@RestController
public class TipoCambioController {

    private List<TipoCambio> tipoCambios = new LinkedList<>();

    public TipoCambioController() {
        tipoCambios.add(new TipoCambio("USD", 18.60));
        tipoCambios.add(new TipoCambio("EUR", 22.07));
        tipoCambios.add(new TipoCambio("JPY", 0.13));
    }
    
    // Obtener todos los tipos de cambio en MXN
    @RequestMapping("obtenerTipoCambios")
    public List<TipoCambio> obtenerTipoCambios() {
        return tipoCambios;
    }

    // Cambiar la moneda en MXN
    /**
     *  QueryString: Enviar parámetros al final de la URL de la forma:
     *      ? param1=valor1 & param2=valor2 & param3=valor3 ...
     * 
     *  Limitante: El tamaño máximo de una URL son 2000 caracteres aprox.
     * 
     *  El querystring es más usado para filtar búsqueda.
     * 
     *  Nota: Por default, los parámetros en Spring son obligatorios.
     */
    @RequestMapping("convertir")
    public TipoCambio convertir(
        @RequestParam("moneda") String moneda, 
        @RequestParam("cantidad") int cantidad) {
            TipoCambio buscado = null;

            for (TipoCambio tipo : tipoCambios) {
                if (tipo.getMoneda().equals(moneda)) {
                    buscado = tipo;
                    break;
                }
            }

            if (buscado == null) {
                return null;
            }

            return new TipoCambio(moneda, cantidad * buscado.getValor());
    }
}
