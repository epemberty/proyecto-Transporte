package transpem.Service.impl;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.DTO.TiqueteReporteDTO;
import transpem.Persistence.ITiqueteDAO;
import transpem.Service.ReporteService;


@Service

public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ITiqueteDAO iTiqueteDAO;



    @Override
    public List<TiqueteReporteDTO> 
    reporteTiquetes(
        Date fechaInicio, 
        Date fechaFin, 
        Long idMina, 
        Long idRuta, 
        Long idConductor, 
        Long valorMin, 
        Long valorMax) {
        return iTiqueteDAO.reporteTiquetes(
            fechaInicio,
            fechaFin, 
            idMina, 
            idRuta, 
            idConductor, 
            valorMin, 
            valorMax)
            .stream()
            .map(tiquete-> 
            TiqueteReporteDTO.builder()
            .id(tiquete.getId())
            .numeroTiquete(tiquete.getNumeroTiquete())
            .fecha(tiquete.getFecha())
            .nombre(tiquete.getNombre())
            .conductor(tiquete.getConductor())
            .vehiculo(tiquete.getVehiculo())
            .ruta(tiquete.getRuta())
            .pesoToneladas(tiquete.getPesoToneladas())
            .valorViaje(tiquete.getValorViaje())
            .anticipo(tiquete.getAnticipo())
            .saldoBruto(tiquete.getSaldoBruto())
            .encarpe(tiquete.getEncarpe())
            .combustible(tiquete.getCombustible())
            .retencion(tiquete.getRetencion())
            .reteica(tiquete.getReteica())
            .administracion(tiquete.getAdministracion())
            .saldoNeto(tiquete.getSaldoNeto())
            .build())
            .toList();
        
    }


}
