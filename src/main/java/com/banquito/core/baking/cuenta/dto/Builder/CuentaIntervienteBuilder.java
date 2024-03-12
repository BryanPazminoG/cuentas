package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientesPK;
import com.banquito.core.baking.cuenta.dto.CuentaIntervinientesDTO;

public class CuentaIntervienteBuilder {

    public static CuentaIntervinientesDTO toDTO(CuentaIntervinientes cuentaIntervinientes) {
        CuentaIntervinientesDTO dto = CuentaIntervinientesDTO.builder()
                .codCuenta(cuentaIntervinientes.getPK().getCodCuenta())
                .codCliente(cuentaIntervinientes.getPK().getCodCliente())
                .tipoInterviniente(cuentaIntervinientes.getTipoInterviniente())
                .fechaInicio(cuentaIntervinientes.getFechaInicio())
                .fechaFin(cuentaIntervinientes.getFechaFin())
                .estado(cuentaIntervinientes.getEstado())
                .build();
        return dto;
    }

    public static CuentaIntervinientes toCuentaInterviente(CuentaIntervinientesDTO dto) {
        CuentaIntervinientes cuentaIntervinientes = new CuentaIntervinientes();
        CuentaIntervinientesPK PK = new CuentaIntervinientesPK();

        PK.setCodCuenta(dto.getCodCuenta());
        PK.setCodCliente(dto.getCodCliente());
        cuentaIntervinientes.setPK(PK);
        cuentaIntervinientes.setTipoInterviniente(dto.getTipoInterviniente());
        cuentaIntervinientes.setFechaInicio(dto.getFechaInicio());
        cuentaIntervinientes.setFechaFin(dto.getFechaFin());
        cuentaIntervinientes.setEstado(dto.getEstado());

        return cuentaIntervinientes;
    }

    public static CuentaIntervinientes copyCuentaInterviente(CuentaIntervinientes source, CuentaIntervinientes destiny) {

        if (source.getTipoInterviniente() != null) {
            destiny.setTipoInterviniente(source.getTipoInterviniente());
        }

        if (source.getFechaInicio() != null) {
            destiny.setFechaInicio(source.getFechaInicio());
        }

        if (source.getFechaFin() != null) {
            destiny.setFechaFin(source.getFechaFin());
        }

        if (source.getEstado() != null) {
            destiny.setEstado(source.getEstado());
        }

        return destiny;
    }
}
