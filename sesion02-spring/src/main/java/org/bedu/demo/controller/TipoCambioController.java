package org.bedu.demo.controller;

import java.util.LinkedList;
import java.util.List;

import org.bedu.demo.model.TipoCambio;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
