package com.banquito.core.baking.cuenta.dto;

import com.banquito.core.baking.cuenta.domain.TipoCuenta;

public class TipoCuentaBuilder {

    public static TipoCuentaDTO toDTO(TipoCuenta tipoCuenta) {

        TipoCuentaDTO dto = TipoCuentaDTO.builder().codTasaInteres(tipoCuenta.getCodTasaInteres())
                .codTipoCuenta(tipoCuenta.getCodTipoCuenta())
                .descripcion(tipoCuenta.getDescripcion())
                .fechaCreacion(tipoCuenta.getFechaCreacion())
                .fechaUltimoCambio(tipoCuenta.getFechaUltimoCambio())
                .formaCapitalizacion(tipoCuenta.getFormaCapitalizacion())
                .maximoNumeroIntervinientes(tipoCuenta.getMaximoNumeroIntervinientes())
                .nombre(tipoCuenta.getNombre())
                .tipoCapitalizacion(tipoCuenta.getTipoCapitalizacion()).build();
        return dto;
    }

    public static TipoCuenta toTipoCuenta(TipoCuentaDTO dto) {

        TipoCuenta tipoCuenta = new TipoCuenta();
        tipoCuenta.setCodTasaInteres(dto.getCodTasaInteres());
        tipoCuenta.setCodTipoCuenta(dto.getCodTipoCuenta());
        tipoCuenta.setDescripcion(dto.getDescripcion());
        tipoCuenta.setFechaCreacion(dto.getFechaCreacion());
        tipoCuenta.setFechaUltimoCambio(dto.getFechaUltimoCambio());
        tipoCuenta.setFormaCapitalizacion(dto.getFormaCapitalizacion());
        tipoCuenta.setMaximoNumeroIntervinientes(dto.getMaximoNumeroIntervinientes());
        tipoCuenta.setNombre(dto.getNombre());
        tipoCuenta.setTipoCapitalizacion(dto.getTipoCapitalizacion());
        return tipoCuenta;
    }

    public static TipoCuenta copyTipoCuenta(TipoCuenta source, TipoCuenta destiny) {
        if (source.getCodTipoCuenta() != null) {
            destiny.setCodTipoCuenta(source.getCodTipoCuenta());
        }

        if (source.getCodTasaInteres() != null) {
            destiny.setCodTasaInteres(source.getCodTasaInteres());
        }

        if (source.getNombre() != null) {
            destiny.setNombre(source.getNombre());
        }

        destiny.setDescripcion(source.getDescripcion());

        if (source.getTipoCapitalizacion() != null) {
            destiny.setTipoCapitalizacion(source.getTipoCapitalizacion());
        }

        if (source.getFormaCapitalizacion() != null) {
            destiny.setFormaCapitalizacion(source.getFormaCapitalizacion());
        }

        if (source.getMaximoNumeroIntervinientes() != null) {
            destiny.setMaximoNumeroIntervinientes(source.getMaximoNumeroIntervinientes());
        }

        destiny.setFechaCreacion(source.getFechaCreacion());
        destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());
        return destiny;
    }
}
