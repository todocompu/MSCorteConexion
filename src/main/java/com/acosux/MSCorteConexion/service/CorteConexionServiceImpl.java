/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSCorteConexion.service;

import com.acosux.MSCorteConexion.util.RespuestaWebTO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author mario
 */
@Service
public class CorteConexionServiceImpl implements CorteConexionService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${acosux.endpoint}")
    private String ENDPOINT;

    @Override
    public RespuestaWebTO getCarListaCuentasPorCobrarDetalladoTOCortesConexion(Map<String, Object> map) throws Exception {
        System.out.println("==> LLEGAMOS AL SERVICIO getCarListaCuentasPorCobrarDetalladoTOCortesConexion");
        RespuestaWebTO res = new RespuestaWebTO();
        try {
            res = restTemplate.postForObject(ENDPOINT + "/todocompuWS/carteraWebController/listarCuentasCobrarCorteConexionMS", map, RespuestaWebTO.class);
            System.out.println("==> REGRESAMOS CON LA RESPUESTA DESDE EL SERVIDOR: " + (res != null ? res.getMessage().toString() : "SIN RESPUESTA"));
        } catch (RestClientException e) {
            res.setMessage(e.getMessage() != null ? e.getMessage() : "Error desconocido");
        }
        return res;
    }

}
