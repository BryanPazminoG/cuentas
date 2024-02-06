package com.banquito.core.baking.cuenta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/cuentaintervinientes")
public class CuentaIntervinientesController {
    private final CuentaIntervinientesService cuentaIntervinientesService;

    public CuentaIntervinientesController(CuentaIntervinientesService cuentaIntervinientesService) {
        this.cuentaIntervinientesService = cuentaIntervinientesService;
    }

    @GetMapping("/getbyid/{cuentaid}/{clientepersonaid}")
    public ResponseEntity<CuentaIntervinientes> GetById(@PathVariable("cuentaid") Integer cuentaId,
            @PathVariable("clientepersonaid") Integer clientePersonaId) {
            log.info("Recibida solicitud para obtener la cuenta interveniente con ID de cuenta: {} y ID de cliente: {}", cuentaId, clientePersonaId);

        return cuentaIntervinientesService.getById(cuentaId, clientePersonaId)
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getbycuenta/{cuentaid}")
    public ResponseEntity<Iterable<CuentaIntervinientes>> ObtenerPorCuenta(@PathVariable("cuentaid") Integer cuentaId) {
        log.info("Se encontraron cuentas intervenientes para la cuenta con ID: {}", cuentaId);
        return new ResponseEntity<>(cuentaIntervinientesService.getByCuenta(cuentaId), HttpStatus.OK);
    }

    @GetMapping("/getcuentbyinter/{clientepersonaid}")
    public ResponseEntity<List<Cuenta>> GetByCuentaByInter(@PathVariable("clientepersonaid") Integer clientepersonaid) {
        log.info("Se encontraron cuentas para el cliente con ID: {}", clientepersonaid);
        return new ResponseEntity<>(cuentaIntervinientesService.getCuentaByInter(clientepersonaid), HttpStatus.OK);
    }

    @GetMapping("/getbycliente/{clientepersonaid}")
    public ResponseEntity<Iterable<CuentaIntervinientes>> GetByCodCliente(@PathVariable("clientepersonaid") Integer clientePersonaId) {
        log.info("Se encontraron cuentas intervenientes para el cliente/persona con ID: {}", clientePersonaId);
        return new ResponseEntity<>(cuentaIntervinientesService.getByCodCliente(clientePersonaId), HttpStatus.OK);
    }

    @PostMapping("/guardado")
    public ResponseEntity<CuentaIntervinientes> Guardar(@RequestBody CuentaIntervinientesDTO cuentaIntervinientes) {
        log.info("Cuenta interveniente guardada con éxito: {}", cuentaIntervinientes);
        return new ResponseEntity<>(cuentaIntervinientesService.crear(cuentaIntervinientes), HttpStatus.OK);
    }

    @DeleteMapping("/eliminacion/{cuentaid}/{clienteid}")
    public ResponseEntity<Boolean> Borrar(@PathVariable("cuentaid") Integer cuentaId,
            @PathVariable("clienteid") Integer clieclientePersonaIdnteId) {
        cuentaIntervinientesService.borrar(cuentaId, clieclientePersonaIdnteId);
        log.info("Cuenta interveniente eliminada con éxito. ID de cuenta: {}, ID de cliente/persona: {}", cuentaId, clieclientePersonaIdnteId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/actualizacion")
    public ResponseEntity<CuentaIntervinientes> actualizar(@RequestBody CuentaIntervinientesDTO cuentaIntervinientes) {
        log.info("Cuenta interveniente actualizada con éxito: {}", cuentaIntervinientes);
        return new ResponseEntity<>(cuentaIntervinientesService.actualizar(cuentaIntervinientes), HttpStatus.OK);
    }
}
