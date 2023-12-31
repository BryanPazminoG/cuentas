package com.banquito.core.baking.cuenta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;

@RestController
@RequestMapping("/cuentaintervinientes")
public class CuentaIntervinientesController {
    private CuentaIntervinientesService cuentaIntervinientesService;

    public CuentaIntervinientesController(CuentaIntervinientesService cuentaIntervinientesService) {
        this.cuentaIntervinientesService = cuentaIntervinientesService;
    }

    @GetMapping("/getbyid/{cuentaid}/{clientepersonaid}")
    public ResponseEntity<CuentaIntervinientes> GetById(@PathVariable("cuentaid") Integer cuentaId,
            @PathVariable("clientepersonaid") Integer clientePersonaId) {
        return cuentaIntervinientesService.getById(cuentaId, clientePersonaId)
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<CuentaIntervinientes> Save(@RequestBody CuentaIntervinientes cuentaIntervinientes) {
        return new ResponseEntity<>(cuentaIntervinientesService.create(cuentaIntervinientes), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cuentaid}/{clientepersonaid}")
    public ResponseEntity<Boolean> Delete(@PathVariable("creditoid") Integer cuentaId,
            @PathVariable("clienteid") Integer clieclientePersonaIdnteId) {
        cuentaIntervinientesService.delete(cuentaId, clieclientePersonaIdnteId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CuentaIntervinientes> Update(@RequestBody CuentaIntervinientes cuentaIntervinientes) {
        return new ResponseEntity<>(cuentaIntervinientesService.update(cuentaIntervinientes), HttpStatus.OK);
    }
}
