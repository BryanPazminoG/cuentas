package com.banquito.core.baking.cuenta.controller;

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

import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = { "http://localhost:4200"})
// @CrossOrigin(origins = { "http://localhost:4200",
// "http://34.173.161.134:4201", "http://34.176.205.203:4202",
// "http://34.176.102.118:4203", "http://34.176.137.180:4204" })
// @CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET,
// RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/cuentaintervinientes")
public class CuentaIntervinientesController {
    private final CuentaIntervinientesService cuentaIntervinientesService;

    public CuentaIntervinientesController(CuentaIntervinientesService cuentaIntervinientesService) {
        this.cuentaIntervinientesService = cuentaIntervinientesService;
    }

    @GetMapping("/{codCuenta}/{codCliente}")
    public ResponseEntity<CuentaIntervinientesDTO> ObtenerPorId(@PathVariable("codCuenta") Integer codCuenta,
            @PathVariable("codCliente") String codCliente) {
        try {
            log.info("Buscando cuenta interveniente con ID de cuenta: {} y ID de cliente: {}", codCuenta, codCliente);
            CuentaIntervinientesDTO dto = this.cuentaIntervinientesService.BuscarPorId(codCuenta, codCliente);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException rte) {
            log.error("Error al obtener las cuentas intervenientes", rte);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cuentas/{codCuenta}")
    public ResponseEntity<List<CuentaIntervinientesDTO>> ObtenerPorCuenta(
            @PathVariable("codCuenta") Integer codCuenta) {
        try {
            log.info("Buscando cuentas intervenientes para la cuenta con ID: {}", codCuenta);
            List<CuentaIntervinientesDTO> dto = this.cuentaIntervinientesService.BuscarPorCuenta(codCuenta);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException rte) {
            log.error("Error al obtener las cuentas intervenientes", rte);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clientes/{codCliente}")
    public ResponseEntity<List<CuentaIntervinientesDTO>> ObtenerPorCliente(
            @PathVariable("codCliente") String codCliente) {
        try {
            log.info("Buscando cuentas intervenientes para el cliente con ID: {}", codCliente);
            List<CuentaIntervinientesDTO> dto = this.cuentaIntervinientesService.BuscarPorCliente(codCliente);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException rte) {
            log.error("Error al obtener las cuentas intervenientes", rte);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estados/{estado}")
    public ResponseEntity<List<CuentaIntervinientesDTO>> ListarPorEstado(
            @PathVariable("estado") String estado) {
        try {
            log.info("Buscando cuentas intervenientes por el estado: {}", estado);
            List<CuentaIntervinientesDTO> dto = this.cuentaIntervinientesService.ListarPorEstado(estado);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException rte) {
            log.error("Error al obtener las cuentas intervenientes", rte);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CuentaIntervinientesDTO> Guardar(@RequestBody CuentaIntervinientesDTO dto) {
        try {
            log.info("Creando la cuenta interviniente: {}", dto);
            return ResponseEntity.ok(this.cuentaIntervinientesService.Crear(dto));
        } catch (RuntimeException rte) {
            log.error("Error al guardar cuenta interviniente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<CuentaIntervinientesDTO> Actualizar(@RequestBody CuentaIntervinientesDTO dto) {
        try {
            log.info("Se va a actualizar la cuenta interviniente: {}", dto);
            CuentaIntervinientesDTO cuentaIntDTO = this.cuentaIntervinientesService.Actualizar(dto);
            return ResponseEntity.ok(this.cuentaIntervinientesService.Crear(cuentaIntDTO));
        } catch (RuntimeException rte) {
            log.error("Error al actualizar el cuenta interviniente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{codCuenta}/{codCliente}")
    public ResponseEntity<Void> Eliminar(@PathVariable("codCuenta") Integer codCuenta, @PathVariable("codCliente") String codCliente) {
        try {
            log.info("Eliminando la cuenta interviniente con ID de cuenta: {} y ID de cliente: {}", codCuenta, codCliente);
            this.cuentaIntervinientesService.Eliminar(codCuenta, codCliente); 
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al eliminar la cuenta interviniente", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}