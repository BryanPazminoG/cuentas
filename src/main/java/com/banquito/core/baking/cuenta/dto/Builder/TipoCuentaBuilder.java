package com.banquito.core.baking.cuenta.dto.Builder;

import com.banquito.core.baking.cuenta.domain.TipoCuenta;
import com.banquito.core.baking.cuenta.dto.TipoCuentaDTO;

public class TipoCuentaBuilder {

    public static TipoCuentaDTO toDTO(TipoCuenta tipoCuenta) {

        TipoCuentaDTO dto = TipoCuentaDTO.builder()
                .codTipoCuenta(tipoCuenta.getCodTipoCuenta())
                .codTasaInteres(tipoCuenta.getCodTasaInteres())
                .nombre(tipoCuenta.getNombre())
                .descripcion(tipoCuenta.getDescripcion())
                .tipoCapitalizacion(tipoCuenta.getTipoCapitalizacion())
                .formaCapitalizacion(tipoCuenta.getFormaCapitalizacion())
                .maximoNumeroIntervinientes(tipoCuenta.getMaximoNumeroIntervinientes())
                .fechaVigencia(tipoCuenta.getFechaVigencia())
                .estado(tipoCuenta.getEstado()).build();
        return dto;
    }

    public static TipoCuenta toTipoCuenta(TipoCuentaDTO dto) {

        TipoCuenta tipoCuenta = new TipoCuenta();
        tipoCuenta.setCodTipoCuenta(dto.getCodTipoCuenta());
        tipoCuenta.setCodTasaInteres(dto.getCodTasaInteres());
        tipoCuenta.setNombre(dto.getNombre());
        tipoCuenta.setDescripcion(dto.getDescripcion());
        tipoCuenta.setTipoCapitalizacion(dto.getTipoCapitalizacion());
        tipoCuenta.setFormaCapitalizacion(dto.getFormaCapitalizacion());
        tipoCuenta.setMaximoNumeroIntervinientes(dto.getMaximoNumeroIntervinientes());
        tipoCuenta.setFechaVigencia(dto.getFechaVigencia());
        tipoCuenta.setEstado(dto.getEstado());
        
        return tipoCuenta;
    }
}
